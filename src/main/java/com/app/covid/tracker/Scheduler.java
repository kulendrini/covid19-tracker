package com.app.covid.tracker;

import com.app.covid.tracker.service.CovidStatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
	}
}
