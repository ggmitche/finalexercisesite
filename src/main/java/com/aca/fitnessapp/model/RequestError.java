package com.aca.fitnessapp.model;

//this is in the same package as Exercise because the model package is meant for data and objects that need to be passed around
public class RequestError {
	
	private Integer errorId;
	private String message;
	
	public RequestError(Integer errorId, String message) {
		this.errorId = errorId;
		this.message = message;
	}
	
	public Integer getErrorId() {
		return errorId;
	}



	public void setErrorId(Integer errorId) {
		this.errorId = errorId;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}

}
