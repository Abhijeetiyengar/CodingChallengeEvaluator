package com.basic.CodeEvaluator.exception;

@SuppressWarnings("serial")
public class MismatchException extends Exception {
	
	public MismatchException(String exceptionReason)
	{
		super(exceptionReason);
	}
	
	public MismatchException(String exceptionReason,Throwable e)
	{
		super(exceptionReason,e);
	}

}
