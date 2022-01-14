package com.bitcamp.sc.util.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitcamp.sc.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    public List<String> loginEssential = Arrays.asList("/tour/reserve/form", "/tour/change-info", "/shop/shop_payment", "/basket/basket, /mypage/**");

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("interceptor preHandle execute");
        log.info("Request URI ===> " + request.getRequestURI());
        log.info("query ={}", request.getQueryString());
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            log.info("미인증 사용자 요청");
            saveDest(request);
            // 로그인으로 redirect
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }

    private void saveDest(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String query = request.getQueryString();
        if (query == null || query.equals("null")) {
            query = "";
        } else {
            query = "?" + query;
        }
        if (request.getMethod().equals("GET")) {
            log.info("request.getMethod= {}", request.getMethod());
            log.info("dest : {}{}", requestURI, query);
            request.getSession().setAttribute("dest", requestURI + query);
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("interceptor postHandle execute===================");
    }
}