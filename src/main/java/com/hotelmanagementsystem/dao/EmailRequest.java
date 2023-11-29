package com.hotelmanagementsystem.dao;

public class EmailRequest 
{
	private String customername;
	private String from; 
	private String subject;
	private String body;
	public EmailRequest() {
		super();
	}
	
	public EmailRequest(String customername, String from,  String subject, String body) {
		super();
		this.customername = customername;
		this.setFrom(from); 
		this.subject = subject;
		this.body = body;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	 
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	 
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	@Override
	public String toString() {
		return "EmailRequest [customername=" + customername + ", from=" + from + ", subject=" + subject
				+ ", body=" + body + "]";
	}
	
	
}