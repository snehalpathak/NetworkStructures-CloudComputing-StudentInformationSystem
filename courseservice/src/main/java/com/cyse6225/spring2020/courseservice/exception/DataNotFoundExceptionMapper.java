package com.cyse6225.spring2020.courseservice.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.cyse6225.spring2020.courseservice.datamodel.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exception) {
		ErrorMessage errMsg = new ErrorMessage(404, exception.getMessage());
		return Response.status(Status.NOT_FOUND)
				.entity(errMsg)
				.build();
	}

}
