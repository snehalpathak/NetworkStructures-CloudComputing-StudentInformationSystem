package com.cyse6225.spring2020.courseservice.service;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.cyse6225.spring2020.courseservice.datamodel.DynamoDbConnector;
import com.cyse6225.spring2020.courseservice.datamodel.Lecture;
import com.cyse6225.spring2020.courseservice.exception.DataNotFoundException;

public class LectureService {

	private static LectureService instance;
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper;

	public static LectureService getInstance() {
		if (instance == null) {
			instance = new LectureService();
		}
		return instance;
	}

	public LectureService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	// Add Lecture
	public void addLecture(Lecture lect) {
		
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		Integer count = mapper.count(Lecture.class, scanExpression) + 1;
		String lectId = count.toString();
		Lecture lecture = new Lecture(lectId, lect.getClassroom(), lect.getAssignments(),
				lect.getAssignments());
		mapper.save(lecture);
	}

	// Delete Lecture
	public Lecture deleteLecture(String lectId) {
		Lecture oldLect = mapper.load(Lecture.class, lectId);
		if(oldLect == null) {
			throw new DataNotFoundException(" Lecture details with Id '"+ lectId +"' not found");
		}
		mapper.delete(oldLect);
		return oldLect;
	}

	// Update Lecture
	public Lecture updateLecture(String lectId, Lecture lect) {
		Lecture oldLect = mapper.load(Lecture.class, lectId);
		if (oldLect != null) {
			lect.setLectureId(lectId);
			mapper.save(lect);
			return lect;
		}else{
			throw new DataNotFoundException(" Lecture details with Id '"+ lectId +"' not found");
		}
	}

	// Get all lectures
	public List<Lecture> getAllLecture() {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Lecture> lectList = mapper.scan(Lecture.class, scanExpression);
		if(lectList == null) {
			throw new DataNotFoundException(" Lecture details not found");
		}
		return lectList;
	}

	// Get lecture by id
	public Lecture getLecturebyId(String lectId) {
		Lecture lect = mapper.load(Lecture.class, lectId);
		if(lect == null) {
			throw new DataNotFoundException(" Lecture details with Id '"+ lectId +"' not found");
		}
		return lect;
	}
}
