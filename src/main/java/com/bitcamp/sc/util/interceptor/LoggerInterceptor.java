package com.bitcamp.sc.util.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bitcamp.sc.domain.member.domain.LoginInfo;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {


	public List<String> loginEssential = Arrays.asList("/tour/reserve/form","/tour/change-info","/shop/shop_payment","/basket/basket");

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.info("===============================================");
		log.info("==================== BEGIN ====================");
		log.info("Request URI ===> " + request.getRequestURI());
		LoginInfo info = (LoginInfo)request.getSession().getAttribute("loginInfo");
		if(info !=null) {
			return true;
		}else {
            response.sendRedirect("/login");
            return false;
		}
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("==================== END ======================");
		log.info("===============================================");
	}
}