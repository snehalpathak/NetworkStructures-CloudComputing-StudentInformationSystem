package com.cyse6225.spring2020.courseservice.datamodel;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

	private int errorCode;
	private String errMessage;
	
	public ErrorMessage() {
		
	}
	
	public ErrorMessage(int errorCode, String errMessage) {
		this.errorCode = errorCode;
		this.errMessage = errMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
}
