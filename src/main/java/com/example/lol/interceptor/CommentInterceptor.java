package com.example.lol.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.lol.service.common.SystemService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommentInterceptor implements HandlerInterceptor {
	
	@Autowired
	SystemService systemService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("preHandle start.....................{}",request.getHeader("accessToken"));
		String token = request.getHeader("accessToken");
		systemService.verifyToken(token);
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.debug("postHandle start.....................");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
}
