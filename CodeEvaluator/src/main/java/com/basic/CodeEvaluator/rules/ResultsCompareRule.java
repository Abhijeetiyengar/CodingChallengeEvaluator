package com.basic.CodeEvaluator.rules;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.LogOutputStream;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.FileUtils;

import com.basic.CodeEvaluator.bean.ParameterBean;
import com.basic.CodeEvaluator.common.DateUtil;
import com.basic.CodeEvaluator.common.StandaloneCodeCompilerAndExecutor;


public class ResultsCompareRule implements Rules {

	@Override
	public boolean isRuleApplicable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String name()
	{
		return ResultsCompareRule.class.getCanonicalName();
	}
	
	@Override
	public boolean isRuleSuccessFull(ParameterBean paramBean) throws Exception {
		
		StandaloneCodeCompilerAndExecutor compiler = new StandaloneCodeCompilerAndExecutor();
		String className = compiler.compileJavaObject(paramBean.getInputParameterBean().getJavaCodeFile(),paramBean);

		
		List<String> outputResultList = FileUtils.readLines(
				paramBean.getInputParameterBean().getOutputFile());
		List<String> inputList = FileUtils.readLines(
				paramBean.getInputParameterBean().getInputFile());


		ProcessCommandProcessor commandProcessor = new ProcessCommandProcessor(
				className,paramBean.getEvaluatedResultsBean().getPackageNameOfJavaClass());

		int inputIndex = 0;


		for (String ipString : inputList) {
			 commandProcessor.accept(ipString,
					outputResultList.get(inputIndex++));
		}
		
		return true;
	}
	
	public class ProcessCommandProcessor {

		String className;
		String packageName;

		public ProcessCommandProcessor(String className,String packageName) {
			this.className = className;
			this.packageName=packageName;

		}
		
		private String getClassName()
		{
			if(packageName!=null && packageName.length()>0)
			{
				return packageName+"."+className;
			}
			else
				return className;
		}

		// @Override
		public boolean accept(String t, String outputIndexString)
				throws Exception {
			CommandLine commandLine = new CommandLine("/bin/sh");
			commandLine.addArguments(new String[] { "-c",
					"java -cp /tmp/codeEvaluation/"+DateUtil.getCurrentTimeinStringddMMyyyyHHmmss()+" " + getClassName() + " " + t }, false);

			DefaultExecutor executor = new DefaultExecutor();
			List<String> outputLineList = new ArrayList<String>();
			PumpStreamHandler psh = new PumpStreamHandler(
					new CollectingLogOutputStream(
							outputLineList));
			executor.setStreamHandler(psh);
			ExecuteWatchdog watchdog = new ExecuteWatchdog(10000);
			executor.setWatchdog(watchdog);
			try {
				 executor.execute(commandLine);
				if (outputLineList.get(0).equals(outputIndexString))
					return true;
				else
					throwException(
							"Exception occured while matching i/p " + t
									+ " and output " + outputIndexString);
			} catch (Exception e) {
				throwException(
						"Exception occured while matching i/p " + t
								+ " and output " + outputIndexString,e);
			}
			
			return true;

		}
		
		

	}

	public class CollectingLogOutputStream extends LogOutputStream {

		private List<String> outputLineList;

		CollectingLogOutputStream(List<String> outputLineList) {
			this.outputLineList = outputLineList;
		}

		@Override
		protected void processLine(String line, int level) {
			outputLineList.add(line);

			System.out.println(line);

		}

	}

}
