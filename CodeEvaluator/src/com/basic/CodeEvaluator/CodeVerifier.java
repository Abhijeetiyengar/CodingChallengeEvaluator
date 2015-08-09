package com.basic.CodeEvaluator;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.LogOutputStream;
import org.apache.commons.exec.PumpStreamHandler;

public class CodeVerifier {

	public static void main(String[] args)  throws Exception{
		
		StandaloneCodeCompilerAndExecutor compiler =new StandaloneCodeCompilerAndExecutor();
		String className=compiler.compileJavaObject(args[0]);
		

//		String pathForInputFile=args[1];
//		String outputSolutionFile=args[2];

		List<CommandLine> commandLineList;

		 CommandLine commandLine = new CommandLine("/bin/sh");
		 commandLine.addArguments(new String[] {
		            "-c",
		            "java -cp /tmp "+className
		    },false);


		DefaultExecutor executor = new DefaultExecutor();

		PumpStreamHandler psh = new PumpStreamHandler(new CodeVerifier().new CollectingLogOutputStream());
		executor.setStreamHandler(psh);
		ExecuteWatchdog watchdog = new ExecuteWatchdog(10000);
		executor.setWatchdog(watchdog);
		int exitValue = executor.execute(commandLine);
		
	}
	
	public class CollectingLogOutputStream extends LogOutputStream {
	    private final List<String> lines = new LinkedList<String>();
	    @Override protected void processLine(String line, int level) {
	        lines.add(line);
	        System.out.println(line);

	    }   
	    public List<String> getLines() {
	        return lines;
	    }
	}
	

	
	

}
