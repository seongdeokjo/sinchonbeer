package com.bitcamp.sc.domain.pay.domain;

import com.bitcamp.sc.domain.pay.domain.refund.ApprovedCancelAmount;
import com.bitcamp.sc.domain.pay.domain.refund.CancelAvailableAmount;
import com.bitcamp.sc.domain.pay.domain.refund.CancledAmount;
import lombok.Data;

import java.util.Date;

@Data
public class KakaoPayRefund {
    private String tid;
    private String status;
    private String partner_order_id;
    private String partner_user_id;
    private KakaoPayAmount amount;
    private ApprovedCancelAmount approved_cancel_amount;
    private CancledAmount cancled_amount;
    private CancelAvailableAmount cancel_available_amount;
    private String item_name;
    private String item_code;
    private int quantity;
    private Date approved_at;
    private Date cancled_at;
}
