package com.basic.CodeEvaluator.bean;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class InputParameterBean {
	
	
	
	String fileName;
	File javaCodeFile;
	String pathForInputFile;
	File inputFile;
	String pathForSolutionFile;
	File solutionFile;
	Long maxNumberOfLines;
	Long eventId;
	String problemName;
	
	public String getSolutionName(){
		return fileName;
	}
	
	public File getJavaCodeFile() {
		return javaCodeFile;
	}
	public File getInputFile() {
		return inputFile;
	}
	public File getOutputFile() {
		return solutionFile;
	}
	public Long getMaxNumberOfLines() {
		return maxNumberOfLines;
	}
	public Long getEventId() {
		return eventId;
	}
	public String getProblemName() {
		return problemName;
	}
	public InputParameterBean(String fileName, String pathForInputFile,
			String outputSolutionFile, Long maxNumberOfLines, Long eventId,
			String problemName) {
		super();
		this.fileName = fileName;
		this.pathForInputFile = pathForInputFile;
		this.pathForSolutionFile = outputSolutionFile;
		this.maxNumberOfLines = maxNumberOfLines;
		this.eventId = eventId;
		this.problemName = problemName;
		validate();
	}
	
	private void validateFile(File file,String fileName)
	{
		if(!file.exists())
		{
			throw new RuntimeException("Cannot open "+fileName); 
		}
		 
	}
	
	private void validate()
	{
		javaCodeFile=FileUtils.getFile(fileName);
		validateFile(javaCodeFile, fileName);
		inputFile=FileUtils.getFile(pathForInputFile);
		validateFile(inputFile, pathForInputFile);		
		solutionFile=FileUtils.getFile(pathForSolutionFile);
		validateFile(solutionFile, pathForSolutionFile);
	}

}
