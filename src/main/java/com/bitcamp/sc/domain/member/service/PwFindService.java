package com.bitcamp.sc.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.domain.member.repository.MemberDao;

@Slf4j
@Service
@RequiredArgsConstructor
public class PwFindService {

	private final SqlSessionTemplate template;
	
	public String pwSearch(String name, String email) {
		String resultEmail = null;
		try {
		resultEmail = template.getMapper(MemberDao.class).pwSearch(name, email).getEmail();
		}catch(NullPointerException e) {
			e.printStackTrace();
			log.info("찾으려는 이메일이 없을 경우 발생하는 예외");
		}
		return resultEmail;
	}
}
