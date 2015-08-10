package com.basic.CodeEvaluator.bean;


public class ParameterBean {
	
	private InputParameterBean inputParameterBean;
	
	private EvaluatedResultsBean evaluatedResultsBean=new EvaluatedResultsBean();
	
	public InputParameterBean getInputParameterBean() {
		return inputParameterBean;
	}
	
	
	public ParameterBean(InputParameterBean inputParameterBean) {
		super();
		this.inputParameterBean = inputParameterBean;
		this.evaluatedResultsBean=new EvaluatedResultsBean();
	}
	public EvaluatedResultsBean getEvaluatedResultsBean() {
		return evaluatedResultsBean;
	}
	

}
