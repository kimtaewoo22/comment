package com.example.lol.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.lol.interceptor.CommentInterceptor;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CommentInterceptor commentInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.debug("addInterceptors.........start");
		registry.addInterceptor(commentInterceptor)
		.excludePathPatterns("/**/login")
		.excludePathPatterns("/**/token")
		.excludePathPatterns("/admin/**")
		.excludePathPatterns("/css/**","/font/**","/plugin/**","/scripts/**");
	}

}
