package com.example.number.controller;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.number.dto.PointDTO;
import com.example.number.service.GameService;

@Controller
public class GameController {
	
	@Autowired
    private GameService gameService;
	
	@GetMapping("/game")
	public String gameForm(HttpSession session, Model model) {
		String member_id = (String) session.getAttribute("member_id");
		Map<String, Object> gameData = gameService.gameInit(member_id);
		
		if(gameData == null) {
			model.addAttribute("message", "システムエラーが発生しました。");
		}
		
		PointDTO pointInfo = (PointDTO) gameData.get("pointInfo");
		
		model.addAttribute("pointInfo", pointInfo);
        model.addAttribute("resultList", gameData.get("resultList"));
        
        
        if (pointInfo.getGame_act_fig() == 1) {
            model.addAttribute("message", "本日のゲームは実施ずみです。ゲームは１日１回のみです。");
        }
        
		return "gameForm";
	}
	
	@PostMapping("/game")
	@ResponseBody
	public Map<String, Object> playGame(
			@RequestParam String result_answer,
			@RequestParam String hidden_number,
			@RequestParam int game_count,
			@RequestParam int point,
			HttpSession session,
			RedirectAttributes redirectAttributes,
			Model model) {
		
		String member_id = (String) session.getAttribute("member_id");
		Map<String, Object> response = new HashMap<>();
		
        Map<String, Object> result = gameService.playGame(member_id, result_answer, hidden_number, game_count, point);
        String result_content = (String) result.get("result_content");
        String message = (String) result.get("message");
        int game_act_fig = (Integer) result.get("game_act_fig");
        int earn_point = (Integer) result.get("point");
        
        response.put("result_content", result_content);
        response.put("message", message);
        response.put("game_act_fig", game_act_fig);
        response.put("point", earn_point);
        return response;
	}
	
	@PostMapping("/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}
}
