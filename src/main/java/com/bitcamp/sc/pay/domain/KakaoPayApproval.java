package com.bitcamp.sc.pay.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class KakaoPayApproval {

	private String aid;
	private String tid;
	private String cid;
	private String sid;
	private String partner_order_id;
	private String partner_user_id;
	private String payment_method_type;
	private KakaoPayAmount amount;
	private KakaoCardInfo card_info;
	private String item_name;
	private String item_code;
	private String payload;
	private int quantity;
	private int tax_free_amount;
	private int vat_amount;
	private Timestamp created_at;
	private Timestamp approved_at;
	
}
