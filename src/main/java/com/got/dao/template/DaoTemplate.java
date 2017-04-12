package com.got.dao.template;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.transaction.TransactionException;
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
	
	public int insert(String namespace, Object param) {
		return template(session -> {
			return session.insert(namespace, param);
		});
	}
	public int delete(String namespace, Object param) {
		return template(session -> {
			return session.delete(namespace, param);
		});
	}
	public int update(String namespace, Object param) {
		return template(session -> {
			return session.update(namespace, param);
		});
	}
	
	/**
	 * 트랜잭션을 지원하는 DB접속 템플릿이다.
	 * SqlCallback 인터페이스를 구현할 때, rollback 해야 하는 상황이 나타나면
	 * TransactionException을 던져야 한다.
	 * 아무 에러 없이 변동된 컬럼의 수를 반환하면 commit이 된다.
	 * @param callback
	 * @return 추가, 삭제, 수정 된 컬럼의 개수
	 */
	public int transactionTemplate(SqlCallback callback) {
		SqlSession session = MybatisUtil.openTransactionSession();
		int count = -1;
		try {
			count = callback.execute(session);
			session.commit();
		} catch (TransactionException e) {
			System.err.println(e);
			session.rollback();
		} finally {
			session.close();
		}
		return count;
	}
	
	private <T> T selectTemplate(SqlSelectCallback<T> callback) {
		SqlSession session = MybatisUtil.openSession();
		T result = callback.execute(session);
		session.close();
		return result;
	}
	
	private int template(SqlCallback callback) {
		SqlSession session = MybatisUtil.openSession();
		int count = callback.execute(session);
		session.close();
		return count;
	}
}
