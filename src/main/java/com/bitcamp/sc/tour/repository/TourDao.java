package com.bitcamp.sc.tour.repository;

public interface TourDao {
	// 현재 인원 값을 가져오기
	int selectCount(String date);
	// 투어 예약 날짜의 투어 번호 가져오기
	int getTidxbyTdate(String date);
	// 결제 : 투어 번호로 투어 날짜 가져오기
	String getTourDateByTidx(int tidx);
	// 새로운 예약, 예약 변경
	int addTourPeople(int tourPeople,String newDate);
	// 예약 취소, 예약 변경
	int subTourPeople(int tourPeople,String currentDate);
	// 주문테이블의 tidx 변경
	int changeDateByMidx(int oidx,String newDate);
	// 예약 변경시 기존 날짜와 새롭게 예약한 날짜의 인원 처리
	int modifyTour(int tourPeople, String newDate, String resDate);
}