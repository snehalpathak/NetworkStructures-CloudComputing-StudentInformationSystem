package com.cyse6225.spring2020.courseservice.datamodel;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "lecture")
public class Lecture {

	private String lectureId;
	private String classroom;
	private String assignments;
	private String studyMaterials;

	public Lecture() {

	}

	public Lecture(String lectureId, String classroom, String assignments, String studyMaterials) {
		this.lectureId = lectureId;
		this.classroom = classroom;
		this.assignments = assignments;
		this.studyMaterials = studyMaterials;
	}

	@DynamoDBHashKey(attributeName = "lectureId")
	public String getLectureId() {
		return lectureId;
	}

	public void setLectureId(String lectureId) {
		this.lectureId = lectureId;
	}

	@DynamoDBAttribute(attributeName = "classroom")
	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	@DynamoDBAttribute(attributeName = "assignments")
	public String getAssignments() {
		return assignments;
	}

	public void setAssignments(String assignments) {
		this.assignments = assignments;
	}

	@DynamoDBAttribute(attributeName = "studyMaterials")
	public String getStudyMaterials() {
		return studyMaterials;
	}

	public void setStudyMaterials(String studyMaterials) {
		this.studyMaterials = studyMaterials;
	}

	@DynamoDBIgnore
	@Override
	public String toString() {
		return "lectureId=" + lectureId + ", classroom=" + classroom + ", assignments=" + assignments
				+ ", studyMaterials=" + studyMaterials;
	}

}
