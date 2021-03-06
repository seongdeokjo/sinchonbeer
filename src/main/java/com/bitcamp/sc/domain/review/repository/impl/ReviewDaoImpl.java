package com.bitcamp.sc.domain.review.repository.impl;

import java.util.List;

import com.bitcamp.sc.domain.review.repository.ReviewDao;
import com.bitcamp.sc.web.paging.Criteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bitcamp.sc.domain.review.domain.ReviewVO;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ReviewDaoImpl implements ReviewDao {
	  private static final String NAME_SPACE = "ReviewMapper";
	  private final SqlSessionTemplate template;

	@Override
	public List<ReviewVO> findAll(Criteria cri) {
		return template.selectList(NAME_SPACE+".findAll",cri);
	}

	@Override
	public ReviewVO findByIdx(long idx) {
		return template.selectOne(NAME_SPACE+".findByIdx",idx);
	}

	@Override
	public int updateCountView(long idx) {
		return template.update(NAME_SPACE+".updateCountView",idx);
	}

	@Override
	public int getCountReview(Criteria cri) {
		return template.selectOne(NAME_SPACE+".countReview",cri);
	}

	@Override
	public void save(ReviewVO reviewVO) {
		template.insert(NAME_SPACE+".save",reviewVO);
	}

	@Override
	public void edit(ReviewVO reviewVO) {
		template.update(NAME_SPACE+".editReview",reviewVO);
	}

	@Override
	public void delete(Long idx) {
		template.delete(NAME_SPACE+".removeReview",idx);
	}
}