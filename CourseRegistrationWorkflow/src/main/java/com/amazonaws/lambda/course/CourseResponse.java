package com.amazonaws.lambda.course;

public class CourseResponse {
	String generatedCourseId;
	
	public CourseResponse(String generatedCourseId) {
		this.generatedCourseId = generatedCourseId;
	}

	public String getGeneratedCourseId() {
		return generatedCourseId;
	}

	public void setGeneratedCourseId(String generatedCourseId) {
		this.generatedCourseId = generatedCourseId;
	}

}
