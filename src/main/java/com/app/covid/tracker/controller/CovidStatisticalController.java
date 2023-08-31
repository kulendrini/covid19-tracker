package com.app.covid.tracker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.covid.tracker.dto.ChartDataDTO;
import com.app.covid.tracker.dto.CovidStatisticalDTO;
import com.app.covid.tracker.dto.OperationData;
import com.app.covid.tracker.service.CovidStatisticalApiService;

@RestController
@RequestMapping("v1/covid_statistical")
public class CovidStatisticalController {

	private static final Logger log = LoggerFactory.getLogger(CovidStatisticalController.class);
	
	private CovidStatisticalApiService covidStatisticalService;
	
	@Autowired
	public CovidStatisticalController(CovidStatisticalApiService covidStatisticalService) {
		this.covidStatisticalService = covidStatisticalService;
	}
	
	@GetMapping
	public ResponseEntity<OperationData<CovidStatisticalDTO>> getCovidStatisticalInfo() {
		log.info("Get covid statistical updated info");
		OperationData<CovidStatisticalDTO> info = covidStatisticalService.getCovidStatisticalInfo();
		log.debug("data to return - covid statistical info");
		return new ResponseEntity<OperationData<CovidStatisticalDTO>>(info, HttpStatus.OK);
	}
	
	@PostMapping("chart_data")
	public ResponseEntity<OperationData<?>> saveChartData(@RequestBody ChartDataDTO chartDataDTo) {
		log.info("Get covid statistical updated info");
		OperationData<?> info = covidStatisticalService.updateChartData(chartDataDTo);
		log.debug("data to return - covid statistical info");
		return new ResponseEntity<OperationData<?>>(info, HttpStatus.OK);
	}
}
