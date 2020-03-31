package com.cyse6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.cyse6225.spring2020.courseservice.datamodel.Course;
import com.cyse6225.spring2020.courseservice.datamodel.DynamoDbConnector;
import com.cyse6225.spring2020.courseservice.datamodel.Program;
import com.cyse6225.spring2020.courseservice.exception.DataNotFoundException;

public class ProgramService {
	
	private static ProgramService instance;
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper; 
	
	public static ProgramService getInstance() {
		if(instance == null) {
			instance = new ProgramService();
		}
		return instance;
	}
	
	public ProgramService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	//Add program
	public void addProgram(String progId, String progName, List<String> courseId) {
		Program newProg = new Program(progId, progName, courseId);
		mapper.save(newProg);
	}
	
	//Delete program
	public Program deleteProgram(String progId) {
		Program oldProg = mapper.load(Program.class, progId);
		if(oldProg == null) {
			throw new DataNotFoundException(" Program details with id '"+ progId +"' not found");
		}
		mapper.delete(oldProg);
		return oldProg;
	}
	
	//update program
	public Program updateProgram(String progId, Program program) {
		Program oldProg = mapper.load(Program.class, progId);
		if(oldProg != null && program.getProgramId().equals(progId)){
			mapper.save(program);
			return program;
		}else {
			throw new DataNotFoundException(" Program details with id '"+ progId +"' not found");
		}
	}
	
	//get all programs
	public List<Program> getAllPrograms(){
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Program> progList = mapper.scan(Program.class, scanExpression);		
		if(progList == null) {
			throw new DataNotFoundException(" Program details not found");
		}
		return progList;
	}
	
	//get program by id
	public Program getProgrambyId(String progId) {
		Program prog = mapper.load(Program.class, progId);
		if(prog == null) {
			throw new DataNotFoundException(" Program details with id '"+ progId +"' not found");
		}
		return prog;
	}
	
	//get all courses for a program
	public List<Course> getCourseForProgram(String progId){
		Program prog = mapper.load(Program.class, progId);
		List<Course> courseList = new ArrayList<Course>();
		List<String> courseId = prog.getCourseId();
		for(String id : courseId) {
			courseList.add(CourseService.getInstance().getCoursebyId(id));
		}
		return courseList;
	}	
}
