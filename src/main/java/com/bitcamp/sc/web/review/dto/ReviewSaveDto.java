package com.bitcamp.sc.web.review.dto;

import com.bitcamp.sc.domain.review.domain.ReviewVO;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ReviewSaveDto {
    @NotBlank
    private String title;   // 제목
    @NotNull
    private String author;  // 작성자
    @NotBlank
    private String content; // 내용
    @NotNull
    private String category;    // 카테고리
    private MultipartFile file; // 파일
    @NotNull
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
