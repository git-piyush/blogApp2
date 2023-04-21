package com.blog.exception;

import org.springframework.http.HttpStatus;

public class ResourceMismatchException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	
	private String message;

	public ResourceMismatchException(HttpStatus status, String message) {
		super(message);
		this.status = status;
		this.message = message;
	}

	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	
	
	
	

}
