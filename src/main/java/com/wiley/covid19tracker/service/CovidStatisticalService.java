package com.wiley.covid19tracker.service;

import com.wiley.covid19tracker.dto.CurrentStatisticalInfo;
import com.wiley.covid19tracker.dto.OperationData;
import com.wiley.covid19tracker.entity.CovidStatistical;

public interface CovidStatisticalService {

	public OperationData<CurrentStatisticalInfo> getCurrentStatistical();
	
	public OperationData<?> saveCurrentStatistical();
	
	public OperationData<?> updateCurrentStatistical();
	
	public OperationData<CovidStatistical> getCurrentStatisticalInfo();
}
