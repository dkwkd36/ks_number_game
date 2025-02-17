package com.example.number.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	
	private static final String NAMESPACE = "com.example.number.mapper.MemberMapper";
	private static final Logger logger = LoggerFactory.getLogger(MemberDAO.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int findMemberByIdAndPassword(String member_id, String member_password) {
		try (Connection connection = dataSource.getConnection()) {
	        if (connection == null || connection.isClosed()) {
	            logger.error("DB接続時にエラーが発生しました。");
	            return -1; 
	        }
	    } catch (Exception e) {
	        logger.error("DB接続時にエラーが発生しました。", e);
	        return -1;
	    }
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("member_id", member_id);
		params.put("member_password", member_password);
		
		int loginResult = sqlSessionTemplate.selectOne(NAMESPACE + ".findMemberByIdAndPassword", params);
		
		if (loginResult == 0) {
			logger.warn("会員IDが登録されていません。");
		}
		
		return loginResult;
    }
}
