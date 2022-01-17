package com.bitcamp.sc.domain.order.service.impl;

import java.util.List;

import com.bitcamp.sc.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.domain.order.domain.OrderInfo;
import com.bitcamp.sc.domain.order.repository.OrderDao;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    @Override
    public long createOrder(OrderInfo orderInfo) {
        validateOrderInfo(orderInfo);

        orderInfo = orderDao.save(orderInfo);

        return orderInfo.getIdx();
    }

    @Override
    public OrderInfo getOrderInfo(long orderIdx) {
        OrderInfo orderInfo = orderDao.findByIdx(orderIdx);
        return orderInfo;
    }

    @Override
    public List<OrderInfo> getOrderInfos(long memberIdx) {
        List<OrderInfo> orderInfos = orderDao.findByMemberIdx(memberIdx);

        return orderInfos;
    }

    @Override
    public List<OrderInfo> getOrderInfosByType(String type, long memberIdx) {
        return orderDao.findByCategoryAndMemberIdx(type, memberIdx);
    }

    @Override
    public int deleteOrder(long idx) {
        return orderDao.deleteByIdx(idx);
    }

    @Override
    public int changeOrderStatus(long idx, String status) {
        return orderDao.updateStatus(status, idx);
    }

    private void validateOrderInfo(OrderInfo orderInfo) {
        if (!orderInfo.getCategory().isEmpty() && orderInfo.getCategory().equals("tour")) {
            validateTourOrder(orderInfo);
        } else if (!orderInfo.getCategory().isEmpty() && orderInfo.getCategory().equals("shop")) {
            validateShopOrder(orderInfo);
        } else {
            throw new IllegalStateException("주문 정보가 누락됐습니다. 다시 입력해주세요.");
        }
    }

    private void validateTourOrder(OrderInfo orderInfo) {
        if (orderInfo.getCategory().equals("") || orderInfo.getPrice() == 0 || orderInfo.getMemberIdx() == 0
                || orderInfo.getTourIdx() == 0 || orderInfo.getTourPeople() == 0) {
            throw new IllegalStateException("여행 주문 정보가 누락됐습니다. 다시 입력해주세요.");
        }
    }

    private void validateShopOrder(OrderInfo orderInfo) {
        if (orderInfo.getCategory().equals("") || orderInfo.getPrice() == 0 || orderInfo.getMemberIdx() == 0
                || orderInfo.getAddressIdx() == 0) {
            throw new IllegalStateException("상품 주문 정보가 누락됐습니다. 다시 입력해주세요.");
        }
    }

    @Override
    public int confirmOrder(long idx) {
        return orderDao.updateStatus("confirmed", idx);
    }
}