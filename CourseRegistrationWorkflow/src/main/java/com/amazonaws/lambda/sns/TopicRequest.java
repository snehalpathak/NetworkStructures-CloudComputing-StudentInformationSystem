package com.amazonaws.lambda.sns;

public class TopicRequest {
	private String generatedCourseId;
	private String email;

	public String getGeneratedCourseId() {
		return generatedCourseId;
	}

	public void setGeneratedCourseId(String generatedCourseId) {
		this.generatedCourseId = generatedCourseId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
