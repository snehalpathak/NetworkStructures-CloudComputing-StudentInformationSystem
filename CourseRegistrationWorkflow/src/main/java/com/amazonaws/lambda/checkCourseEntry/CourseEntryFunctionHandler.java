package com.amazonaws.lambda.checkCourseEntry;

import java.util.List;

import com.amazonaws.lambda.datamodel.Course;
import com.amazonaws.lambda.datamodel.DynamoDbConnector;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.util.CollectionUtils;
import com.amazonaws.util.StringUtils;

public class CourseEntryFunctionHandler implements RequestHandler<CourseEntryRequest, Boolean> {

	private DynamoDBMapper mapper;
	private static DynamoDbConnector dynamoDb;

	public CourseEntryFunctionHandler() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	@Override
	public Boolean handleRequest(CourseEntryRequest input, Context context) {

		if (StringUtils.isNullOrEmpty(input.getCourseId()) || StringUtils.isNullOrEmpty(input.getCourseName())
				|| StringUtils.isNullOrEmpty(input.getDepartment())
				|| StringUtils.isNullOrEmpty(input.getProfessorId())) {
			return false;
		}

		if (!isCourseExists(input.getCourseId())) {
			if (StringUtils.isNullOrEmpty(input.getBoardId()) && StringUtils.isNullOrEmpty(input.getNotificationTopic())
					&& CollectionUtils.isNullOrEmpty(input.getStudentList())) {
				return true;
			}
		}

		return false;
	}

	public boolean isCourseExists(String gsi) {

		Course course = new Course();
		course.setCourseId(gsi);

		final DynamoDBQueryExpression<Course> queryExpression = new DynamoDBQueryExpression<Course>()
				.withIndexName("courseId-index").withConsistentRead(false).withHashKeyValues(course);

		List<Course> list = mapper.query(Course.class, queryExpression);

		if (list.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
}
