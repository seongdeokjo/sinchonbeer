package com.bitcamp.sc.web.shop.dto;

import com.bitcamp.sc.domain.goods.domain.Goods;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class GoodsResponse {
    private long gidx; // 상품번호
    private String name; // 상품이름
    private int price; //상품가격
    private String photo; //상품이미지
    private String title; // 상품 상세 설명

    @Builder
    public GoodsResponse(Goods entity) {
        this.gidx = entity.getIdx();
        this.name = entity.getName();
        this.photo = entity.getPhoto();
        this.price = entity.getPrice();
        this.title = entity.getTitle();
    }
}
