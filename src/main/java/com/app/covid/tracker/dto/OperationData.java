package com.app.covid.tracker.dto;

public class OperationData <T> {

	private T data;
	private OperationStatus status;
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public OperationStatus getStatus() {
		return status;
	}
	public void setStatus(OperationStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "OperationData [data=" + data + ", status=" + status + "]";
	}
}
