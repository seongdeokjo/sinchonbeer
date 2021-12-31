package com.bitcamp.sc.tour.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bitcamp.sc.tour.repository.TourDao;

@Repository
public class MybatisTourDao implements TourDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String NAME_SPACE = "TourMapper";

	private final SqlSessionTemplate template;

	public MybatisTourDao(SqlSessionTemplate template) {
		this.template = template;
	}

	// 선택한 날짜의 예약 가능한 인원을 가져오는 메서드
	@Override
	public int selectCount(String date) {
		return template.selectOne(NAME_SPACE + ".selectCount", date);
	}
	
	// 투어 예약 날짜로 투어 번호 가져오기
	@Override
	public int getTidxbyTdate(String date) {
		return template.selectOne(NAME_SPACE+".getTidxByTdate",date);
	}

	// 새로운 예약, 예약 변경시 바뀌는 날짜
	@Override
	public int addTourPeople(int tourPeople, String newDate) {
		System.out.println("인원 더하기");
		Map<String, Object> params = new HashMap<>();
		params.put("tourPeople", tourPeople);
		params.put("tdate", newDate);
		return template.update(NAME_SPACE + ".addTourPeople", params);
	}

	// 예약 취소 , 예약 변경시 기존 날짜
	@Override
	public int subTourPeople(int tourPeople, String currnetDate) {
		logger.info("인원 감소");
		Map<String, Object> params = new HashMap<>();
		logger.info(tourPeople+" : "+currnetDate);
		params.put("tourPeople", tourPeople);
		params.put("tdate", currnetDate);
		
		return template.update(NAME_SPACE + ".subTourPeople", params);

	}

	// 주문 테이블의 예약 날짜를 변경 --> 현재는 test code
	@Override
	public int changeDateByMidx(int oidx, String newDate) {
		Map<String, Object> params = new HashMap<>();
		params.put("oidx", oidx);
		params.put("tdate", newDate);
		return template.update(NAME_SPACE + ".changeDateByMidx", params);
		
	}

	public String getTourDateByTidx(int tidx) {
		return template.selectOne(NAME_SPACE+".getTourDateByTidx",tidx);
	}

	@Override
	public int modifyTour(int tourPeople, String newDate, String resDate) {
		Map<String, Object> params = new HashMap<>();
		params.put("tourPeople", tourPeople);
		params.put("newDate", newDate);
		params.put("resDate",resDate);
	return	template.update(NAME_SPACE+".changeTour", params);
	}
}