package com.wiley.covid19tracker.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class CurrentStatisticalInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int local_total_cases;
	private int local_recovered;
	private int local_active_cases;
	private int local_deaths;
	
	public int getLocal_total_cases() {
		return local_total_cases;
	}
	
	public void setLocal_total_cases(int local_total_cases) {
		this.local_total_cases = local_total_cases;
	}
	
	public int getLocal_recovered() {
		return local_recovered;
	}
	
	public void setLocal_recovered(int local_recovered) {
		this.local_recovered = local_recovered;
	}
	
	public int getLocal_active_cases() {
		return local_active_cases;
	}
	
	public void setLocal_active_cases(int local_active_cases) {
		this.local_active_cases = local_active_cases;
	}
	
	public int getLocal_deaths() {
		return local_deaths;
	}
	
	public void setLocal_deaths(int local_deaths) {
		this.local_deaths = local_deaths;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CurrentStatisticalInfo [local_total_cases=" + local_total_cases + ", local_recovered=" + local_recovered
				+ ", local_active_cases=" + local_active_cases + ", local_deaths=" + local_deaths + "]";
	}
	
}
