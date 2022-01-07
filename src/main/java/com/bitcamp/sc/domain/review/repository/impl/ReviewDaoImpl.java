package com.bitcamp.sc.domain.review.repository.impl;

import java.util.List;

import com.bitcamp.sc.domain.review.repository.ReviewDao;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitcamp.sc.domain.review.domain.ReviewVO;


@Repository
public class ReviewDaoImpl implements ReviewDao {
	
	  private static final String NAME_SPACE = "ReviewMapper";
	  
	  private final SqlSessionTemplate template;
	  
	  public ReviewDaoImpl(SqlSessionTemplate template) { 
		  this.template = template;
	  }
	
	
	@Autowired
	SqlSession SqlSession;
	
	// 01. 게시글 작성
	@Override
	public int insertReview(ReviewVO vo) throws Exception {
		
		return template.insert(NAME_SPACE + ".insertReview", vo);
	}
	
		
	
	// 02. 게시글 상세보기
	@Override
	public ReviewVO readReview(long idx) throws Exception {
    	return template.selectOne(NAME_SPACE + ".readReview", idx);
	}
    
	// 03. 게시글 수정
	@Override
	public int updateReview(ReviewVO vo) throws Exception {
		return template.update(NAME_SPACE + ".updateReview", vo);

	}
    // 04. 게시글 삭제
    @Override
    public void deleteReview(long idx) throws Exception {
    	template.delete(NAME_SPACE + ".deleteReview",idx);
 
    }
    // 05. 게시글 전체 목록
    @Override
    public List<ReviewVO> listAllReview() throws Exception {
        return template.selectList(NAME_SPACE+".listAllReview");
    }
    
    
    // 06. 게시글 좋아요
    public int likeReview(ReviewVO vo) throws Exception {
    	return template.update(NAME_SPACE + ".likeReview",vo);
    	 
    }
    
	
	/*
	 * // 게시글 조회수 증가
	 * 
	 * @Override public void increaseViewcnt(Integer idx) throws Exception {
	 * template.update(NAME_SPACE + ".increaseViewcnt", idx); }
	 * 
	 */
    
    
 
}
