package com.bitcamp.sc.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.member.repository.MemberDao;

@Service
public class PwFindService {

	@Autowired
	private SqlSessionTemplate template;
	
	public String pwSearch(String name, String email) {
		String resultEmail = null;
		try {
		resultEmail = template.getMapper(MemberDao.class).pwSearch(name, email).getEmail();
		}catch(NullPointerException e) {
			e.printStackTrace();
			System.out.println("찾으려는 이메일이 없을 경우 발생하는 예외");
		}
		return resultEmail;
	}
}
