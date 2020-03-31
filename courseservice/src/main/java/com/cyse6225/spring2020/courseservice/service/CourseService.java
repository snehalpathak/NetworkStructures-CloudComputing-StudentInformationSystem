package com.cyse6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.cyse6225.spring2020.courseservice.datamodel.Course;
import com.cyse6225.spring2020.courseservice.datamodel.DynamoDbConnector;
import com.cyse6225.spring2020.courseservice.datamodel.Lecture;
import com.cyse6225.spring2020.courseservice.datamodel.Professor;
import com.cyse6225.spring2020.courseservice.datamodel.Student;
import com.cyse6225.spring2020.courseservice.exception.DataNotFoundException;

public class CourseService {

	private static CourseService instance;
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper;

	public static CourseService getInstance() {
		if (instance == null) {
			instance = new CourseService();
		}
		return instance;
	}

	public CourseService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	// Add course
	public void addCourse(String courseId, String courseName, String lectureId, String professorId, String courseTa) {
		Course course = new Course(courseId, courseName, lectureId, professorId, courseTa);
		mapper.save(course);
	}

	// Delete course
	public Course deleteCourse(String courseId) {
		Course oldCourse = mapper.load(Course.class, courseId);
		if(oldCourse == null) {
			throw new DataNotFoundException("Course details with Id '"+ courseId +"' not found");
		}
		mapper.delete(oldCourse);
		return oldCourse;
	}

	// Update Course
	public Course updateCourse(String courseId, Course course) {
		Course oldCourse = mapper.load(Course.class, courseId);
		if (course.getCourseId().equals(courseId) && oldCourse != null) {
			mapper.save(course);
			return course;
		}else{
			throw new DataNotFoundException("Course details with Id '"+ courseId +"' not found");
		}
	}

	// Getting a list of all professor
	public List<Course> getAllCourses() {
		// Getting the list
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Course> list = mapper.scan(Course.class, scanExpression);
		if(list == null) {
			throw new DataNotFoundException("Course details not found");
		}
		return list;
	}

	// get course by id
	public Course getCoursebyId(String courseId) {
		Course course = mapper.load(Course.class, courseId);
		if(course == null) {
			throw new DataNotFoundException("Course details with Id '"+ courseId +"' not found");
		}
		return course;
	}

	// Get course TA
	public Student getStudentTAforCourse(String courseId) {
		Course student = mapper.load(Course.class, courseId);
		String studentId = student.getCourseTA();
		return StudentService.getInstance().getStudentById(studentId);
	}

	// Get professor for course
	public Professor getProfessorforCourse(String courseId) {
		Course course = mapper.load(Course.class, courseId);
		String profId = course.getProfessorId();
		return ProfessorService.getInstance().getProfessor(profId);
	}

	// Get lecture details for course
	public Lecture getLectureDetailsforCourse(String courseId) {
		Course course = mapper.load(Course.class, courseId);
		String lectId = course.getLectureId();
		return LectureService.getInstance().getLecturebyId(lectId);
	}

	// get course list by id
	public List<Course> getCoursesByCourseId(List<String> courseIds) {
		List<Course> courses = new ArrayList<Course>();
		for (String courseId : courseIds) {
			courses.add(mapper.load(Course.class, courseId));
		}
		return courses;
	}
}
