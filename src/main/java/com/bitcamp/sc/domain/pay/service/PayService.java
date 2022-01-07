package com.bitcamp.sc.domain.pay.service;

import java.util.List;

import com.bitcamp.sc.domain.pay.domain.PayInfo;

public interface PayService {
	
	 long savePayment(PayInfo payInfo);
	 PayInfo getPayInfo(long payIdx);
	 List<PayInfo> getPayInfos(long memberIdx);
	 List<PayInfo> getPayInfosByType(String type, long memberIdx);
	 PayInfo getPayInfoByOrderIdx(long orderIdx);

}
