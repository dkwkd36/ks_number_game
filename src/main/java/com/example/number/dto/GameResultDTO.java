package com.example.number.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class GameResultDTO {
	
	private String member_id;
    private LocalDate game_date;
    private int game_count;
    private String result_answer;
    private String result_content;
    private LocalDateTime rec_create_date;
    private LocalDateTime rec_update_date;
    
	public String getMember_id() {
		return member_id;
	}
	
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	public LocalDate getGame_date() {
		return game_date;
	}
	
	public void setGame_date(LocalDate game_date) {
		this.game_date = game_date;
	}
	
	public int getGame_count() {
		return game_count;
	}
	
	public void setGame_count(int game_count) {
		this.game_count = game_count;
	}
	
	public String getResult_answer() {
		return result_answer;
	}
	
	public void setResult_answer(String result_answer) {
		this.result_answer = result_answer;
	}
	
	public String getResult_content() {
		return result_content;
	}
	
	public void setResult_content(String result_content) {
		this.result_content = result_content;
	}
	
	public LocalDateTime getRec_create_date() {
		return rec_create_date;
	}
	
	public void setRec_create_date(LocalDateTime rec_create_date) {
		this.rec_create_date = rec_create_date;
	}
	
	public LocalDateTime getRec_update_date() {
		return rec_update_date;
	}
	
	public void setRec_update_date(LocalDateTime rec_update_date) {
		this.rec_update_date = rec_update_date;
	}
    
}
