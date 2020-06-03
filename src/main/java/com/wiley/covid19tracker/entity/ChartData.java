package com.wiley.covid19tracker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChartData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String month;
	private Integer total;
	private Integer active;
	
	public ChartData() {	}

	public ChartData(Integer id, String month, Integer total, Integer active) {
		super();
		this.id = id;
		this.month = month;
		this.total = total;
		this.active = active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "ChartData [id=" + id + ", month=" + month + ", total=" + total + ", active=" + active + "]";
	}
	
}
