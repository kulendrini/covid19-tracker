package com.app.covid.tracker.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.covid.tracker.entity.CovidStatistical;

public interface CovidStatisticalRepository extends CrudRepository<CovidStatistical, Integer>{

	@Query(value = "select * from COVID_STATISTICAL order by id desc limit 1", nativeQuery = true)
	CovidStatistical getCovidStatistical();
}
