package com.amazonaws.lambda.board;

import com.amazonaws.lambda.datamodel.Board;
import com.amazonaws.lambda.datamodel.Course;
import com.amazonaws.lambda.datamodel.DynamoDbConnector;
import com.amazonaws.lambda.datamodel.Professor;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.util.StringUtils;

public class BoardFunctionHandler implements RequestHandler<BoardRequest, String> {

	private DynamoDBMapper mapper; 
	private static DynamoDbConnector dynamoDb;
		
	public BoardFunctionHandler() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
    @Override
    public String handleRequest(BoardRequest input, Context context) {
        	String courseId = input.getGeneratedCourseId();
        	Course course = mapper.load(Course.class, courseId);
        	//save board details
        	String boardId = saveBoardDetails(courseId);  	
        	//save board id to course
        	saveBoardIdToCourse(course, boardId);
        	//fetch professor details
        	String email = getProfessorEmail(input.getProfessorId());
        	
        return StringUtils.isNullOrEmpty(email) ? "" : email;
    }

    public void saveBoardIdToCourse(Course course, String boardId) {
    	course.setBoardId(boardId);
    	mapper.save(course);
    }
        
    public String saveBoardDetails(String courseId) {
    	Board board = new Board(courseId,"Welcome");
    	mapper.save(board);
    	return board.getBoardId();
    }
    
    public String getProfessorEmail(String profId) {
    	Professor prof = mapper.load(Professor.class, profId);
    	return prof.getEmail();
    }

}
