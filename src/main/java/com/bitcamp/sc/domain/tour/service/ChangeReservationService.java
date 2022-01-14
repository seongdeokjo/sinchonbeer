package com.bitcamp.sc.domain.tour.service;

import com.bitcamp.sc.domain.tour.repository.TourDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.web.tour.dto.ChangeTourDto;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChangeReservationService {

	private final TourDao tDao;

	// 주문 테이블의 투어 날짜 변경 후 투어 날짜별 현재인원 증가 / 감소 변경
	public boolean changeTourOrder(ChangeTourDto changeDto) {
		boolean result = false;
		log.info("changeDto: " + changeDto.toString());
		int result1 = tDao.changeDateByMidx(changeDto.getOidx(), changeDto.getNewDate());
		log.info("result1 값 :  " + result1);
		if (result1 == 1) {
			result = (modifyPeople(changeDto) == 2) ? true : false;
			log.info("인원 변경 결과  :" + result);
		}
		return result;
	}

	// 변경된 날짜와 기존 날짜 인원 증가,감소 확인
	private int modifyPeople(ChangeTourDto changeDto) {
		return tDao.modifyTour(changeDto.getTourPeople(), changeDto.getNewDate(), changeDto.getResDate());
	}
}