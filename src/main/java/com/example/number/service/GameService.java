package com.example.number.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.number.dao.PointDAO;
import com.example.number.dao.ResultDAO;
import com.example.number.dto.GameResultDTO;
import com.example.number.dto.PointDTO;

@Service
public class GameService {
	
	@Autowired
	private PointDAO pointDAO;
	
	@Autowired
	private ResultDAO resultDAO;
		
	public Map<String, Object> gameInit(String member_id) {
		Map<String, Object> gameData = new HashMap<>();
		List<GameResultDTO> resultList = new ArrayList<>();
		PointDTO pointInfo = pointDAO.getPointByMemberId(member_id);
		
		if(pointInfo == null) {
	    	return null;
	    }
		
	    String today = LocalDate.now().toString();
	    String recUpdateDate = pointInfo.getRec_update_date().toLocalDate().toString(); 
	    
	    if(pointInfo.getRec_create_date().equals(pointInfo.getRec_update_date()) || !today.equals(recUpdateDate)) {
	    	Random rand = new Random();
	        List<Integer> numbers = new ArrayList<>();

	        while (numbers.size() < 3) {
	            int randomNum = rand.nextInt(10); 
	            if (!numbers.contains(randomNum)) { 
	                numbers.add(randomNum);
	            }
	        }

	        String hidden_number = "";
	        for (int num : numbers) {
	            hidden_number += num; 
	        }
	    	
            pointInfo.setHidden_number(hidden_number);
            pointInfo.setGame_count(0);
            pointInfo.setGame_act_fig(0);
            pointDAO.setHiddenNumberByMemberId(member_id, hidden_number, 0, 0);
	    } else {
	    	resultList = resultDAO.getResultAllByMemberId(member_id);
	    	
	    	if (resultList == null) {
	    		return null;
	    	}
	    }
            
        gameData.put("pointInfo", pointInfo);
        gameData.put("resultList", resultList);
        
	    return gameData;
	}
	
	public Map<String, Object> playGame(String member_id, String result_answer, String hidden_number, int game_count, int point) {
		Map<String, Object> result = new HashMap<>();
		String result_content = "";
		int s = 0, b = 0;
		int game_act_fig = 0;
		String message = null;
		
		game_count += 1;

        for (int i = 0; i < 3; i++) {
        	for (int j = 0; j < 3; j++) {
        		if (result_answer.charAt(i) == hidden_number.charAt(j)) {
        			if (i == j) {
        				s++;
        			} else {
        				b++;
        			}
        		}
        	}
        		
        }
        
        if (s == 3) {
        	result_content = "あたり";
        	game_act_fig = 1;
        	if (game_count >= 1 && game_count <= 5) {
        		point = point + 1000;
        		message = "挑戦に成功しました。（1000ポイント）";
        	} else if (game_count >= 6 && game_count <= 7) {
        		point = point + 500;
        		message = "挑戦に成功しました。（500ポイント）";
        	} else if (game_count >=8 && game_count <= 10) {
        		point = point + 200;
        		message = "挑戦に成功しました。（200ポイント）";
        	}
        } else {         	
        	if (s > 0 && b > 0) {
        		result_content = s + "S" + b + "B";
        	} else if (s == 0 && b > 0) {
        		result_content = b + "B";
        	} else if (b == 0 && s > 0) {
        		result_content = s + "S";
        	} else {
        		result_content = "はずれ";
        	}
        	
        	if (game_count == 10) {
        		game_act_fig = 1;
        		message = "挑戦に失敗しました。";
            }
        	
        }
        
        resultDAO.saveResult(member_id, game_count, result_answer, result_content);
        pointDAO.setPointInfo(member_id, point, game_count, game_act_fig);
        
        result.put("result_content", result_content);
        result.put("message", message);
        result.put("game_act_fig", game_act_fig);
        result.put("point", point);
        
        return result;
	}
}
