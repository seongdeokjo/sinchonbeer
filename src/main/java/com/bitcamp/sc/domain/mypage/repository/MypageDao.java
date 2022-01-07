package com.bitcamp.sc.domain.mypage.repository;

import com.bitcamp.sc.domain.mypage.domain.OrderList;
import com.bitcamp.sc.domain.mypage.domain.RezList;
import com.bitcamp.sc.domain.mypage.domain.UpdateMember;

import java.util.List;

public interface MypageDao {
    List<RezList> getRezList(long idx);

    List<OrderList> getOrderList(long idx);

    UpdateMember getMemberInfo(long idx);

    int updateMember(UpdateMember member);
}
