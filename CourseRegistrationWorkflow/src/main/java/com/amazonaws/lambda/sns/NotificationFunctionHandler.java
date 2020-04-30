package com.amazonaws.lambda.sns;

import com.amazonaws.lambda.datamodel.Course;
import com.amazonaws.lambda.datamodel.DynamoDbConnector;
import com.amazonaws.lambda.datamodel.SNSConnector;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.util.StringUtils;

public class NotificationFunctionHandler implements RequestHandler<TopicRequest, String> {

	private DynamoDBMapper mapper;
	private static DynamoDbConnector dynamodb;
	private static SNSConnector snsConnector;
	AmazonSNSClient snsClient;

	public NotificationFunctionHandler() {
		snsConnector = new SNSConnector();
		snsConnector.init();
		snsClient = snsConnector.getSNSClient();
		dynamodb = new DynamoDbConnector();
		dynamodb.init();
		mapper = new DynamoDBMapper(dynamodb.getClient());
	}

	@Override
	public String handleRequest(TopicRequest input, Context context) {

		Course course = mapper.load(Course.class, input.getGeneratedCourseId());

		// create topic
		String topicArn = createTopic(course.getCourseName());

		// update course with topic
		updateCourse(course, topicArn);

		if (StringUtils.isNullOrEmpty(input.getEmail())) {
			return sendNoEmailErrMessage();
		}
		// topic subscription
		subscribeToSNSTopic(topicArn, input.getEmail());

		return sendSuccessMessage();
	}

	public void subscribeToSNSTopic(String topicArn, String email) {
		final SubscribeRequest subscribeRequest = new SubscribeRequest(topicArn, "email", email);
		snsClient.subscribe(subscribeRequest);

	}

	public void updateCourse(Course course, String topicArn) {
		course.setNotificationTopic(topicArn);
		mapper.save(course);
	}

	public String createTopic(String topicName) {
		// Create an Amazon SNS topic.
		final CreateTopicRequest createTopicRequest = new CreateTopicRequest(topicName);

		final CreateTopicResult createTopicResponse = snsClient.createTopic(createTopicRequest);

		return createTopicResponse.getTopicArn();
	}

	public String sendSuccessMessage() {
		return "Workflow completed successfully. To confirm the subscription, check your email.";
	}

	public String sendNoEmailErrMessage() {
		return "Workflow terminated: Professor email address is not present. \nSuccessfully registered Course and created SNS Topic for course";
	}
}
