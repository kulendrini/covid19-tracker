package com.wiley.covid19tracker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiley.covid19tracker.dto.CurrentStatisticalInfo;
import com.wiley.covid19tracker.dto.OperationData;
import com.wiley.covid19tracker.entity.CovidStatistical;
import com.wiley.covid19tracker.service.CovidStatisticalService;
import com.wiley.covid19tracker.service.CovidStatisticalServiceImpl;

@RestController
@RequestMapping("v1/covid_statistical")
public class CovidStatisticalController {

	private static final Logger log = LoggerFactory.getLogger(CovidStatisticalServiceImpl.class);
	
	private CovidStatisticalService covidStatisticalService;
	
	@Autowired
	public CovidStatisticalController(CovidStatisticalService covidStatisticalService) {
		this.covidStatisticalService = covidStatisticalService;
	}
	
	@GetMapping("gov")
	public ResponseEntity<OperationData<CurrentStatisticalInfo>> getCovidStatistical() {
		log.info("Get covid statistical info");
		OperationData<CurrentStatisticalInfo> info = covidStatisticalService.getCurrentStatistical();
		log.debug("data to return : {}" , info);
		return new ResponseEntity<OperationData<CurrentStatisticalInfo>>(info, HttpStatus.OK);
	}
	 
	@PostMapping
	public ResponseEntity<OperationData<?>> saveCovidStatistical(){
		log.info("Save covid statistical info");
		OperationData<?> info = covidStatisticalService.saveCurrentStatistical();
		log.info("data to return : {}" , info);
		return new ResponseEntity<OperationData<?>>(info, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<OperationData<CovidStatistical>> getCovidStatisticalInfo() {
		log.info("Get covid statistical updated info");
		OperationData<CovidStatistical> info = covidStatisticalService.getCurrentStatisticalInfo();
		log.debug("data to return : {}" , info);
		return new ResponseEntity<OperationData<CovidStatistical>>(info, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<OperationData<?>> updateCovidStatistical(){
		log.info("Update covid statistical info");
		OperationData<?> info = covidStatisticalService.updateCurrentStatistical();
		log.info("data to return : {}" , info);
		return new ResponseEntity<OperationData<?>>(info, HttpStatus.OK);
	}
}
