package com.cyse6225.spring2020.courseservice.datamodel;

import java.util.List;

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

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public List<String> getCourseId() {
		return courseId;
	}

	public void setCourseId(List<String> courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "programId=" + programId + ", programName=" + programName + ", courseId=" + courseId;
	}		
}
