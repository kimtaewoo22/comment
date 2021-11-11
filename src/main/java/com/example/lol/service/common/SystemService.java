package com.example.lol.service.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lol.config.exception.ServiceException;
import com.example.lol.mapper.LoginMapper;
import com.example.lol.mapper.UserMapper;
import com.example.lol.mapper.common.AuthMapper;
import com.example.lol.model.User;
import com.example.lol.model.common.ResVO;
import com.example.lol.model.common.ResultCode;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SystemService {
	
	@Autowired
	LoginMapper loginMapper;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	AuthMapper authMapper;
	
	@Autowired
	CommonService commonService;
	
	public ResVO tokenService(User user) {
		Map<String, Object> uuidMap = new HashMap<String, Object>();
		
		try {
			//uuid 생성
			String uuid = UUID.randomUUID().toString();
			uuidMap.put("uuid", uuid);
			
			user.setToken(uuid);
			loginMapper.userUuid(user);
			
		}catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			log.error("LoginService Exception{}"+e.getMessage());
		}
		
		return ResVO.builder()
				.data(uuidMap)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public void verifyToken(String token) {
		Map<String, Object> tokenInfo = authMapper.selectToken(token);
		
		if(tokenInfo == null) {
			log.error("토큰정보 없습니다.");
			throw new ServiceException(ResultCode.ERROR_5000);
		}else {
			String tokenData = tokenInfo.get("tokenTime").toString();
			Date tokenTime = commonService.StringToDateCasting(tokenData);
			boolean isTokenTime = commonService.currentTimeCompare(tokenTime);
			
			if(!isTokenTime) {
				log.error("토큰 만료 되었습니다.");
				throw new ServiceException(ResultCode.ERROR_5001);
			}else {
				log.debug("토큰 유효 합니다.");
			}
		}
	}
}
