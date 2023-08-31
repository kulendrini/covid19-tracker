package com.app.covid.tracker.dto;

public class OperationStatus {

	private String status;
	private int statusCode;	
	private String reason;

	public OperationStatus() { }

	public OperationStatus(String status, int statusCode) {
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "OperationStatus [status=" + status + ", statusCode=" + statusCode + ", reason=" + reason + "]";
	}
	
}
