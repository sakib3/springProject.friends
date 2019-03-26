package com.springProject.friends.util;

public class FieldErrorMessage {
	public FieldErrorMessage(String field, String message) {
		this.setField(field);
		this.setMessage(message);
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String field;
	private String message;
}
