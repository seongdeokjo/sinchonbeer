package com.bitcamp.sc.web.review.dto;

import com.bitcamp.sc.domain.review.domain.ReviewVO;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReviewSaveDto {

    private String title;   // 제목
    private String author;  // 작성자
    private String content; // 내용
    private String category;    // 카테고리
    private MultipartFile file; // 파일
    private Integer star; // 평점

    @Builder
    public ReviewVO toReview(){
        return ReviewVO.builder()
                .title(title)
                .contents(content)
                .name(author)
                .rate(star)
                .category(category)
                .build();
    }
}
