package com.wiley.covid19tracker.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wiley.covid19tracker.dto.CovidStatisticalDTO;
import com.wiley.covid19tracker.entity.ChartData;
import com.wiley.covid19tracker.service.CovidStatisticalService;

import springfox.documentation.annotations.ApiIgnore;

@Controller
public class DashboardController {
	
	private CovidStatisticalService covidStatisticalService;
	
	@Autowired
	public DashboardController(CovidStatisticalService covidStatisticalService) {
		this.covidStatisticalService = covidStatisticalService;
	}

	@ApiIgnore
	@RequestMapping(value = {"/", "/dashboard"}, method = RequestMethod.GET)
	public String vewDashboard(Model model) {
		HashMap response = covidStatisticalService.getCovidStatisticalInfo();
		model.addAttribute("value", (CovidStatisticalDTO)response.get("CovidStatisticalResponse"));
		model.addAttribute("chartData", (ArrayList<ChartData>)response.get("ChartData"));
		return "index";
	}
}
