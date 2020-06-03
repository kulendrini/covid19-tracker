package com.wiley.covid19tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wiley.covid19tracker.entity.CovidStatistical;
import com.wiley.covid19tracker.service.CovidStatisticalService;

@Component
public class Scheduler {

	private static final Logger log = LoggerFactory.getLogger(Scheduler.class);
	
	private CovidStatisticalService covidStatisticalService;
	
	@Autowired
	public Scheduler(CovidStatisticalService covidStatisticalService) {
		this.covidStatisticalService = covidStatisticalService;
	}

	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		CovidStatistical updateCovidStatistical = covidStatisticalService.updateCovidStatistical();
		log.info("Update covid Statistical : {}", updateCovidStatistical);
	}
}
