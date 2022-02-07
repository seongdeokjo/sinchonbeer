package com.bitcamp.sc.web.review.dto;

import lombok.Data;

@Data
public class ReviewEditDto {
    private String title;   // 제목
    private String content;    // 내용
    private int star;       // 평점

}
