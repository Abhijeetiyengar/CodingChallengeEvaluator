package com.test.codeEvaluator;

import org.junit.Test;

import com.basic.CodeEvaluator.CodeVerifier;

public class TestCodeEvaluator {

	@Test
	public void testWithPackage()
	{
		String[] pasingargs={"src/test/java/com/test/codeEvaluator/HelloWorld.java",
				"src/test/resources/input.txt",
				"src/test/resources/output.txt",
				"20","1","Test"};
		
		try {
			CodeVerifier.main(pasingargs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() throws Exception 
	{

		String[] pasingargs={"src/test/java/HelloWorldNoPackage.java",
				"src/test/resources/input.txt",
				"src/test/resources/output.txt",
				"20","1","Test"};
		
		try {
			CodeVerifier.main(pasingargs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
}
