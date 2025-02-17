package com.example.number.dto;

import java.time.LocalDateTime;

public class MemberDTO {
	private String member_id;
    private String member_name;
    private String member_password;
    private LocalDateTime rec_create_date;
    private LocalDateTime rec_update_date;
    
	public String getMember_id() {
		return member_id;
	}
	
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_name() {
		return member_name;
	}
	
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	
	public String getMember_password() {
		return member_password;
	}
	
	public void setMember_password(String member_password) {
		this.member_password = member_password;
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
