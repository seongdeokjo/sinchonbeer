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
	public int getCountReview() {
		return template.selectOne(NAME_SPACE+".countReview");
	}

	@Override
	public void save(ReviewVO reviewVO) {
		template.insert(NAME_SPACE+".save",reviewVO);
	}
}