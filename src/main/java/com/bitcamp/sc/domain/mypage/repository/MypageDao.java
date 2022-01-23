package com.bitcamp.sc.domain.mypage.repository;

import com.bitcamp.sc.domain.member.domain.Member;
import com.bitcamp.sc.domain.mypage.domain.OrderList;
import com.bitcamp.sc.domain.mypage.domain.RezList;
import com.bitcamp.sc.web.mypage.dto.EditMemberRequest;

import java.util.List;

public interface MypageDao {
    List<RezList> getRezList(long idx);

    List<OrderList> getOrderList(long idx);

    Member findMemberAddressByMidx(long idx);
    int updateMember(Member member);
    int findOrderGoodsCount(long idx);
    int findOrderTourCount(long idx);
}
