package com.wiley.covid19tracker.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wiley.covid19tracker.constant.Status;
import com.wiley.covid19tracker.dto.CovidStatisticalDTO;
import com.wiley.covid19tracker.dto.OperationData;
import com.wiley.covid19tracker.dto.ResponseData;
import com.wiley.covid19tracker.entity.CovidStatistical;
import com.wiley.covid19tracker.repository.CovidStatisticalRepository;
import com.wiley.covid19tracker.service.CovidStatisticalService;
import com.wiley.covid19tracker.util.CommonUtils;

@Service
public class CovidStatisticalServiceImpl implements CovidStatisticalService{
	
	private static final Logger log = LoggerFactory.getLogger(CovidStatisticalServiceImpl.class);
	
	@Value( "${healthgov.coronavirus.url}" )
	private String BASE_URL;
	
	private CommonUtils appHelper;
	private CovidStatisticalRepository covidStatisticalRepository;
	
	@Autowired
	public CovidStatisticalServiceImpl(CommonUtils appHelper, CovidStatisticalRepository covidStatisticalRepository) {
		this.appHelper = appHelper;
		this.covidStatisticalRepository = covidStatisticalRepository;
	}

	@Override
	public OperationData<CovidStatisticalDTO> getCovidStatisticalInfo() {
		CovidStatisticalDTO info = getCovidtStatisticalData();
		OperationData<CovidStatisticalDTO> opData;
		if(info != null) opData = appHelper.generateOperationData(Status.SUCCESS, info, null);
		else opData = appHelper.generateOperationData(Status.FAILURE, null, null);
		return opData;
	}

	@Override
	public OperationData<?> updateCovidStatistical() {
		OperationData<?> opData;
		CovidStatistical covidEntity = updateCovidStatisticalInfo();
		if(covidEntity != null) opData = appHelper.generateOperationData(Status.SUCCESS, covidEntity, null);
		else opData = appHelper.generateOperationData(Status.FAILURE, null, null);
		return opData;
	}
	
	private CovidStatisticalDTO getCovidtStatisticalData() {
		CovidStatistical info = covidStatisticalRepository.getCovidStatistical();
		return convertToDTO(info);
	}
	
	private CovidStatisticalDTO convertToDTO(CovidStatistical covidStatistical) {
		CovidStatisticalDTO covidStatisticalDto = new CovidStatisticalDTO();
		BeanUtils.copyProperties(covidStatistical, covidStatisticalDto);
		return covidStatisticalDto;
	}

	private CovidStatistical updateCovidStatisticalInfo() {
		Optional<CovidStatistical> covidStatistical = covidStatisticalRepository.findById(new Integer(1));
		CovidStatistical latestCovidStatistical = retreiveDataFromExternalSource();
		if (covidStatistical.isPresent()) {
			covidStatistical.get().setLocalTotalCases(latestCovidStatistical.getLocalTotalCases());
			covidStatistical.get().setLocalRecovered(latestCovidStatistical.getLocalRecovered());
			covidStatistical.get().setLocalActiveCases(latestCovidStatistical.getLocalActiveCases());
			covidStatistical.get().setLocalDeaths(latestCovidStatistical.getLocalDeaths());
			covidStatistical.get().setUpdateDateTime(LocalDateTime.now());
			return covidStatisticalRepository.save(covidStatistical.get());
		} else {
			return covidStatisticalRepository.save(latestCovidStatistical);
		}
	}
	
	private CovidStatistical retreiveDataFromExternalSource() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ResponseData> response = restTemplate.getForEntity(BASE_URL, ResponseData.class);
		return dtoToEntity(response.getBody().getData());
	}
	
	private CovidStatistical dtoToEntity(CovidStatisticalDTO dto) {
		CovidStatistical entity = new CovidStatistical();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

}
