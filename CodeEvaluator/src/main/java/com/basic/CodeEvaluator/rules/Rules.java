package com.basic.CodeEvaluator.rules;

import com.basic.CodeEvaluator.bean.ParameterBean;
import com.basic.CodeEvaluator.exception.MismatchException;

public interface Rules {

	public boolean isRuleApplicable();
	
	public boolean isRuleSuccessFull (ParameterBean parametersBean) throws Exception;
	
	public String name();
	
	default void throwException(String reason) throws MismatchException
	{
		throw new MismatchException(getReasonString(reason));
	}
	
	default void throwException(String reason,Throwable e) throws MismatchException
	{
		throw new MismatchException(getReasonString(reason),e);
	}
	
	default String getReasonString(String reason)
	{
		return "Error while running validator "+name()+" : reason :  "+reason;
	}
}
