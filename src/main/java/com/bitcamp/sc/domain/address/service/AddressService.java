package com.bitcamp.sc.domain.address.service;

import com.bitcamp.sc.web.address.dto.MemberAddress;

public interface AddressService {
    MemberAddress getAddressByMidx(long idx);
}
