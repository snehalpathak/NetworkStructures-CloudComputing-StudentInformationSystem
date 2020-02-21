package com.cyse6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cyse6225.spring2020.courseservice.datamodel.Course;
import com.cyse6225.spring2020.courseservice.datamodel.InMemoryDatabase;
import com.cyse6225.spring2020.courseservice.datamodel.Program;

public class ProgramService {
	
	private static HashMap<String, Program> programMap = InMemoryDatabase.getProgramDB();
	private static ProgramService instance;
	
	public static ProgramService getInstance() {
		if(instance == null) {
			instance = new ProgramService();
		}
		return instance;
	}
	
	public void addProgram(String progId, String progName, List<String> courseId) {
		Program newProg = new Program(progId, progName, courseId);
		programMap.put(progId, newProg);
	}
	
	public Program deleteProgram(String progId) {
		Program oldprog = programMap.get(progId);
		programMap.remove(progId);
		return oldprog;
	}
	
	public Program updateProgram(String progId, Program program) {
		if(programMap.containsKey(progId)) {
			programMap.put(progId, program);
		}
		return program;
	}
	
	//get all programs
	public List<Program> getAllPrograms(){
		List<Program> progList = new ArrayList<>();
		
		for(Program program : programMap.values()) {
			progList.add(program);
		}		
		return progList;
	}
	
	//get program by id
	public Program getProgrambyId(String progId) {
		return programMap.get(progId);
	}
	
	//get all courses for a program
	public List<Course> getCourseForProgram(String progId){
		List<String> courseId = programMap.get(progId).getCourseId();
		List<Course> courseList = new ArrayList<Course>();
		
		for(String id : courseId) {
			courseList.add(CourseService.getInstance().getCoursebyId(id));
		}
		return courseList;
	}	
}
