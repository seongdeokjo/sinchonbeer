package com.bitcamp.sc.domain.review.domain;

import com.bitcamp.sc.domain.comment.domain.Comment;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewVO {
    private long idx;        // 게시글 번호
    private String title;        // 게시글 제목
    private String name;        // 게시글 작성자
    private String contents;    // 게시글 내용
    private Integer rate;        // 게시글 평점
    private String date;        // 게시글 작성일자 util.Date
    private String category; // 리뷰 카테고리 (상품구매 or 투어)
    private Integer likes;        // 게시글 좋아요
    private int viewcnt;
    private String gphoto;        // 썸네일
//    private List<Comment> comments; // 댓글 idx

    @Builder
    public ReviewVO(String title, String name, String contents, Integer rate, String category){
        this.title = title;
        this.name = name;
        this.contents = contents;
        this.category = category;
        this.rate = rate;
    }

    public void setPhotoPath(String fileName){
        this.gphoto = fileName;
    }

    public void updates(String title,String contents,String category , int rate){
        this.title = title;
        this.contents = contents;
        this.category = category;
        this.rate = rate;
    }
}