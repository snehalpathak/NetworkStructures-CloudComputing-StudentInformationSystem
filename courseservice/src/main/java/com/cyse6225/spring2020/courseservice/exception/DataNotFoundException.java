package com.cyse6225.spring2020.courseservice.exception;

public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6741470999993757687L;

	public DataNotFoundException(String message) {
		super(message);
	}
}
