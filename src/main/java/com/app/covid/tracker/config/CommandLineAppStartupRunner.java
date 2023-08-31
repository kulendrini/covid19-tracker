package com.app.covid.tracker.config;

import com.app.covid.tracker.service.CovidStatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
