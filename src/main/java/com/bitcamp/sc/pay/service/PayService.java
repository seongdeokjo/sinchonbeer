package com.bitcamp.sc.pay.service;

import java.util.List;

import com.bitcamp.sc.pay.domain.PayInfo;

public interface PayService {
	
	public int savePayment(PayInfo payInfo);
	public PayInfo getPayInfo(int payIdx);
	public List<PayInfo> getPayInfos(int memberIdx);
	public List<PayInfo> getPayInfosByType(String type, int memberIdx);
	public PayInfo getPayInfoByOrderIdx(int orderIdx);

}
