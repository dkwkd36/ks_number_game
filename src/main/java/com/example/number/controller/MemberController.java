package com.example.number.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.number.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/")
	public String index() {
		return "login";
	}
	
	@GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
	
	@PostMapping("/login")
    public String login(
            @RequestParam String member_id,
            @RequestParam String member_password,
            Model model,
            HttpSession session) {
		
        int login_result = memberService.login(member_id, member_password);
        
        if (login_result == 1) {
            session.setAttribute("member_id", member_id);
            return "redirect:/game";
        } else if (login_result == 0) {
            model.addAttribute("login_result", "会員IDが登録されていません。");
            return "login";
        } else {
        	model.addAttribute("login_result", "システムエラーが発生しました。");
            return "login";
        }
    }
	
}
