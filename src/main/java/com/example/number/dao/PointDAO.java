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

import com.example.number.dto.PointDTO;

@Repository
public class PointDAO {
	
	private static final String NAMESPACE = "com.example.number.mapper.PointMapper";
	private static final Logger logger = LoggerFactory.getLogger(PointDAO.class);
	
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;
	
	public PointDTO getPointByMemberId(String member_id) {
		try (Connection connection = dataSource.getConnection()) {
			
	        if (connection == null || connection.isClosed()) {
	            logger.error("DB接続時にエラーが発生しました。");
	            return null;
	        }
	        
	    } catch (Exception e) {
	        logger.error("DB接続時にエラーが発生しました。", e);
	        return null;
	    }
		
		try {
			PointDTO pointInfo = sqlSessionTemplate.selectOne(NAMESPACE + ".getPointByMemberId", member_id);
			
			if (pointInfo == null) {
				logger.warn("ポイント情報テーブルに会員IDが登録されていません。");
			}
			
			return pointInfo;
		} catch (Exception e) {
			logger.error("システムエラーが発生しました。", e);
			return null;
		}
		
    }
	
	public void setHiddenNumberByMemberId(String member_id, String hidden_number, int game_count, int game_act_fig) {
        Map<String, Object> params = new HashMap<String, Object>();
		params.put("member_id", member_id);
		params.put("hidden_number", hidden_number);
		params.put("game_count", game_count);
		params.put("game_act_fig", game_act_fig);
		try {
			sqlSessionTemplate.update(NAMESPACE + ".setHiddenNumberByMemberId", params);        				
		} catch (Exception e) {
			logger.error("システムエラーが発生しました。", e);
		}
    }
	
	
	public void setPointInfo(String member_id, int point, int game_count, int game_act_fig) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("member_id", member_id);
		params.put("game_count", game_count);
		params.put("point", point);
		params.put("game_act_fig", game_act_fig);
		try {
			sqlSessionTemplate.update(NAMESPACE + ".setPointInfo", params);			
		} catch (Exception e) {
			logger.error("システムエラーが発生しました。", e);
		}
		
	}
	
}
