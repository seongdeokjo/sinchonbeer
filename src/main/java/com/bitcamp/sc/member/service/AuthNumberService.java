package com.bitcamp.sc.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.member.repository.MemberDao;

@Service
public class AuthNumberService {
	
	@Autowired
	private SqlSessionTemplate template;
	public Boolean checkAuthNum(String inputNum, String email) {
		
		Boolean result = false;
		String memberCode =  template.getMapper(MemberDao.class).selectCode(email).getCode();
		
		if(memberCode.equals(inputNum)) {
			result=true;
		}
		System.out.println("일치한다면 true 반환 : "+ result);
		
		return result;
	}

}
