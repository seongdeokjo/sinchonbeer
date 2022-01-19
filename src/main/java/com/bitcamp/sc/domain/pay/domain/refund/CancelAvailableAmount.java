package com.bitcamp.sc.domain.pay.domain.refund;

import lombok.Data;

@Data
public class CancelAvailableAmount {
    private int total;
    private int tax_free;
    private int vat;
    private int point;
    private int discount;
}
