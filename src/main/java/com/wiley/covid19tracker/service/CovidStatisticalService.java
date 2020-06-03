package com.wiley.covid19tracker.service;

import java.util.HashMap;

public interface CovidStatisticalService {
	
	public HashMap getCovidStatisticalInfo();

	public void updateCovidStatistical();
	
	public void updateChartData();
	
}
