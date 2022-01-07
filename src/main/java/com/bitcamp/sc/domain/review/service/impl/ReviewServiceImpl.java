package com.bitcamp.sc.domain.review.service.impl;

import java.util.List;

import com.bitcamp.sc.domain.review.domain.ReviewVO;
import com.bitcamp.sc.domain.review.repository.ReviewDao;
import com.bitcamp.sc.domain.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
    ReviewDao reviewDao;

	@Autowired
	public ReviewServiceImpl(ReviewDao dao) {
		this.reviewDao = dao;
	}

	// 01. 게시글쓰기
    @Override
    public int insertReview(ReviewVO vo) throws Exception {

    	int check = 0;

        try {
        	check = reviewDao.insertReview(vo);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return check;

    }

	// 02. 게시글 상세보기
    @Override
    public ReviewVO readReview(long idx) throws Exception {
        return reviewDao.readReview(idx);
    }


	// 03. 게시글 수정
    @Override
    public int updateReview(ReviewVO vo) throws Exception {
    	int check = 0;
        try {
        	check = reviewDao.updateReview(vo);

		} catch (Exception e) {
			// TODO: handle exception
		}
    	return check;
    }
//    // 04. 게시글 삭제
//    @Override
//    public void deleteReview(long idx) throws Exception {

//    	reviewDao.deleteReview(idx);
//    }


	@Override
	public void deleteReview(Long idx) throws Exception {
		reviewDao.deleteReview(idx);
	}


	// 05. 게시글 전체 목록

	@Override
	public List<ReviewVO> listAllReview() throws Exception {
    	return reviewDao.listAllReview();
	}

    // 06. 게시글 좋아요
	@Override
    public int likeReview(ReviewVO vo) throws Exception {
		/*
		 * int check = 0; 
		 * try { 
		 * check = reviewDao.likeReview(vo);
		 * 
		 * } catch (Exception e) { 
		 * // TODO: handle exception 
		 * }
		 * return check;
		 */
		
    	return reviewDao.likeReview(vo);
    }
	

/*	// 06. 게시글 조회수 증가
	@Override
	public void increaseViewcnt(Integer idx, HttpSession session) throws Exception {
    	long update_time = 0;
        // 세션에 저장된 조회시간 검색
        // 최초로 조회할 경우 세션에 저장된 값이 없기 때문에 if문은 실행X
        
    	if(session.getAttribute("update_time_"+idx) != null){
        	// 세션에서 읽어오기
        	update_time = (long)session.getAttribute("update_time_"+idx);
        }
    	
        // 시스템의 현재시간을 current_time에 저장
        long current_time = System.currentTimeMillis();
        
        // 일정시간이 경과 후 조회수 증가 처리 24*60*60*1000(24시간)
        // 시스템현재시간 - 열람시간 > 일정시간(조회수 증가가 가능하도록 지정한 시간)
        
        if(current_time - update_time > 5*1000){
        	reviewDao.increaseViewcnt(idx);
        	
        	// 세션에 시간을 저장 : "update_time_"+ridx는 다른변수와 중복되지 않게 명명한 것
        	
        	session.setAttribute("update_time_"+idx, current_time);
            
        }
    }
*/
 
}
