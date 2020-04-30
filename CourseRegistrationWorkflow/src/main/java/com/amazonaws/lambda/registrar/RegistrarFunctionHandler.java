package com.amazonaws.lambda.registrar;

import com.amazonaws.lambda.datamodel.DynamoDbConnector;
import com.amazonaws.lambda.datamodel.Registrar;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class RegistrarFunctionHandler implements RequestHandler<RegistrarRequest, String> {

	private DynamoDBMapper mapper;
	private static DynamoDbConnector dynamoDb;
		
	public RegistrarFunctionHandler() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	@Override
	public String handleRequest(RegistrarRequest input, Context context) {

		//create registrar		
		Registrar registrar = new Registrar(input.getCourseId(), "Course", input.getDepartment(), 10);

		mapper.save(registrar);
		return registrar.getRegistrationId();
	}
	
	
}
