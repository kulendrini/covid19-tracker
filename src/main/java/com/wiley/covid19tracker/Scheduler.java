package com.wiley.covid19tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wiley.covid19tracker.service.CovidStatisticalService;

@Component
public class Scheduler {

	private CovidStatisticalService covidStatisticalService;
	
	@Autowired
	public Scheduler(CovidStatisticalService covidStatisticalService) {
		this.covidStatisticalService = covidStatisticalService;
	}

	@Scheduled(cron = "${cronExpression}")
	public void updateCovidStatisticalData() {
		covidStatisticalService.updateCovidStatistical();
		System.out.println("2");
	}
}
