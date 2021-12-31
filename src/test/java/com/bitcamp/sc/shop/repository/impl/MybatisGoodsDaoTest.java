//package com.bitcamp.sc.shop.repository.impl;
//
//
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.bitcamp.sc.shop.domain.GoodsVO;
//
//@SpringBootTest
//public class MybatisGoodsDaoTest {
//
//	
//	@Autowired
//	MybatisGoodsDao dao;
//	
//	@Test
//	@Transactional
//	@Rollback(true)
//	void 상품등록() {
//			
//			
//			GoodsVO vo = new GoodsVO("김부각", 12100, "김부각사진", "맛있는 김부각"); // gname, price, gphoto, gtext
//			
//			// when
//			dao.insertGoods(vo);
//
//			
//			
//			int idx = vo.getIdx();
//
//			assertThat(dao.insertGoods(vo).getGname()).isEqualTo(vo.getGname());
//			assertThat(dao.insertGoods(vo).getPrice()).isEqualTo(vo.getPrice());
//			assertThat(dao.insertGoods(vo).getPhoto()).isEqualTo(vo.getPhoto());
//			assertThat(dao.insertGoods(vo).getText()).isEqualTo(vo.getText());
//		
//		
//		
//		
//	}
//	// 이부분 뭔가잘못됨...
//	@Test
//	@Transactional
//	@Rollback(true)
//	void 상품삭제() {
//		
////		GoodsVO vo = new GoodsVO(2, "김부각",12100, "김부각사진", "맛있는 김부각");
////		
////		dao.insertGoods(vo);
//		
//		
//		int idx=2;
//		
//		dao.deleteGoods(idx);
//		
//		
//	}
//	
//	@Test
//	@Transactional
//	@Rollback(true)
//	void 상품조회() {
//		
//		GoodsVO vo = new GoodsVO("김부각" ,12100,"김부각 사진", "맛있는 김부각 입니다.");
//		
//		dao.insertGoods(vo);
//		
//		GoodsVO findVo;
//		findVo = dao.findIdx(vo.getIdx());
//		
//		assertThat(findVo.getGname()).isEqualTo(vo.getGname());
//		assertThat(findVo.getPrice()).isEqualTo(vo.getPrice());
//		
//		
//	}
//	
//	
//	
//}