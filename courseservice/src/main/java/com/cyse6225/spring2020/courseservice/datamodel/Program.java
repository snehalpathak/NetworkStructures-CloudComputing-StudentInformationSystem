package com.cyse6225.spring2020.courseservice.datamodel;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="program")
public class Program {
	
	private String programId;
	private String programName;
	private List<String> courseId;
	
	public Program() {
		
	}

	public Program(String programId, String programName, List<String> courseId) {
		this.programId = programId;
		this.programName = programName;
		this.courseId = courseId;
	}

	@DynamoDBHashKey(attributeName="programId")
	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	@DynamoDBAttribute(attributeName="programName")
	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	@DynamoDBAttribute(attributeName="courseId")
	public List<String> getCourseId() {
		return courseId;
	}

	public void setCourseId(List<String> courseId) {
		this.courseId = courseId;
	}
	
	@DynamoDBIgnore
	@Override
	public String toString() {
		return "programId=" + programId + ", programName=" + programName + ", courseId=" + courseId;
	}		
}
