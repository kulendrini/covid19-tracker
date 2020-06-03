package com.wiley.covid19tracker.service;

import com.wiley.covid19tracker.dto.ChartDataDTO;
import com.wiley.covid19tracker.dto.CovidStatisticalDTO;
import com.wiley.covid19tracker.dto.OperationData;

public interface CovidStatisticalApiService {

	OperationData<CovidStatisticalDTO> getCovidStatisticalInfo();
	
	OperationData<?> updateChartData(ChartDataDTO chartDataDTO);
}
