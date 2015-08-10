package com.basic.CodeEvaluator.common;

import com.basic.CodeEvaluator.bean.InputParameterBean;
import com.basic.CodeEvaluator.bean.ParameterBean;

public class GeneralUtility {

	public static ParameterBean getParameterMapFromCommandLineArgs(String args[])
	{
		
		
		InputParameterBean inputParameterBean=new InputParameterBean(args[0], args[1], args[2], 
				new Long(args[3]), new Long(args[4]), args[5]);
		
		ParameterBean paramBean=new ParameterBean(inputParameterBean);

		return paramBean;
	}
	
}
