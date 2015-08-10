package com.basic.CodeEvaluator;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.basic.CodeEvaluator.bean.ParameterBean;
import com.basic.CodeEvaluator.common.GeneralUtility;
import com.basic.CodeEvaluator.dao.ResultsDAO;
import com.basic.CodeEvaluator.rules.RuleGenerator;

public class CodeVerifier {

	public static void main(String[] args) throws Exception {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ParameterBean paramBean=null;
		try {
			paramBean = GeneralUtility.getParameterMapFromCommandLineArgs(args);
		} catch (Exception e) {
			e.printStackTrace();
			throw e; 
		}
		ResultsDAO resultDao = applicationContext.getBean(ResultsDAO.class);
		

		try {
			
			RuleGenerator ruleGenerator =applicationContext.getBean(RuleGenerator.class);
			boolean isSuccessFull=ruleGenerator.executeRules(paramBean);
			

			if (isSuccessFull) {
				resultDao.insertResults(paramBean.getInputParameterBean().getSolutionName(), "SUCCESS", null, paramBean.getInputParameterBean().getEventId(),
						paramBean.getInputParameterBean().getProblemName());
				System.out.println("Everything is hunky dory");

			}
		} catch (Exception e) {
			resultDao.insertResults(paramBean.getInputParameterBean().getSolutionName(), "Fail",
					ExceptionUtils.getFullStackTrace(e), paramBean.getInputParameterBean().getEventId(),
					paramBean.getInputParameterBean().getProblemName());
		}

	}

	

}
