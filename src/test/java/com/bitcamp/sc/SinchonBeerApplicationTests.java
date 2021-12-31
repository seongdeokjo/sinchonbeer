package com.bitcamp.sc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SinchonBeerApplicationTests {
	
	private SqlSessionFactory sqlSessionFactory;

	@Autowired
	public SinchonBeerApplicationTests(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Test
	void contextLoads() {
	}
	
	@Test
	void DBDriverTest() throws SQLException {
        try(Connection con = sqlSessionFactory.openSession().getConnection()){
            System.out.println("커넥션 성공!");
        }catch(Exception e){
            e.printStackTrace();
        }
	}
}