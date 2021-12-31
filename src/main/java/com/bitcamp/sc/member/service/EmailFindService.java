package com.bitcamp.sc.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.member.domain.Member;
import com.bitcamp.sc.member.repository.MemberDao;

@Service
public class EmailFindService {

	@Autowired
	private SqlSessionTemplate template;
	
	public String emailSearch(String name, String phone) {
		String resultEmail = null;
		try {
		Member member = template.getMapper(MemberDao.class).emailSearch(name, phone);
		System.out.println("member 객체 : "+member);
		
		resultEmail = member.getEmail();
		}catch(NullPointerException e) {
			e.printStackTrace();
			System.out.println("찾으려는 이메일이 없을 경우 발생하는 예외");
		}
		return resultEmail;
	}
	
	
}
