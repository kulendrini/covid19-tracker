package com.wiley.covid19tracker.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wiley.covid19tracker.dto.ChartDataDTO;
import com.wiley.covid19tracker.dto.CovidStatisticalDTO;
import com.wiley.covid19tracker.dto.ResponseData;
import com.wiley.covid19tracker.entity.ChartData;
import com.wiley.covid19tracker.entity.CovidStatistical;
import com.wiley.covid19tracker.repository.ChartDataRepository;
import com.wiley.covid19tracker.repository.CovidStatisticalRepository;
import com.wiley.covid19tracker.service.CovidStatisticalService;

@Service
public class CovidStatisticalServiceImpl implements CovidStatisticalService{
	
	private static final Logger log = LoggerFactory.getLogger(CovidStatisticalServiceImpl.class);
	
	@Value( "${healthgov.coronavirus.url}" )
	private String url;
	
	private CovidStatisticalRepository covidStatisticalRepository;
	private ChartDataRepository chartDataRepository;
	
	@Autowired
	public CovidStatisticalServiceImpl(CovidStatisticalRepository covidStatisticalRepository, 
			ChartDataRepository chartDataRepository) {
		this.covidStatisticalRepository = covidStatisticalRepository;
		this.chartDataRepository = chartDataRepository;
	}

	@Override
	public HashMap getCovidStatisticalInfo() {
		HashMap response = new HashMap();
		CovidStatisticalDTO covidStatisticalResponse;
		ArrayList<ChartDataDTO> chartDataResponse;
		try {
			covidStatisticalResponse = getCovidStatisticalData();
			chartDataResponse = getChartData();
		} catch (Exception e) {
			covidStatisticalResponse = new CovidStatisticalDTO();
			chartDataResponse = new ArrayList<ChartDataDTO>();
			log.error("Error occured when mapping data. Error message : {}", e.getMessage());
		}
		response.put("CovidStatisticalResponse", covidStatisticalResponse);
		response.put("ChartData", chartDataResponse);
		return response;
	}

	@Override
	public void updateCovidStatistical() {
		try {
			Optional<CovidStatistical> covidStatistical = covidStatisticalRepository.findById(new Integer(1));
			CovidStatistical latestCovidStatistical = getlatestCovidStatistical();
			if (covidStatistical.isPresent()) {
				covidStatistical.get().setLocalTotalCases(latestCovidStatistical.getLocalTotalCases());
				covidStatistical.get().setLocalRecovered(latestCovidStatistical.getLocalRecovered());
				covidStatistical.get().setLocalActiveCases(latestCovidStatistical.getLocalActiveCases());
				covidStatistical.get().setLocalDeaths(latestCovidStatistical.getLocalDeaths());
				covidStatistical.get().setUpdateDateTime(LocalDateTime.now());
				covidStatisticalRepository.save(covidStatistical.get());
			} else {
				latestCovidStatistical.setUpdateDateTime(LocalDateTime.now());
				covidStatisticalRepository.save(latestCovidStatistical);
			}
		} catch (Exception e) {
			covidStatisticalRepository.save(new CovidStatistical());
			log.error("Error occured when saving data. Error message : {}", e.getMessage());
		}
	}
	
	@Override
	public void updateChartData() {

	}
	
	private CovidStatisticalDTO getCovidStatisticalData() throws Exception {
		CovidStatistical info = covidStatisticalRepository.getCovidStatistical();
		return convertToDTO(info);
	}
	
	private ArrayList<ChartDataDTO> getChartData() throws Exception {
		ArrayList<ChartDataDTO> listDto = new ArrayList<ChartDataDTO>();
		Iterable<ChartData> findAll = chartDataRepository.findAll();
		for (ChartData chartData : findAll) {
			ChartDataDTO dto = new ChartDataDTO();
			BeanUtils.copyProperties(chartData, dto);
			listDto.add(dto);
		}
		return listDto;
	}
	
	private CovidStatisticalDTO convertToDTO(CovidStatistical covidStatistical) throws Exception {
		CovidStatisticalDTO covidStatisticalDto = new CovidStatisticalDTO();
		BeanUtils.copyProperties(covidStatistical, covidStatisticalDto);
		return covidStatisticalDto;
	}

	private CovidStatistical getlatestCovidStatistical() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ResponseData> response = restTemplate.getForEntity(url, ResponseData.class);
		return dtoToEntity(response.getBody().getData());
	}
	
	private CovidStatistical dtoToEntity(CovidStatisticalDTO dto) throws Exception {
		CovidStatistical entity = new CovidStatistical();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

}
