package com.wiley.covid19tracker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CovidStatistical {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer localTotalCases;
	private Integer localRecovered;
	private Integer localActiveCases;
	private Integer localDeaths;
	
	public CovidStatistical() {	}

	public CovidStatistical(Integer id, Integer localTotalCases, Integer localRecovered, Integer localActiveCases,
			Integer localDeaths) {
		super();
		this.id = id;
		this.localTotalCases = localTotalCases;
		this.localRecovered = localRecovered;
		this.localActiveCases = localActiveCases;
		this.localDeaths = localDeaths;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLocalTotalCases() {
		return localTotalCases;
	}

	public void setLocalTotalCases(Integer localTotalCases) {
		this.localTotalCases = localTotalCases;
	}

	public Integer getLocalRecovered() {
		return localRecovered;
	}

	public void setLocalRecovered(Integer localRecovered) {
		this.localRecovered = localRecovered;
	}

	public Integer getLocalActiveCases() {
		return localActiveCases;
	}

	public void setLocalActiveCases(Integer localActiveCases) {
		this.localActiveCases = localActiveCases;
	}

	public Integer getLocalDeaths() {
		return localDeaths;
	}

	public void setLocalDeaths(Integer localDeaths) {
		this.localDeaths = localDeaths;
	}

	@Override
	public String toString() {
		return "CovidStatistical [id=" + id + ", localTotalCases=" + localTotalCases + ", localRecovered="
				+ localRecovered + ", localActiveCases=" + localActiveCases + ", localDeaths=" + localDeaths + "]";
	}
	
}
