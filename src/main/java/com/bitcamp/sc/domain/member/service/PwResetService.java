package com.bitcamp.sc.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.domain.member.repository.MemberDao;

@Slf4j
@Service
@RequiredArgsConstructor
public class PwResetService {

	private final SqlSessionTemplate template;
	private final PasswordEncoder passwordEncoder;
	
	public Boolean updateNewPw(String userEmail, String newPw) {
		
		Boolean result = false;
		int resultCnt = template.getMapper(MemberDao.class).updatePw(userEmail, passwordEncoder.encode(newPw));
		if(resultCnt==1) {
			result = true;
		}
		return result;
	}
	
}
