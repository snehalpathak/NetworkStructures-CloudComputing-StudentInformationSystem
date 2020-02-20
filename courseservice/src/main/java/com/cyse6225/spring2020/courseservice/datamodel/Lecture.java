package com.cyse6225.spring2020.courseservice.datamodel;

public class Lecture {
	
	private String lectureId;
	private String classroom;
	private String assignments;
	private String studyMaterials;
	
	public Lecture() {
		
	}
	
	public Lecture(String lectureId, String classroom, String assignments,
			String studyMaterials) {
		this.lectureId = lectureId;
		this.classroom = classroom;
		this.assignments = assignments;
		this.studyMaterials = studyMaterials;
	}

	public String getLectureId() {
		return lectureId;
	}

	public String getClassroom() {
		return classroom;
	}

	public String getAssignments() {
		return assignments;
	}

	public String getStudyMaterials() {
		return studyMaterials;
	}
	
	public void setLectureId(String lectureId) {
		this.lectureId = lectureId;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public void setAssignments(String assignments) {
		this.assignments = assignments;
	}

	public void setStudyMaterials(String studyMaterials) {
		this.studyMaterials = studyMaterials;
	}

	@Override
	public String toString() {
		return "lectureId=" + lectureId + ", classroom=" + classroom + ", assignments=" + assignments
				+ ", studyMaterials=" + studyMaterials;
	}
	
}
