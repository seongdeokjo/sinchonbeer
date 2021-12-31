package com.bitcamp.sc.util.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bitcamp.sc.member.domain.LoginInfo;

public class LoggerInterceptor implements HandlerInterceptor {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<String> loginEssential = Arrays.asList("/tour/reserve/form","/tour/change-info","/shop/shop_payment","/basket/basket");

	public List<String> loginInessential = Arrays.asList("/tour/","/tour/info","/tour/notice","/tour/notice/{idx}","/tour/pick-date");

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.info("===============================================");
		logger.info("==================== BEGIN ====================");
		logger.info("Request URI ===> " + request.getRequestURI());
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
				
		logger.info("==================== END ======================");
		logger.info("===============================================");
	}

}
