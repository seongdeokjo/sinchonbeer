package com.bitcamp.sc.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.domain.member.domain.Member;
import com.bitcamp.sc.domain.member.repository.MemberDao;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailFindService {

	private final SqlSessionTemplate template;
	
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
