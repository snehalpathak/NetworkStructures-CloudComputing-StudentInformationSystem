package com.amazonaws.lambda.board;

public class BoardRequest {

	private String generatedCourseId;
	private String professorId;
	
	public String getGeneratedCourseId() {
		return generatedCourseId;
	}

	public void setGeneratedCourseId(String generatedCourseId) {
		this.generatedCourseId = generatedCourseId;
	}

	public String getProfessorId() {
		return professorId;
	}

	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}
	
}
