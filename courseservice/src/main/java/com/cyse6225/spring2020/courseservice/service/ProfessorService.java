package com.cyse6225.spring2020.courseservice.service;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.cyse6225.spring2020.courseservice.datamodel.DynamoDbConnector;
import com.cyse6225.spring2020.courseservice.datamodel.Professor;
import com.cyse6225.spring2020.courseservice.exception.DataNotFoundException;

public class ProfessorService {

	private static ProfessorService instance;
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper;

	public static ProfessorService getInstance() {
		if (instance == null) {
			instance = new ProfessorService();
		}
		return instance;
	}

	public ProfessorService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	// Getting a list of all professor
	public List<Professor> getAllProfessors() {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Professor> profList = mapper.scan(Professor.class, scanExpression);
		if(profList == null) {
			throw new DataNotFoundException(" Professor details not found");
		}
		return profList;
	}

	// Adding a professor
	public void addProfessor(String firstName, String lastName, String department,
			String joiningDate) {	
		
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		Integer count = mapper.count(Professor.class, scanExpression) + 1;
		String professorId = count.toString();
		// Create a Professor Object
		Professor prof = new Professor(professorId, firstName, lastName, department, joiningDate.toString());
		mapper.save(prof);
	}

	// Getting One Professor
	public Professor getProfessor(String profId) {
		Professor profDetails = mapper.load(Professor.class, profId);
		if(profDetails == null) {
			throw new DataNotFoundException(" Professor details with id '"+ profId +"' not found");
		}
		return profDetails;
	}

	// Deleting a professor
	public Professor deleteProfessor(String profId) {
		Professor deletedProfDetails = mapper.load(Professor.class, profId);
		if(deletedProfDetails == null) {
			throw new DataNotFoundException(" Professor details with id '"+ profId +"' not found");
		}
		mapper.delete(deletedProfDetails);
		return deletedProfDetails;
	}

	// Updating Professor Info
	public Professor updateProfessorInformation(String profId, Professor prof) {
		Professor oldProf = mapper.load(Professor.class, profId);
		if (oldProf != null) {
			prof.setProfessorId(profId);
			mapper.save(prof);
			return prof;
		}else {
			throw new DataNotFoundException(" Professor details with id '"+ profId +"' not found");
		}
	}

	// Get professors in a department
	public List<Professor> getProfessorsByDepartment(String department) {
		// Getting the list
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(department));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("department = :v1")
				.withExpressionAttributeValues(eav);

		List<Professor> profList = mapper.scan(Professor.class, scanExpression);
		if(profList == null) {
			throw new DataNotFoundException(" Professor details for department '"+ department +"' not found");
		}
		return profList;
	}
}
