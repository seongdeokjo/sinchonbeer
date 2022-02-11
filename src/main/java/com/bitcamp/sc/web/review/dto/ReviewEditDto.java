package com.bitcamp.sc.web.review.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReviewEditDto {
    private String title;   // 제목
    private String content;    // 내용
    private String category;
    private int star;       // 평점
    private MultipartFile editFile;
}