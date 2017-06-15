package com.got.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.got.enums.MileageCategory;
import com.got.mapper.MileageMapper;
import com.got.vo.MileageVO;
import com.got.vo.deal.DealDetailVO;
import com.got.vo.deal.PaymentVO;

@Service
public class MileageService {
	
	@Inject private MileageMapper mileageMapper;
	
	public BigDecimal checkMileage(MileageVO useMileage, BigDecimal total_price) {
		MileageVO m = mileageMapper.selectCurrMileage(useMileage.getM_no());
		if(m.getCurr_mileage().subtract(useMileage.getChange_amount()).intValue() < 0)
			throw new IllegalArgumentException(m.getCurr_mileage() + " - " + useMileage.getChange_amount() + " < 0");
		if(total_price.subtract(useMileage.getChange_amount()).intValue() < 0)
			throw new IllegalArgumentException(total_price + " - " + useMileage.getChange_amount() + " < 0");
		return total_price.subtract(useMileage.getChange_amount());
	}
	
	public void insertHistory(PaymentVO pay, List<DealDetailVO> details, Integer m_no) {
		Objects.requireNonNull(m_no);
		if(pay.isUseMileage())
			insertHistory(m_no, pay.getP_no(), pay.getUse_mileage(), MileageCategory.USE);

		BigDecimal saveMileageAmount = calculateSaveMileageAmount(details, pay.getPay_amount());
		if(!saveMileageAmount.equals(BigDecimal.ZERO))
			insertHistory(m_no, pay.getP_no(), saveMileageAmount, MileageCategory.SAVE);
	}
	
	private BigDecimal calculateSaveMileageAmount(List<DealDetailVO> details, BigDecimal pay_amount) {
		if(pay_amount.equals(BigDecimal.ZERO))
			return BigDecimal.ZERO;
		
		BigDecimal totalSavingMileage = BigDecimal.ZERO;
		for(DealDetailVO detail : details) {
			BigDecimal percent = detail.getUnit_price().divide(pay_amount, 2, RoundingMode.HALF_UP);
			BigDecimal unitPrice = pay_amount.multiply(percent);
			BigDecimal saveRate = BigDecimal.valueOf(detail.getStock().getGoods().getSaving_mileage());
			totalSavingMileage = totalSavingMileage.add(unitPrice.multiply(saveRate));
		}
		return totalSavingMileage;
	}
	
	public void insertHistory(Integer m_no, Integer p_no, BigDecimal mileageChangeAmount, MileageCategory category) {
		insertHistory(m_no, p_no, mileageChangeAmount, category, null);
	}
	
	public void insertHistory(Integer m_no, Integer p_no, BigDecimal mileageChangeAmount, MileageCategory category, String reason) {
		MileageVO m = new MileageVO();
		m.setM_no(m_no);
		m.setP_no(p_no);
		m.setChange_amount(mileageChangeAmount);
		m.setEnumCategory(category);
		m.setReason(reason);
		mileageMapper.insert(m);
	}
	
	public MileageVO createMileageVO(Integer m_no, Integer p_no, BigDecimal change_amount, MileageCategory category) {
		return createMileageVO(m_no, p_no, change_amount, category, null);
	}
	public MileageVO createMileageVO(Integer m_no, Integer p_no, BigDecimal change_amount, MileageCategory category, String reason) {
		MileageVO m = new MileageVO();
		m.setM_no(m_no);
		m.setP_no(p_no);
		m.setChange_amount(change_amount);
		m.setEnumCategory(category);
		m.setReason(reason);
		return m;
	}
}
