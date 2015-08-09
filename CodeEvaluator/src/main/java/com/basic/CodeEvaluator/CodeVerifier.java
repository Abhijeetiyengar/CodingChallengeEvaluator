package com.basic.CodeEvaluator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.LogOutputStream;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.FileUtils;

public class CodeVerifier {

	public static void main(String[] args) throws Exception {

		StandaloneCodeCompilerAndExecutor compiler = new StandaloneCodeCompilerAndExecutor();
		String className = compiler.compileJavaObject(args[0]);

		String pathForInputFile = args[1];
		String outputSolutionFile = args[2];

		List<String> outputResultList = FileUtils.readLines(new File(
				outputSolutionFile));
		List<String> inputList = FileUtils
				.readLines(new File(pathForInputFile));

		CodeVerifier codeVerifier = new CodeVerifier();

		ProcessCommandProcessor commandProcessor = codeVerifier.new ProcessCommandProcessor(
				className);

		int inputIndex=0;
		boolean isSucess=true;
	//	inputList.forEach(inputRow -> commandProcessor.accept(inputRow,outputResultList.get(inputIndex++)));
		for(String ipString:inputList)
		{
			isSucess=commandProcessor.accept(ipString, outputResultList.get(inputIndex++));
		}
		
		if(isSucess)
			System.out.println("Everything is hunky dory");

	}

	public class ProcessCommandProcessor  {

		String className;
		
		public ProcessCommandProcessor(String className) {
			this.className = className;
			
		}

		//@Override
		public boolean accept(String t,String outputIndexString) throws RuntimeException {
			CommandLine commandLine = new CommandLine("/bin/sh");
			commandLine.addArguments(new String[] { "-c",
					"java -cp /tmp " + className + " " + t }, false);

			DefaultExecutor executor = new DefaultExecutor();
			List<String> outputLineList=new ArrayList<String>();
			PumpStreamHandler psh = new PumpStreamHandler(
					new CodeVerifier().new CollectingLogOutputStream(outputLineList));
			executor.setStreamHandler(psh);
			ExecuteWatchdog watchdog = new ExecuteWatchdog(10000);
			executor.setWatchdog(watchdog);
			try {
				int exitValue = executor.execute(commandLine);
				if(outputLineList.get(0).equals(outputIndexString))
					return true;
				else
					throw new RuntimeException("Exception occured while matching i/p "+ t +" and output "+outputIndexString);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

	}

	public class CollectingLogOutputStream extends LogOutputStream {
		
		private List<String> outputLineList;
		
		CollectingLogOutputStream(List<String> outputLineList)
		{
			this.outputLineList=outputLineList;
		}

		@Override
		protected void processLine(String line, int level) {
			outputLineList.add(line);
			
			System.out.println(line);

		}

		
	}

}
