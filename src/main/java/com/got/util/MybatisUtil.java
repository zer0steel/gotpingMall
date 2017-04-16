package com.got.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	private static final SqlSessionFactory FACTORY;
	private static final String MYBATIS_PATH = "mybatis/sqlMapConfig.xml"; 
	static {
		try {
			Reader reader = Resources.getResourceAsReader(MYBATIS_PATH);
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
