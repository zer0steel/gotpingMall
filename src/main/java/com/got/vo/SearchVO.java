package com.got.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SearchVO {
	private String startDate, endDate;
	
	public SearchVO() {
		setDefaultDate();
	}
	
	private void setDefaultDate() {
		LocalDate endDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		this.startDate = endDate.minusMonths(3).format(formatter);
		this.endDate = endDate.format(formatter);
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
