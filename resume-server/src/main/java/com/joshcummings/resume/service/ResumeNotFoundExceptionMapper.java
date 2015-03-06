package com.joshcummings.resume.service;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.springframework.stereotype.Component;

import com.joshcummings.resume.model.ResumeNotFoundException;

@Component
public class ResumeNotFoundExceptionMapper implements ExceptionMapper<ResumeNotFoundException> {

	public Response toResponse(ResumeNotFoundException arg0) {
		return Response.status(404).build();
	}

}
