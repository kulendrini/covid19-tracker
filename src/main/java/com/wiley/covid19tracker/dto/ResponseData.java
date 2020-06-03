package com.wiley.covid19tracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class ResponseData {

	private static final long serialVersionUID = 1L;
	private boolean success;
	private String message;
	private CovidStatisticalDTO statistical;
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public CovidStatisticalDTO getData() {
		return statistical;
	}
	
	public void setData(CovidStatisticalDTO statistical) {
		this.statistical = statistical;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "ResponseData [success=" + success + ", message=" + message + ", statistical=" + statistical + "]";
	}
	
}
