package com.app.covid.tracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.covid.tracker.entity.ChartData;

public interface ChartDataRepository extends CrudRepository<ChartData, Integer>{

}
