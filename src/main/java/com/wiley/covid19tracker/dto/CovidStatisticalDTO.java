package com.wiley.covid19tracker.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class CovidStatisticalDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("local_total_cases")
	private int localTotalCases;
	
	@JsonProperty("local_recovered")
	private int localRecovered;
	
	@JsonProperty("local_active_cases")
	private int localActiveCases;
	
	@JsonProperty("local_deaths")
	private int localDeaths;

	public int getLocalTotalCases() {
		return localTotalCases;
	}

	public void setLocalTotalCases(int localTotalCases) {
		this.localTotalCases = localTotalCases;
	}

	public int getLocalRecovered() {
		return localRecovered;
	}

	public void setLocalRecovered(int localRecovered) {
		this.localRecovered = localRecovered;
	}

	public int getLocalActiveCases() {
		return localActiveCases;
	}

	public void setLocalActiveCases(int localActiveCases) {
		this.localActiveCases = localActiveCases;
	}

	public int getLocalDeaths() {
		return localDeaths;
	}

	public void setLocalDeaths(int localDeaths) {
		this.localDeaths = localDeaths;
	}

	@Override
	public String toString() {
		return "CovidStatisticalInfo [localTotalCases=" + localTotalCases + ", localRecovered=" + localRecovered
				+ ", localActiveCases=" + localActiveCases + ", localDeaths=" + localDeaths + "]";
	}
	
}
