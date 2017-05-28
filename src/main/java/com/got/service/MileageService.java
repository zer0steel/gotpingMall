package com.got.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.got.mapper.MileageMapper;
import com.got.vo.MileageVO;

@Service
public class MileageService {
	
	@Inject private MileageMapper mileageMapper;
	
	public int checkMileage(MileageVO useMileage, int total_price) {
		MileageVO m = mileageMapper.selectCurrMileage(useMileage.getM_no());
		if(m.getCurr_mileage() - useMileage.getChange_amount() < 0)
			throw new IllegalArgumentException(m.getCurr_mileage() + " - " + useMileage.getChange_amount() + " < 0");
		if(total_price - useMileage.getChange_amount() < 0)
			throw new IllegalArgumentException(total_price + " - " + useMileage.getChange_amount() + " < 0");
		return total_price - useMileage.getChange_amount();
	}
}
