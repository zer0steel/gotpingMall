package com.got.dao.template;

import org.apache.ibatis.session.SqlSession;

@FunctionalInterface
public interface SqlSelectCallback<T> {
	
	public T execute(SqlSession session);
}
