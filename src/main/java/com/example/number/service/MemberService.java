package com.example.number.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.number.dao.MemberDAO;

@Service
public class MemberService {
	
	@Autowired
	private  MemberDAO memberDAO;
	
	public int login(String member_id, String member_password) {
		return memberDAO.findMemberByIdAndPassword(member_id, member_password);
    }
}
