package com.got.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.got.dao.CategoryDao;
import com.got.enums.Menu_level;
import com.got.util.CommonUtil;
import com.got.vo.CategoryVO;

@Service
public class CategoryService {
	
	@Autowired private CategoryDao dao;
	
	public List<CategoryVO> getAll() {
		return dao.selectAll();
	}
	
	public String getOneWithJSON(int c_no) {
		return CommonUtil.convertToJSON(dao.selectOne(c_no));
	}
	
	/**
	 * ���ο� �з��� ����Ѵ�.
	 * @param c
	 * @return ����, ���� �޽���
	 */
	public String enroll(CategoryVO c) {
		validationCheck(c);
		if(dao.insertOne(c) == 1) {
			Menu_level.addCategory(c);
			return "����ϼ̽��ϴ�.";
		}
		return "��Ͽ� �����߽��ϴ�.";
	}

	/**
	 * �ش� ��ȣ�� �з��� �����Ѵ�
	 * @param c_no
	 * @return ����, ���� �޽���
	 */
	public String delete(int c_no) {
		if(dao.deleteOne(c_no) == 1) {
			Menu_level.deleteCategory(c_no);
			return "�����߽��ϴ�.";
		}
		return "������ �����߽��ϴ�.";
	}

	/**
	 * �з� ������ �ٲ۴�.
	 * @param c
	 * @return ����, ���� �޽���
	 */
	public String update(CategoryVO c) {
		validationCheck(c);
		if(c.getC_no() == 0)
			throw new IllegalArgumentException("�з� ������Ʈ���� PK���� c_no �� ���� 0��");
		if(dao.updateOne(c) == 1) {
			Menu_level.updateCategory(c);
			return "�����߽��ϴ�.";
		}
		return "������ �����߽��ϴ�.";
	}
	
	/**
	 * ModelAndView�� �з��������� �����Ѵ�.
	 * @param mav
	 * @return big, middle, small ���� ��ϵ� ModelAndView
	 */
	public ModelAndView setEnumsInMAV(ModelAndView mav) {
		setEnum();
		mav.addObject("big", Menu_level.BIG);
		mav.addObject("middle", Menu_level.MIDDLE);
		mav.addObject("small", Menu_level.SMALL);
		return mav;
	}
	
	private static boolean isSetting = false;
	/**
	 * Enum class Menu_level �� ������ ���̽��� �����ϴ� �з����� �з������� ���缭 �з��Ѵ�.
	 */
	private void setEnum() {
		if(!isSetting) {
			Menu_level.groupingCategories(dao.selectAll());
			isSetting = true;
		}
	}
	
	private void validationCheck(CategoryVO c) {
		if(c.getTitle().equals(""))
			throw new IllegalArgumentException("c.getTitle() is empty ");
		if(c.getMenuLevel() == Menu_level.BIG && c.getParent_no() > 0)
			throw new IllegalArgumentException("��з��ε� �θ��ȣ�� ������ ����.");
		if(c.getMenuLevel() != Menu_level.BIG && c.getParent_no() == 0)
			throw new IllegalArgumentException("�����з��ε� �θ��ȣ�� ����.");
	}
}
