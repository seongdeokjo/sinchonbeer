package com.bitcamp.sc.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.member.repository.MemberDao;

@Service
public class EmailCheckService {
	
	@Autowired
	private SqlSessionTemplate template;
	
	//이메일이 중복이면  "Y", 중복되지 않았으면 "N"를 반환
	public String emailCheck(String email) {
		String result="Y";
		int cnt = template.getMapper(MemberDao.class).selectByEmail2(email);
		
		if(cnt>0) {
			result="N";
		}
		return result;
	}
}
