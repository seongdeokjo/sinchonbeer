package com.bitcamp.sc.domain.review.repository.impl;

import java.util.List;

import com.bitcamp.sc.domain.review.repository.ReviewDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitcamp.sc.domain.review.domain.ReviewVO;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ReviewDaoImpl implements ReviewDao {
	  private static final String NAME_SPACE = "ReviewMapper";
	  private final SqlSessionTemplate template;

	@Override
	public List<ReviewVO> findAll() {
		return template.selectList(NAME_SPACE+".findAll");
	}

	@Override
	public void save(ReviewVO reviewVO) {
		template.insert(NAME_SPACE+".save",reviewVO);
	}
}