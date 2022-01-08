package com.bitcamp.sc.domain.member.service;

import com.bitcamp.sc.web.member.dto.RegRequest;

public interface MemberRegService {
     boolean saveMember(RegRequest regRequest) ;
}