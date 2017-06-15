package com.got.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SearchVO {
	private String startDate, endDate;
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
	
	public SearchVO() {
		setStartMonth(3);
	}
	
	/**
	 * 오늘 날짜를 기준으로 시작날짜를 세팅한다.
	 * @param startMonth 오늘을 기준으로 뺄 달의 개수 Ex) now = 12, startMonth = 3 이면 시작달은 9월로 세팅된다.
	 */
	public SearchVO(int startMonth) {
		setStartMonth(startMonth);
	}
	
	private void setStartMonth(int month) {
		LocalDate endDate = LocalDate.now();
		this.startDate = endDate.minusMonths(month).toString();
		this.endDate = endDate.toString();
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
