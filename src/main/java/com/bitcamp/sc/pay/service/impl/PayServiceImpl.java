package com.bitcamp.sc.pay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.order.service.OrderService;
import com.bitcamp.sc.pay.domain.KakaoPayApproval;
import com.bitcamp.sc.pay.domain.PayInfo;
import com.bitcamp.sc.pay.repository.PayDao;
import com.bitcamp.sc.pay.service.PayService;

@Service
public class PayServiceImpl implements PayService {

	PayDao payDao;
	OrderService orderService;

	@Autowired
	public PayServiceImpl(PayDao payDao, OrderService orderService) {
		this.payDao = payDao;
		this.orderService = orderService;
	}

	@Override
	public int savePayment(PayInfo payInfo) {
		validatePayInfo(payInfo);

		payInfo = payDao.save(payInfo);
		
		// 같은 주문 번호를 여러번 결제하는 경우 예외처리 필요

		return payInfo.getIdx();
	}

	private void validatePayInfo(PayInfo payInfo) {
		if (payInfo.getPrice() == 0 || payInfo.getWay().equals("") || payInfo.getStatus().equals("")
				|| payInfo.getOrderIdx() == 0) {
			throw new IllegalStateException("결제 정보가 누락됐습니다. 다시 입력해주세요.");
		}
	}

	@Override
	public PayInfo getPayInfo(int payIdx) {
		PayInfo payInfo = payDao.findByIdx(payIdx);
		return payInfo;
	}

	@Override
	public List<PayInfo> getPayInfos(int memberIdx) {
		List<PayInfo> payInfos = payDao.findByMemberId(memberIdx);
		return payInfos;
	}

	@Override
	public List<PayInfo> getPayInfosByType(String type, int memberIdx) {
		List<PayInfo> payInfos = payDao.findByCategoryAndMemberIdx(type, memberIdx);
		return payInfos;
	}

	public PayInfo approvalToPayInfo(KakaoPayApproval approval) {
		PayInfo payInfo = PayInfo.builder()
								 .price(approval.getAmount().getTotal())
								 .date(approval.getApproved_at())
								 .way("kakaopay")
								 .status("complete")
								 .orderIdx(Integer.parseInt(approval.getPartner_order_id()))
								 .build();
		return payInfo;
	}

	@Override
	public PayInfo getPayInfoByOrderIdx(int orderIdx) {
		PayInfo payInfo = payDao.findByOrderIdx(orderIdx);
		return payInfo;
	}

}
