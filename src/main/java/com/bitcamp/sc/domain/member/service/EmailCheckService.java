package com.bitcamp.sc.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.domain.member.repository.MemberDao;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailCheckService {
	
	private final SqlSessionTemplate template;
	
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