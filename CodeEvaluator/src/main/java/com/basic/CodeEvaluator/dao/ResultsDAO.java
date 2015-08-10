package com.basic.CodeEvaluator.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.basic.CodeEvaluator.common.DateUtil;
import com.jooq.database.Tables;

@Repository
public class ResultsDAO {
	
	@Autowired
	DSLContext dslContext;
	

	public void insertResults(String userName,String result,String exception,Long eventId,String problemName) throws Exception
	{
		dslContext.insertInto(Tables.USERSOLUTIONRESULTS,Tables.USERSOLUTIONRESULTS.NAME,Tables.USERSOLUTIONRESULTS.PASSED,Tables.USERSOLUTIONRESULTS.ERROR,
				Tables.USERSOLUTIONRESULTS.TIMESTAMP,Tables.USERSOLUTIONRESULTS.EVENTID,Tables.USERSOLUTIONRESULTS.PROBLEMNAME).values(userName,result,exception,DateUtil.getCurrentTimeinString(),eventId,problemName).execute();
	}
	
	

}
