package com.wiley.covid19tracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.wiley.covid19tracker.service.CovidStatisticalService;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
	
	private CovidStatisticalService covidStatisticalService;
	
	@Autowired
	public CommandLineAppStartupRunner(CovidStatisticalService covidStatisticalService) {
		this.covidStatisticalService = covidStatisticalService;
	}

	@Override
	public void run(String... args) throws Exception {
		covidStatisticalService.updateCovidStatistical();
		covidStatisticalService.updateChartData();
	}

}
