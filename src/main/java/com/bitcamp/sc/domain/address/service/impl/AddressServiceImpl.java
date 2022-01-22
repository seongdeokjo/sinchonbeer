package com.bitcamp.sc.domain.address.service.impl;

import com.bitcamp.sc.domain.address.domain.Address;
import com.bitcamp.sc.domain.address.repository.AddressDao;
import com.bitcamp.sc.domain.address.service.AddressService;
import com.bitcamp.sc.web.address.dto.MemberAddress;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressDao addressDao;

    @Override
    public MemberAddress getAddressByMidx(long idx) {
        Address address = addressDao.findAddressByMidx(idx);
        return new MemberAddress(address);
    }
}
