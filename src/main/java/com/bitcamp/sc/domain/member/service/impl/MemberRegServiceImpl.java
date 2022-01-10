package com.bitcamp.sc.domain.member.service.impl;

import com.bitcamp.sc.domain.address.domain.Address;
import com.bitcamp.sc.domain.address.repository.AddressDao;
import com.bitcamp.sc.domain.member.domain.Member;
import com.bitcamp.sc.domain.member.repository.MemberDao;
import com.bitcamp.sc.domain.member.service.MemberRegService;
import com.bitcamp.sc.web.member.dto.RegRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberRegServiceImpl implements MemberRegService {
    private final MemberDao memberDao;
    private final AddressDao addressDao;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public boolean saveMember(RegRequest regRequest) {
        boolean result = false;
        // 회원등록 - toMember(): email, pw, name, phone

        Member regMember = regRequest.toMember();
        //암호화
        regMember.encryptionPw(passwordEncoder, regMember.getPw());
        memberDao.save(regMember);
        if (regMember != null) {
            log.info("member = {}", regMember);
            // 사용자가 입력한 주소등록
            result = saveAddress(regRequest.toAddress(), regMember);
        }
        return result;
    }

    // 주소 등록
    private boolean saveAddress(Address regAddress, Member member) {
        boolean result = false;
        if (regAddress.formValidate()) {
            regAddress.changeMemberInfo(member);
            addressDao.save(regAddress);
            if (regAddress != null) {
                log.info("newAddress ={}",regAddress);
                result = true;
            }
        }
        return result;
    }

}