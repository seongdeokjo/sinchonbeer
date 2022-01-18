package com.bitcamp.sc.web.error.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController{

		private final Logger logger = LoggerFactory.getLogger(this.getClass());
		
		private final String ERROR_404_PAGE_PATH = "errors/404"; 
		private final String ERROR_500_PAGE_PATH = "errors/500";

		
		@GetMapping("/error")
	    public String handleError(HttpServletRequest request) {
	        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	      
	       
	        if (status != null) {
	            int statusCode = Integer.valueOf(status.toString());
	            logger.info("httpStatus : "+statusCode);
	            
	            if (statusCode == HttpStatus.NOT_FOUND.value()) {
	                return ERROR_404_PAGE_PATH;
	            }
	            if(statusCode == HttpStatus.FORBIDDEN.value()) {
	            	return ERROR_500_PAGE_PATH;
	            }
	        }
	        return "error";
		}
}
