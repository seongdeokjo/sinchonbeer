package com.bitcamp.sc.domain.review.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewVO {
	private Integer idx;		// 게시글 번호
    private String title;		// 게시글 제목
    private String name;		// 게시글 작성자
    private String contents;	// 게시글 내용
    private Integer rate;		// 게시글 평점
    private String date;		// 게시글 작성일자 util.Date
	/*
	 * private String category; // 리뷰 카테고리 (상품구매 or 투어)
	 */
    private Integer likes;		// 게시글 좋아요
    private Integer pidx;		// 결제번호
    private String gphoto;		// 썸네일
	/*
	 * private Integer viewcnt; // 게시글 조회수
	 */
}
