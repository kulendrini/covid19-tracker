package com.app.covid.tracker.constant;

public enum Status {

	SUCCESS("SUCCESS", 200), FAILURE("FAILURE", 500), BAD_REQUEST("BAD REQUEST DATA", 400),
	FORBIDDEN("FORBIDDEN", 403), APPROVED("APPROVED", 201), NOT_APPROVED("NOT APPROVED", 411), 
	TRY_AGAIN("TRY AGAIN WITH SAME REFERENCE CODE.", 321), TRY_AGAIN_LATER("TRY AGAIN LATER", 421),
	REQUEST_IN_PROGRESS("REQUEST ALREADY IN PROGRESS", 521);

	private String status;
	private int statusCode;

	Status(String status, int statusCode) {
		this.status = status;
		this.statusCode = statusCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
