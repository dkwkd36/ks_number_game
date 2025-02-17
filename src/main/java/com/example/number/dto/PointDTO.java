package com.example.number.dto;

import java.time.LocalDateTime;

public class PointDTO {
	
	private String member_id;
    private int point;
    private int game_count;
    private int game_act_fig;
    private String hidden_number;
    private LocalDateTime rec_create_date;
    private LocalDateTime rec_update_date;
    
	public String getMember_id() {
		return member_id;
	}
	
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	
	public int getGame_count() {
		return game_count;
	}
	
	public void setGame_count(int game_count) {
		this.game_count = game_count;
	}
	
	public int getGame_act_fig() {
		return game_act_fig;
	}
	
	public void setGame_act_fig(int game_act_fig) {
		this.game_act_fig = game_act_fig;
	}
	
	public String getHidden_number() {
		return hidden_number;
	}
	
	public void setHidden_number(String hidden_number) {
		this.hidden_number = hidden_number;
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
