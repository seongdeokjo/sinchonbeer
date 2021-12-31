package com.bitcamp.sc.util.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bitcamp.sc.util.interceptor.LoggerInterceptor;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	LoggerInterceptor loggerInterceptor = new LoggerInterceptor();
		registry.addInterceptor(loggerInterceptor).addPathPatterns(loggerInterceptor.loginEssential).excludePathPatterns(loggerInterceptor.loginInessential);
		
	}
	
}
