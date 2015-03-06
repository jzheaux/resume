package com.joshcummings.resume.model;

public class ResumeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResumeNotFoundException(String m, Throwable t) {
		super(m, t);
	}
}
