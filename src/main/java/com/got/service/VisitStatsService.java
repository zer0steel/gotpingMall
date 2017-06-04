package com.got.service;

import java.util.Objects;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.got.mapper.VisitStatsMapper;

@Service
public class VisitStatsService {

	@Inject private VisitStatsMapper statsMapper;
	
	public void updateCount() {
		if(Objects.isNull(statsMapper.selectOneToday())) {
			statsMapper.insert();
		}
		statsMapper.updateCount();
	}
}
