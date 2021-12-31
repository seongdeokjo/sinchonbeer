package com.bitcamp.sc.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.member.repository.MemberDao;

@Service
public class PwResetService {

	@Autowired
	private SqlSessionTemplate template;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Boolean updateNewPw(String userEmail, String newPw) {
		
		Boolean result = false;
		int resultCnt = template.getMapper(MemberDao.class).updatePw(userEmail, passwordEncoder.encode(newPw));
		if(resultCnt==1) {
			result = true;
		}
		return result;
	}
	
}
