package com.cyse6225.spring2020.courseservice.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.cyse6225.spring2020.courseservice.datamodel.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		ErrorMessage errMsg = new ErrorMessage(500, exception.getMessage());
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errMsg)
				.build();
	}
}