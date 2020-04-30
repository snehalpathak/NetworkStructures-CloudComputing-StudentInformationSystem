package com.amazonaws.lambda.board;

public class BoardResponse {
	private String email;
	
	public BoardResponse(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
