package com.bitcamp.sc.util.configuration;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

//@Configuration
@EnableAsync // 비동기 처리 사용가는 어노테이션
public class AsyncConfiguration implements AsyncConfigurer {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	@Bean(name = "mailExecutor")
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(5);
		executor.setQueueCapacity(10);
		executor.setThreadNamePrefix("MailExecutor-");
		executor.initialize();
		return executor; 
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return (ex, method, params) ->
		logger.error("Exception handler for async method '"+ method.toGenericString()+ "'throw unexpected excpetion itself",ex);
	}

}
