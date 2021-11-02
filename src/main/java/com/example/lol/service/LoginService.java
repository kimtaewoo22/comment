package com.example.lol.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lol.config.exception.ServiceException;
import com.example.lol.mapper.LoginMapper;
import com.example.lol.mapper.UserMapper;
import com.example.lol.model.User;
import com.example.lol.model.common.ResVO;
import com.example.lol.model.common.ResultCode;

@Service
public class LoginService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LoginMapper loginMapper;
	
	@Autowired
	UserMapper userMapper;
	
	public ResVO loginService(User user) {
		Map<String, Object> uuidMap = new HashMap<String, Object>();
		
		try {
			boolean isLogin = loginMapper.isLogin(user);
			if(!isLogin) {
				throw new ServiceException(ResultCode.ERROR_9000);
			}
			
			boolean isLoginPw = loginMapper.isLoginPw(user);
			if(!isLoginPw) {
				throw new ServiceException(ResultCode.ERROR_9001);
			}
			//uuid 생성
			String uuid = createUuidService();
			uuidMap.put("uuid", uuid);
			
			user.setToken(uuid);
			loginMapper.userUuid(user);
			
		}catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			logger.error("LoginService Exception{}"+e.getMessage());
		}
		
		return ResVO.builder()
				.data(uuidMap)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public String createUuidService() {
		return UUID.randomUUID().toString();
	}
}
