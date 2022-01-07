package com.bitcamp.sc.domain.review.service;

import java.util.List;

import com.bitcamp.sc.domain.review.domain.ReviewVO;

public interface ReviewService {

	// 01. 게시글 작성
    public int insertReview(ReviewVO vo) throws Exception;
    // 02. 게시글 상세보기
    public ReviewVO readReview(long idx) throws Exception;
    // 03. 게시글 수정
    public int updateReview(ReviewVO vo) throws Exception;
    // 04. 게시글 삭제
    public void deleteReview(Long idx) throws Exception;
    // 05. 게시글 전체 목록
    public List<ReviewVO> listAllReview() throws Exception;
    // 06. 게시글 좋아요
    public int likeReview(ReviewVO vo) throws Exception;	
    
    
	/*
	 * // 06. 게시글 조회 
	 * public void increaseViewcnt(Integer idx, HttpSession session) throws Exception;
	 */
}
