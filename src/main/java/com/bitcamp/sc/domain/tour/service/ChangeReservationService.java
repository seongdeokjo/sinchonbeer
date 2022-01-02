package com.bitcamp.sc.domain.tour.service;

import com.bitcamp.sc.domain.tour.repository.TourDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.domain.tour.domain.ChangeTourDto;

@Service
public class ChangeReservationService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private TourDao tDao;
	
	@Autowired
	public ChangeReservationService(TourDao tDao) {
		this.tDao = tDao;
	
	}

	// 주문 테이블의 투어 날짜 변경 후 투어 날짜별 현재인원 증가 / 감소 변경
	public boolean changeTourOrder(ChangeTourDto changeDto) {
		boolean result = false;
		logger.info("changeDto: " + changeDto.toString());

		int result1 = tDao.changeDateByMidx(changeDto.getOidx(), changeDto.getNewDate());
		logger.info("result1 값 :  " + result1);

		if (result1 == 1) {
			result = (modifyPeople(changeDto) == 2) ? true : false;
			logger.info("인원 변경 결과  :" + result);
		}

		return result;
	}

	// 변경된 날짜와 기존 날짜 인원 증가,감소 확인
	private int modifyPeople(ChangeTourDto changeDto) {
		return tDao.modifyTour(changeDto.getTourPeople(), changeDto.getNewDate(), changeDto.getResDate());
	}
	
	

}
