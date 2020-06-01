package com.wiley.covid19tracker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wiley.covid19tracker.constant.Status;
import com.wiley.covid19tracker.dto.CurrentStatisticalInfo;
import com.wiley.covid19tracker.dto.OperationData;
import com.wiley.covid19tracker.dto.ResponseData;
import com.wiley.covid19tracker.entity.CovidStatistical;
import com.wiley.covid19tracker.mapper.CurrentStatisticalMapper;
import com.wiley.covid19tracker.repository.CovidStatisticalRepository;
import com.wiley.covid19tracker.util.AppHelper;

@Service
public class CovidStatisticalServiceImpl implements CovidStatisticalService{
	
	@Value( "${healthgov.coronavirus.url}" )
	private String BASE_URL;
	
	private static final Logger log = LoggerFactory.getLogger(CovidStatisticalServiceImpl.class);
	
	private AppHelper appHelper;
	private CovidStatisticalRepository covidStatisticalRepository;
	
	@Autowired
	public CovidStatisticalServiceImpl(AppHelper appHelper, CovidStatisticalRepository covidStatisticalRepository) {
		this.appHelper = appHelper;
		this.covidStatisticalRepository = covidStatisticalRepository;
	}

	@Override
	public OperationData<CurrentStatisticalInfo> getCurrentStatistical() {
		CurrentStatisticalInfo info = callCurrentStatisticalInfo();
		OperationData<CurrentStatisticalInfo> opData = appHelper.generateOperationData(Status.SUCCESS, info, null);
		return opData;
	}

	private CurrentStatisticalInfo callCurrentStatisticalInfo() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ResponseData> response = restTemplate.getForEntity(BASE_URL, ResponseData.class);
		return response.getBody().getData();
	}
	
	private CovidStatistical mapToEntity() {
		CurrentStatisticalInfo info = callCurrentStatisticalInfo();
		log.info("Mapping Entity : {}", info);
		CovidStatistical covidEntity = CurrentStatisticalMapper.INSTANCE
				.maptoEntityCovidStatistical(info.getLocal_total_cases(), info.getLocal_recovered(),  info.getLocal_active_cases(), info.getLocal_deaths());
		return covidEntity;
	}

	@Override
	public OperationData<?> saveCurrentStatistical() {
		OperationData<?> opData;
		CovidStatistical saveEntity = saveCovidStatisticalEntity();
		if(saveEntity != null) opData = appHelper.generateOperationData(Status.SUCCESS, saveEntity, null);
		else opData = appHelper.generateOperationData(Status.FAILURE, null, null);
		return opData;
	}
	
	private CovidStatistical saveCovidStatisticalEntity() {
		CovidStatistical covidEntity = mapToEntity();
		log.info("Map entity : {}", covidEntity);
		CovidStatistical saveEntity = covidStatisticalRepository.save(covidEntity);
		log.info("Save Entity : {}", saveEntity);
		return saveEntity;
	}

	@Override
	public OperationData<?> updateCurrentStatistical() {
		OperationData<?> opData;
		CovidStatistical covidEntity = updateCovidStatistical();
		log.info("Map entity : {}", covidEntity);
		if(covidEntity != null) opData = appHelper.generateOperationData(Status.SUCCESS, covidEntity, null);
		else opData = appHelper.generateOperationData(Status.FAILURE, null, null);
		return opData;
	}
	
	private CovidStatistical updateCovidStatistical() {
		CovidStatistical covidEntity = mapToEntity();
		covidEntity.setId(1);
		CovidStatistical saveEntity = covidStatisticalRepository.save(covidEntity);
		return saveEntity;
	}

	@Override
	public OperationData<CovidStatistical> getCurrentStatisticalInfo() {
		CovidStatistical info = getCurrentStatisticalEntity();
		OperationData<CovidStatistical> opData;
		if(info != null) opData = appHelper.generateOperationData(Status.SUCCESS, info, null);
		else opData = appHelper.generateOperationData(Status.FAILURE, null, null);
		return opData;
	}
	
	private CovidStatistical getCurrentStatisticalEntity() {
		CovidStatistical info = covidStatisticalRepository.getCovidStatistical();
		return info;
	}

}
