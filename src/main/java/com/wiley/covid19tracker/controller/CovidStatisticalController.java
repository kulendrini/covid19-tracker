package com.wiley.covid19tracker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiley.covid19tracker.dto.CovidStatisticalDTO;
import com.wiley.covid19tracker.dto.OperationData;
import com.wiley.covid19tracker.service.CovidStatisticalService;

@RestController
@RequestMapping("v1/covid_statistical")
public class CovidStatisticalController {

	private static final Logger log = LoggerFactory.getLogger(CovidStatisticalController.class);
	
	private CovidStatisticalService covidStatisticalService;
	
	@Autowired
	public CovidStatisticalController(CovidStatisticalService covidStatisticalService) {
		this.covidStatisticalService = covidStatisticalService;
	}
	
	@GetMapping
	public ResponseEntity<OperationData<CovidStatisticalDTO>> getCovidStatisticalInfo() {
		log.info("Get covid statistical updated info");
		OperationData<CovidStatisticalDTO> info = covidStatisticalService.getCovidStatisticalInfo();
		log.debug("data to return : {}" , info);
		return new ResponseEntity<OperationData<CovidStatisticalDTO>>(info, HttpStatus.OK);
	}
}
