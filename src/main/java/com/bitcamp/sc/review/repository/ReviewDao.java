package com.bitcamp.sc.review.repository;

import java.util.List;

import com.bitcamp.sc.review.domain.ReviewVO;
/*import com.bitcamp.sc.review.domain.Review;*/


public interface ReviewDao {
	
	// 01. 게시글 작성
    public int insertReview(ReviewVO vo) throws Exception;
    // 02. 게시글 상세보기
    public ReviewVO readReview(Integer idx) throws Exception;
    // 03. 게시글 수정
    public int updateReview(ReviewVO vo) throws Exception;
    // 04. 게시글 삭제
    public void deleteReview(Integer idx) throws Exception;
    // 05. 게시글 전체 목록
    public List<ReviewVO> listAllReview() throws Exception;
    // 06. 게시글 좋아요
    public int likeReview(ReviewVO vo) throws Exception;
    
	/*
	 * // 06. 게시글 조회 증가 
	 * public void increaseViewcnt(Integer idx) throws Exception;
	 */
	
    
    
    
	/* 
	 * // 01. 게시글 작성 
	 * public int create(Review vo) throws Exception; 
	 * // 02. 게시글 상세보기 
	 * public Review read(Integer idx) throws Exception; 
	 * // 03. 게시글 수정 
	 * public void update(Review vo) throws Exception; 
	 * // 04. 게시글 삭제 
	 * public void delete(Integer idx) throws Exception; 
	 * // 05. 게시글 전체 목록 
	 * public List<Review> listAll() throws Exception; 
	 * // 06. 게시글 조회 증가 
	 * public void increaseViewcnt(int idx) throws Exception; 
	 */
    
    
    
    
}
