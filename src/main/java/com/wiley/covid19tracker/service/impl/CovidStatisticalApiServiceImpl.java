package com.wiley.covid19tracker.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiley.covid19tracker.constant.Status;
import com.wiley.covid19tracker.dto.ChartDataDTO;
import com.wiley.covid19tracker.dto.CovidStatisticalDTO;
import com.wiley.covid19tracker.dto.OperationData;
import com.wiley.covid19tracker.entity.ChartData;
import com.wiley.covid19tracker.entity.CovidStatistical;
import com.wiley.covid19tracker.repository.ChartDataRepository;
import com.wiley.covid19tracker.repository.CovidStatisticalRepository;
import com.wiley.covid19tracker.service.CovidStatisticalApiService;
import com.wiley.covid19tracker.util.CommonUtils;

@Service
public class CovidStatisticalApiServiceImpl implements CovidStatisticalApiService{
	
	private static final Logger log = LoggerFactory.getLogger(CovidStatisticalServiceImpl.class);
	
	private CommonUtils commonUtils;
	private CovidStatisticalRepository covidStatisticalRepository;
	private ChartDataRepository chartDataRepository;
	
	@Autowired
	public CovidStatisticalApiServiceImpl(CommonUtils commonUtils, CovidStatisticalRepository covidStatisticalRepository, 
			ChartDataRepository chartDataRepository) {
		this.commonUtils = commonUtils;
		this.covidStatisticalRepository = covidStatisticalRepository;
		this.chartDataRepository = chartDataRepository;
	}

	@Override
	public OperationData<CovidStatisticalDTO> getCovidStatisticalInfo() {
		CovidStatisticalDTO covidStatisticalDTO = getCovidStatistical();
		OperationData<CovidStatisticalDTO> opData;
		if(covidStatisticalDTO != null) opData = commonUtils.generateOperationData(Status.SUCCESS, covidStatisticalDTO, null);
		else opData = commonUtils.generateOperationData(Status.FAILURE, null, null);
		return opData;
	}
	
	@Override
	public OperationData<?> updateChartData(ChartDataDTO chartDataDTO) {
		OperationData<?> opData;
		ChartData chartData = updateChartDataInfo(chartDataDTO);
		if(chartData != null) {
			ChartData savedChartData = chartDataRepository.save(chartData);
			opData = commonUtils.generateOperationData(Status.SUCCESS, savedChartData, null);
		}else {
			opData = commonUtils.generateOperationData(Status.FAILURE, null, null);
		}
		return null;
	}
	
	private CovidStatisticalDTO getCovidStatistical() {
		CovidStatisticalDTO response;
		try {
			response = getCovidStatisticalData();
		} catch (Exception e) {
			response = new CovidStatisticalDTO();
			log.error("Error occured when getting data. Error message : {}", e.getMessage());
		}
		return response;
	}

	private CovidStatisticalDTO getCovidStatisticalData() throws Exception {
		CovidStatistical info = covidStatisticalRepository.getCovidStatistical();
		return convertToDTO(info);
	}
	
	private CovidStatisticalDTO convertToDTO(CovidStatistical covidStatistical) throws Exception {
		CovidStatisticalDTO covidStatisticalDto = new CovidStatisticalDTO();
		BeanUtils.copyProperties(covidStatistical, covidStatisticalDto);
		return covidStatisticalDto;
	}

	private ChartData updateChartDataInfo(ChartDataDTO chartDataDTO) {
		ChartData chartData;
		try {
			chartData = convertToEntity(chartDataDTO);
		} catch (Exception e) {
			chartData = new ChartData();
			log.error("Error occured when getting data. Error message : {}", e.getMessage());
		}
		return chartData;
	}
	
	private ChartData convertToEntity(ChartDataDTO chartDataDTO) throws Exception {
		ChartData chartData = new ChartData();
		BeanUtils.copyProperties(chartDataDTO, chartData);
		return chartData;
	}
	
}
