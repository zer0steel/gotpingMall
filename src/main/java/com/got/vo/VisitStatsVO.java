package com.got.vo;

import java.sql.Timestamp;

public class VisitStatsVO {
	
	private Timestamp day;
	private int count;
	
	public Timestamp getDay() {
		return day;
	}
	public String getDayStr() {
		return day.toLocalDateTime().toLocalDate().toString();
	}
	public void setDay(Timestamp day) {
		this.day = day;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "VisitStatsVO [day=" + day + ", count=" + count + "]";
	}
	
}
