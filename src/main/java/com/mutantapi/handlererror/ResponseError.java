package com.mutantapi.handlererror;

public class ResponseError {

	private String message;

	public ResponseError(final String message) {
		this.message = message;
	}

	public ResponseError(Exception e) {
		this.message = e.getMessage();
	}

	public String getMessage() {
		return this.message;
	}
}
