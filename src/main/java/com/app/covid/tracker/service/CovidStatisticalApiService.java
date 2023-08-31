package com.app.covid.tracker.service;

import com.app.covid.tracker.dto.ChartDataDTO;
import com.app.covid.tracker.dto.CovidStatisticalDTO;
import com.app.covid.tracker.dto.OperationData;

public interface CovidStatisticalApiService {

	OperationData<CovidStatisticalDTO> getCovidStatisticalInfo();
	
	OperationData<?> updateChartData(ChartDataDTO chartDataDTO);
}
