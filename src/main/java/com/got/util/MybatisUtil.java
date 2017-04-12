package com.got.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	private static final SqlSessionFactory FACTORY;
		
	static {
		try {
			Reader reader = Resources.getResourceAsReader("mybatis/sqlMapConfig.xml");
			FACTORY = new SqlSessionFactoryBuilder().build(reader);
		} catch(Exception e) {
			System.err.println(e);
			throw new ExceptionInInitializerError();
		}
	}
	
	public static SqlSession openSession() {
		return FACTORY.openSession(true);
	}
	
	public static SqlSession openTransactionSession() {
		return FACTORY.openSession();
	}
}
