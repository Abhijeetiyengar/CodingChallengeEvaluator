package com.basic.CodeEvaluator.rules;

import java.util.List;

import com.basic.CodeEvaluator.bean.ParameterBean;

public class RuleGenerator {
	
	List<String> rules;

	public List<String> getRules() {
		return rules;
	}

	public void setRules(List<String> rules) {
		this.rules = rules;
	}
	
	public boolean executeRules(ParameterBean paramterBean) throws Exception
	{
		
		for(String ruleString:rules)
		{
			Rules rule=(Rules)this.getClass().getClassLoader().loadClass(ruleString).newInstance();
			if(rule.isRuleApplicable())
			{
				rule.isRuleSuccessFull(paramterBean);
			}
		}
		
		return true;
	}
	
	

}
