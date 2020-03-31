package com.cyse6225.spring2020.courseservice.service;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.cyse6225.spring2020.courseservice.datamodel.Course;
import com.cyse6225.spring2020.courseservice.datamodel.DynamoDbConnector;
import com.cyse6225.spring2020.courseservice.datamodel.Student;
import com.cyse6225.spring2020.courseservice.exception.DataNotFoundException;

public class StudentService {

	private static StudentService instance;
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper; 
	
	public static StudentService getInstance() {
		if (instance == null) {
			instance = new StudentService();
		}
		return instance;
	}

	public StudentService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	//Add student
	public void addStudent(String name, String email, String program, List<String> courseId) {
		
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		Integer count = mapper.count(Student.class, scanExpression) + 1;
		String studentId = count.toString();
		Student newStudent = new Student(studentId, name, email, program, courseId);
		
		mapper.save(newStudent);
	}

	//Delete Student
	public Student deleteStudent(String studentId) {
		Student oldStudent = mapper.load(Student.class, studentId);
		if(oldStudent == null) {
			throw new DataNotFoundException(" Student details with id '"+ studentId +"' not found");
		}
		mapper.delete(oldStudent);
		return oldStudent;
	}

	//Get student by ID
	public Student getStudentById(String id) {
		Student student = mapper.load(Student.class, id);
		if(student == null) {
			throw new DataNotFoundException(" Student details with id '"+ id +"' not found");
		}
		return student;
	}

	//Get all students
	public List<Student> getAllStudents() {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Student> list = mapper.scan(Student.class, scanExpression);
		if(list == null) {
			throw new DataNotFoundException(" Student details not found");
		}
		return list;
	}

	//Get Student by Program
	public List<Student> getStudentbyProgram(String programId) {		
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(programId));
		
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("programId = :v1")
			    .withExpressionAttributeValues(eav);
		
		List<Student> studentList = mapper.scan(Student.class, scanExpression);
		if(studentList == null) {
			throw new DataNotFoundException(" Student details with Program Id '"+ programId +"' not found");
		}
		return studentList;
	}

	//Update Student
	public Student updateStudent(String id, Student student) {
		Student oldStudent = mapper.load(Student.class, id);
		if(oldStudent != null) {
			student.setStudentId(id);
			mapper.save(student);
			return student;
		}else{
			throw new DataNotFoundException(" Student details with id '"+ id +"' not found");
		}
	}

	// get course list for student
	public List<Course> getCourseListForStudent(String studentId) {
		Student student = mapper.load(Student.class, studentId);
		List<String> courseId = student.getCourseId();
		List<Course> courseList = CourseService.getInstance().getCoursesByCourseId(courseId);
		if(courseList == null) {
			throw new DataNotFoundException(" Student details with id '"+ studentId +"' not found");
		}
		return courseList;
	}
}
