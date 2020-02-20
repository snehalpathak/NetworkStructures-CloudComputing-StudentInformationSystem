package com.cyse6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cyse6225.spring2020.courseservice.datamodel.Course;
import com.cyse6225.spring2020.courseservice.datamodel.InMemoryDatabase;
import com.cyse6225.spring2020.courseservice.datamodel.Lecture;
import com.cyse6225.spring2020.courseservice.datamodel.Professor;
import com.cyse6225.spring2020.courseservice.datamodel.Student;

public class CourseService {

	private static HashMap<String, Course> courseMap = InMemoryDatabase.getCourseDB();
	private static CourseService instance;

	public static CourseService getInstance() {
		if (instance == null) {
			instance = new CourseService();
		}
		return instance;
	}

	// Add course
	public void addCourse(String courseId, String courseName, String lectureId, String professorId, String courseTa) {
		courseMap.put(courseId, new Course(courseId, courseName, lectureId, professorId, courseTa));
	}

	// Delete course
	public Course deleteCourse(String courseId) {
		Course oldCourse = courseMap.get(courseId);
		courseMap.remove(courseId);
		return oldCourse;
	}

	//Update Course
	public Course updateCourse(String courseId, Course course) {
		if (courseMap.containsKey(courseId)) {
			courseMap.put(courseId, course);
		}
		return course;
	}

	// Getting a list of all professor
	public List<Course> getAllCourses() {
		// Getting the list
		ArrayList<Course> list = new ArrayList<>();
		for (Course prof : courseMap.values()) {
			list.add(prof);
		}
		return list;
	}

	//get course by id
	public Course getCoursebyId(String courseId) {
		return courseMap.get(courseId);
	}

	
	//  Get course TA
	public Student getStudentTAforCourse(String courseId) {
		String studentId = courseMap.get(courseId).getCourseTA();
		return StudentService.getInstance().getStudentById(studentId);
	}

	
	//  Get professor for course
	public Professor getProfessorforCourse(String courseId) {
		String profId = courseMap.get(courseId).getProfessorId();
		return ProfessorService.getInstance().getProfessor(profId);
	}

	
	//  Get lecture details for course
	public Lecture getLectureDetailsforCourse(String courseId) {
		String lectId = courseMap.get(courseId).getLectureId();
		return LectureService.getInstance().getLecturebyId(lectId);
	}
	
	//get course list by id
	public List<Course> getCoursesByCourseId(List<String> courseIds) {
		List<Course> courses = new ArrayList<Course>();
		for (String courseId : courseIds) {
			courses.add(courseMap.get(courseId));
		}
		System.out.println(courses);
		return courses;
	}
}
