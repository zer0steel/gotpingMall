package com.got.dao.template;

import org.apache.ibatis.session.SqlSession;

@FunctionalInterface
public interface SqlCallback {
	
	public int execute(SqlSession session);
}
