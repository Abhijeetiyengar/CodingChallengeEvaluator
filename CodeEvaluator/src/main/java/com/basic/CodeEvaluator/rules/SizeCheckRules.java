package com.basic.CodeEvaluator.rules;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import com.basic.CodeEvaluator.bean.ParameterBean;

public class SizeCheckRules implements Rules,Enricher {

	@Override
	public boolean isRuleApplicable() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public String name()
	{
		return SizeCheckRules.class.getCanonicalName();
	}

	@Override
	public boolean isRuleSuccessFull(ParameterBean paramBean) throws Exception {
		
		List<String> codeBaseList=FileUtils.readLines(paramBean.getInputParameterBean().getJavaCodeFile());
		
		List<String> finalStringList=codeBaseList.stream().filter(codeLine -> (codeLine!=null && codeLine.trim().length()>0)).collect(Collectors.toList());
		
		Long maxNumberOfLines=null;
		
		if((maxNumberOfLines=paramBean.getInputParameterBean().getMaxNumberOfLines())!=null)
		{
			if(finalStringList.size()>0 && finalStringList.size()<=maxNumberOfLines)
			{
				getPackageName(finalStringList.get(0), paramBean);
				return true;
			}
			else
			{
				throwException(" Max Size is "+maxNumberOfLines+" where as number of lines in code is "+ finalStringList.size());
			}
		}
		return true;
	}
	
	private void getPackageName(String firstLine,ParameterBean paramBean)
	{
		if(firstLine.trim().startsWith("package"))
		{
			String packageName=firstLine.replace("package", "").replace(";", "").trim();
			paramBean.getEvaluatedResultsBean().setPackageNameOfJavaClass(packageName);
		}
		
	}

}
