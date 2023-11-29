package com.hotelmanagementsystem.dao;

import org.springframework.http.HttpStatus;

public class ErrorMessage 
{
	private HttpStatus httpStatus;
    private String message;
	public ErrorMessage(HttpStatus httpStatus, String message) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
	}
	public ErrorMessage() {
		super();
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
 

}
