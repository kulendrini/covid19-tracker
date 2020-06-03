package com.wiley.covid19tracker.service;

import com.wiley.covid19tracker.dto.CovidStatisticalDTO;
import com.wiley.covid19tracker.dto.OperationData;
import com.wiley.covid19tracker.entity.CovidStatistical;

public interface CovidStatisticalService {
	
	public CovidStatisticalDTO getCovidStatisticalInfo();

	public CovidStatistical updateCovidStatistical();
	
}
