package com.cyse6225.spring2020.courseservice.datamodel;

public class Course {
	private String courseId;
	private String courseName;
	private String lectureId;
	private String professorId;
	private String courseTA;
	
	public Course() {
		
	}
	
	public Course(String courseId, String courseName, String lectureId, String professorId, String courseTa) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.lectureId = lectureId;
		this.professorId = professorId;
		this.courseTA = courseTa; 
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getLectureId() {
		return lectureId;
	}

	public void setLectureId(String lectureId) {
		this.lectureId = lectureId;
	}

	public String getProfessorId() {
		return professorId;
	}

	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}

	public String getCourseTA() {
		return courseTA;
	}

	public void setCourseTA(String courseTA) {
		this.courseTA = courseTA;
	}

	@Override
	public String toString() {
		return "courseId=" + courseId + ", courseName=" + courseName + ", lectureId=" + lectureId
				+ ", professorId=" + professorId + ", courseTA=" + courseTA;
	}
	
}
