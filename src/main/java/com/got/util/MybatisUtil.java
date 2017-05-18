package com.got.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.got.mapper.files.GoodsImageMapper;
import com.got.mapper.goods.GoodsMapper;
import com.got.mapper.goods.OptionStockMapper;

public class MybatisUtil {
	private static final SqlSessionFactory FACTORY;
	private static final String MYBATIS_PATH = "mybatis/sqlMapConfig.xml"; 
	static {
		try {
			Reader reader = Resources.getResourceAsReader(MYBATIS_PATH);
			FACTORY = new SqlSessionFactoryBuilder().build(reader);
			addMapper(FACTORY.getConfiguration());
		} catch(Exception e) {
			System.err.println(e);
			throw new ExceptionInInitializerError();
		}
	}
	
	private static void addMapper(Configuration configuration) {
		configuration.addMapper(GoodsMapper.class);
		configuration.addMapper(GoodsImageMapper.class);
		configuration.addMapper(OptionStockMapper.class);
	}
	
	public static SqlSession openSession() {
		return FACTORY.openSession(true);
	}
	
	public static SqlSession openTransactionSession() {
		return FACTORY.openSession();
	}
	
	public static SqlSessionFactory getFactroy() {
		return FACTORY;
	}
}
