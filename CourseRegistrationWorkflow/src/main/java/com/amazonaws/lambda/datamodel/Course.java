package com.amazonaws.lambda.datamodel;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Course")
public class Course {
	private String id;
	private String courseId;
	private String courseName;
	private String professorId;
	private String taId;
	private String department;
	private List<String> studentList;
	private String boardId;
	private String notificationTopic;

	public Course() {

	}

	public Course(String courseId, String courseName, String professorId, String taId, String department) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.professorId = professorId;
		this.taId = taId;
		this.department = department;
	}

	@DynamoDBAutoGeneratedKey
	@DynamoDBHashKey(attributeName = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@DynamoDBIndexHashKey(globalSecondaryIndexName = "courseId-index")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@DynamoDBAttribute(attributeName = "courseName")
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@DynamoDBAttribute(attributeName = "professorId")
	public String getProfessorId() {
		return professorId;
	}

	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}

	@DynamoDBAttribute(attributeName = "taId")
	public String getTaId() {
		return taId;
	}

	public void setTaId(String taId) {
		this.taId = taId;
	}

	@DynamoDBAttribute(attributeName = "department")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@DynamoDBAttribute(attributeName = "studentList")
	public List<String> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<String> studentList) {
		this.studentList = studentList;
	}

	@DynamoDBAttribute(attributeName = "boardId")
	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	@DynamoDBAttribute(attributeName = "notificationTopic")
	public String getNotificationTopic() {
		return notificationTopic;
	}

	public void setNotificationTopic(String notificationTopic) {
		this.notificationTopic = notificationTopic;
	}

	@Override
	@DynamoDBIgnore
	public String toString() {
		return "Course [id=" + id + ", courseId=" + courseId + ", courseName=" + courseName + ", professorId="
				+ professorId + ", taId=" + taId + ", department=" + department + ", studentList=" + studentList
				+ ", boardId=" + boardId + ", notificationTopic=" + notificationTopic + "]";
	}
}