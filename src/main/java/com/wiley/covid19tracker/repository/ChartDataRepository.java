package com.wiley.covid19tracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.wiley.covid19tracker.entity.ChartData;

public interface ChartDataRepository extends CrudRepository<ChartData, Integer>{

}
