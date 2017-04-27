package com.got.dao.template;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.got.util.MybatisUtil;

@Repository
public class DaoTemplate {
	
	public <T> T selectOne(String namespace) {
		return selectOne(namespace, null);
	}
	
	public <T> T selectOne(String namespace, Object param) {
		return selectTemplate(session -> {
			return session.selectOne(namespace, param);
		});
	}
	
	public <T> List<T> selectList(String namespace) {
		return selectList(namespace, null);
	}
	
	public <T> List<T> selectList(String namespace, Object param) {
		return selectTemplate(session -> {
			return session.selectList(namespace, param);
		});
	}
	
	public void insert(String namespace, Object param) {
		template(session -> {
			session.insert(namespace, param);
		});
	}
	public void delete(String namespace, Object param) {
		template(session -> {
			session.delete(namespace, param);
		});
	}
	public void update(String namespace, Object param) {
		template(session -> {
			session.update(namespace, param);
		});
	}
	
	public void transactionTemplate(SqlCallback callback) {
		SqlSession session = MybatisUtil.openTransactionSession();
		try {
			callback.execute(session);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}
	
	private <T> T selectTemplate(SqlSelectCallback<T> callback) {
		SqlSession session = MybatisUtil.openSession();
		T result = callback.execute(session);
		session.close();
		return result;
	}
	
	private void template(SqlCallback callback) {
		SqlSession session = MybatisUtil.openSession();
		callback.execute(session);
		session.close();
	}
}
