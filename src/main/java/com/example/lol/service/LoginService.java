package com.example.lol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lol.config.exception.ServiceException;
import com.example.lol.mapper.LoginMapper;
import com.example.lol.mapper.UserMapper;
import com.example.lol.model.User;
import com.example.lol.model.common.ResVO;
import com.example.lol.model.common.ResultCode;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginService {
	
	@Autowired
	LoginMapper loginMapper;
	
	@Autowired
	UserMapper userMapper;
	
	public ResVO loginService(User user) {
		try {
			boolean isLogin = loginMapper.isLogin(user);
			if(!isLogin) {
				throw new ServiceException(ResultCode.ERROR_9000);
			}
			
			boolean isLoginPw = loginMapper.isLoginPw(user);
			if(!isLoginPw) {
				throw new ServiceException(ResultCode.ERROR_9001);
			}
			
		}catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			log.error("LoginService Exception{}"+e.getMessage());
		}
		
		return ResVO.builder()
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
}
