package com.amazonaws.lambda.course;

import com.amazonaws.lambda.datamodel.Course;
import com.amazonaws.lambda.datamodel.DynamoDbConnector;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CourseFunctionHandler implements RequestHandler<CourseRequest, String> {

	private DynamoDBMapper mapper;
	private static DynamoDbConnector dynamoDb;
		
	public CourseFunctionHandler() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	@Override
	public String handleRequest(CourseRequest input, Context context) {
		
		Course course = new Course(input.getCourseId(), input.getCourseName(), input.getProfessorId(), input.getTaId(),
				input.getDepartment());
		mapper.save(course);
		
		return course.getId();
	}
}
