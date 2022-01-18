package com.bitcamp.sc.domain.pay.service.impl;

import java.util.List;

import com.bitcamp.sc.domain.order.service.OrderService;
import com.bitcamp.sc.domain.pay.repository.PayDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.domain.pay.domain.KakaoPayApproval;
import com.bitcamp.sc.domain.pay.domain.PayInfo;
import com.bitcamp.sc.domain.pay.service.PayService;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

	private final PayDao payDao;
	private final OrderService orderService;

	@Override
	public long savePayment(PayInfo payInfo) {
		validatePayInfo(payInfo);
//		PayInfo byIdx = payDao.findByIdx(payInfo.getIdx());
//		if(byIdx.getIdx() == 1){
//			log.info("이미 결제 정보가 존재합니다.");
//			return 0;
//		}
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
	public PayInfo getPayInfo(long payIdx) {
		PayInfo payInfo = payDao.findByIdx(payIdx);
		return payInfo;
	}

	@Override
	public List<PayInfo> getPayInfos(long memberIdx) {
		List<PayInfo> payInfos = payDao.findByMemberId(memberIdx);
		return payInfos;
	}

	@Override
	public List<PayInfo> getPayInfosByType(String type, long memberIdx) {
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
	public PayInfo getPayInfoByOrderIdx(long orderIdx) {
		PayInfo payInfo = payDao.findByOrderIdx(orderIdx);
		return payInfo;
	}
}