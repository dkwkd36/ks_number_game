package com.example.number.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.number.dto.GameResultDTO;

@Repository
public class ResultDAO {
	
	private static final String NAMESPACE = "com.example.number.mapper.ResultMapper";
	private static final Logger logger = LoggerFactory.getLogger(ResultDAO.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	public List<GameResultDTO> getResultAllByMemberId(String member_id) {
		return sqlSessionTemplate.selectList(NAMESPACE + ".getResultAllByMemberId", member_id);			
    }
	
	public void saveResult(String member_id, int game_count, String result_answer, String result_content) {
		try (Connection connection = dataSource.getConnection()) {
	        if (connection == null || connection.isClosed()) {
	            logger.error("DB接続時にエラーが発生しました。");
	        }
	    } catch (Exception e) {
	        logger.error("DB接続時にエラーが発生しました。", e);
	    }
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("member_id", member_id);
		params.put("game_count", game_count);
		params.put("result_answer", result_answer);
		params.put("result_content", result_content);
		sqlSessionTemplate.insert(NAMESPACE + ".saveResult", params);			
		
	}
}
