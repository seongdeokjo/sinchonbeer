package com.bitcamp.sc.member.repository;

import org.apache.ibatis.annotations.Insert;

import com.bitcamp.sc.member.domain.Member;
import com.bitcamp.sc.member.domain.MemberAddress;

public interface MemberDao {

	//로그인 하기
	Member selectByEmailPw(String email, String pw);
	//이메일로 회원정보 불러오기
	Member selectByEmail(String email);
	//회원가입
	int insertMember(Member member);
	//이메일(아이디) 중복 체크
	int selectByEmail2(String email);
	
	//회원 주소 입력
	@Insert("insert into address (midx, postcode, address1, address2) values(#{midx}, #{postcode}, #{address1}, #{address2})")
	int insertAddress(MemberAddress memberAddress);
	
	//이메일 찾기 (멤버 객체)
	Member emailSearch(String name, String phone);

	//비밀번호 찾기 (멤버 객체)
	Member pwSearch(String name, String email);
	
	//비밀번호 찾기 - 인증번호 저장하기.
	int updateCode(String code, String email);
	
	//비밀번호 찾기 - 인증번호 조회하기.
	Member selectCode(String email);
	
	//비밀번호 찾기 - 비밀번호 재설정
	int updatePw(String email, String pw);
	
	//멤버의 idx로 멤버 정보 조회하기
	Member selectByMidx(int idx);
	
	//멤버의 idx로 주소 조회하기.
	MemberAddress selectAddressByMidx(int idx);
	
	//회원 수정 에서 기존 비밀번호 확인하기
	String selectPw(int midx);
}
