package com.wiley.covid19tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wiley.covid19tracker.dto.CovidStatisticalDTO;
import com.wiley.covid19tracker.service.CovidStatisticalService;

@Controller
public class DashboardController {
	
	private CovidStatisticalService covidStatisticalService;
	
	@Autowired
	public DashboardController(CovidStatisticalService covidStatisticalService) {
		this.covidStatisticalService = covidStatisticalService;
	}

	@RequestMapping(value = {"/", "/dashboard"}, method = RequestMethod.GET)
	public String vewDashboard(Model model) {
		CovidStatisticalDTO covidStatisticalInfo = covidStatisticalService.getCovidStatisticalInfo().getData();
		model.addAttribute("value", covidStatisticalInfo);
		return "index";
	}
}
