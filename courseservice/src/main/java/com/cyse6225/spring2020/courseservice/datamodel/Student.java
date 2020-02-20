package com.cyse6225.spring2020.courseservice.datamodel;

import java.util.List;

public class Student {

	private String studentId;
	private String name;
	private String email;
	private String programId;
	private List<String> courseId;

	public Student() {

	}

	public Student(String studentId, String name, String email, String programId, List<String> courseId) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.email = email;
		this.programId = programId;
		this.courseId = courseId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public List<String> getCourseId() {
		return courseId;
	}

	public void setCourseId(List<String> courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "studentId=" + studentId + ", name=" + name + ", email=" + email + ", programId=" + programId
				+ ", courseId=" + courseId;
	}
}
