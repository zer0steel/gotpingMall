package com.got.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.got.mapper.VisitStatsMapper;
import com.got.vo.SearchVO;
import com.got.vo.VisitStatsVO;

@Service
public class VisitStatsService {

	@Inject private VisitStatsMapper statsMapper;
	
	public void updateCount() {
		if(Objects.isNull(statsMapper.selectOneToday())) {
			statsMapper.insert();
		}
		statsMapper.updateCount();
	}
	
	public List<VisitStatsVO> getDataByDate(SearchVO s) {
		return fillEmptyDate(statsMapper.selectListPeriod(s), LocalDate.parse(s.getStartDate(), DateTimeFormatter.ISO_LOCAL_DATE));
	}
	
	private List<VisitStatsVO> fillEmptyDate(List<VisitStatsVO> selectedData, LocalDate startDate) {
		List<VisitStatsVO> returnData = new ArrayList<>();
		for(int i = 0; i < selectedData.size(); i++) {
			LocalDate time = selectedData.get(i).getDay().toLocalDateTime().toLocalDate();
			while(!time.isEqual(startDate)) {
				VisitStatsVO statsVO = new VisitStatsVO();
				statsVO.setDay(Timestamp.valueOf(startDate.atStartOfDay()));
				returnData.add(statsVO);
				startDate = startDate.plusDays(1);
			}
			returnData.add(selectedData.get(i));
			startDate = startDate.plusDays(1);
		}
		return returnData;
	}
}
