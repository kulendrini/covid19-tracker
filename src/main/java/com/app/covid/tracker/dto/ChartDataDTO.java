package com.app.covid.tracker.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class ChartDataDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String month;
	private int total;
	private int active;
	
	public ChartDataDTO() {	}
	
	public ChartDataDTO(String month, int total, int active) {
		super();
		this.month = month;
		this.total = total;
		this.active = active;
	}

	public String getMonth() {
		return month;
	}
	
	public void setMonth(String month) {
		this.month = month;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getActive() {
		return active;
	}
	
	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "ChartDataDTO [month=" + month + ", total=" + total + ", active=" + active + "]";
	}
	
}
