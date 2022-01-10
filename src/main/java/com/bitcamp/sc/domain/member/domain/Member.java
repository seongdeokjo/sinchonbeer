package com.bitcamp.sc.domain.member.domain;

import com.bitcamp.sc.domain.address.domain.Address;
import com.bitcamp.sc.web.login.dto.LoginInfo;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {

	private long idx;
	private String email;
	private String pw;
	private String name;
	private String phone;
	private String code;

	private Address address;
//	private MemberType role;

	public Member(long idx, String pw, String phone,Address address) {
		log.info("member 생성자 호출 ------");
		this.idx = idx;
		this.pw = pw;
		this.phone = phone;
		this.address = address;
	}

	@Builder
	public Member(String email, String pw, String name, String phone) {
		log.info("member bulider 호출");
		this.email = email;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
	}

	public LoginInfo toLoginInfo() {
		return new LoginInfo(this.idx, this.email, this.name, this.phone);
	}

	// 암호화
	public void encryptionPw(PasswordEncoder passwordEncoder, String pw) {
		this.pw = passwordEncoder.encode(pw);
	}

	// 회원_주소 변경
	public void updateAccount(String pw,String phone,Address address){
		this.pw = pw;
		this.phone = phone;
		this.address = address;
	}

}