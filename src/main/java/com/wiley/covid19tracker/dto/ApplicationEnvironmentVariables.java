package com.wiley.covid19tracker.dto;

public class ApplicationEnvironmentVariables {

	// health gov related
	private String healthgovCoronavirusUrl;

	public String getHealthgovCoronavirusUrl() {
		return healthgovCoronavirusUrl;
	}

	public void setHealthgovCoronavirusUrl(String healthgovCoronavirusUrl) {
		this.healthgovCoronavirusUrl = healthgovCoronavirusUrl;
	}

	@Override
	public String toString() {
		return "ApplicationEnvironmentVariables [healthgovCoronavirusUrl=" + healthgovCoronavirusUrl + "]";
	}
	
	
}
