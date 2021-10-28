package com.example.lol.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lol.config.exception.ServiceException;
import com.example.lol.mapper.UserMapper;
import com.example.lol.model.User;
import com.example.lol.model.common.Pagination;
import com.example.lol.model.common.ResVO;
import com.example.lol.model.common.ResultCode;
import com.example.lol.service.common.CommonService;

@Service
public class UserService {
	
	@Autowired
	UserMapper userMapper;
	
	public ResVO createUser(User user) {
		
		try {
			
			userMapper.insertUser(user);
			
		} catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		} catch (Exception e) {
			System.out.println("error :"+ e.getMessage());
		}
		
		return ResVO.builder()
				.data(user)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO modifyUser(long userId,User user) {
		
		Boolean isUser = userMapper.isUser(userId);
		
		if(!isUser) {
			throw new ServiceException(ResultCode.ERROR_1001);
		}
		
		try {
			
			user.setUserId(userId);
			userMapper.updateUser(user);
			
		} catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		} catch (Exception e) {
			System.out.println("error :"+ e.getMessage());
		}
		
		return ResVO.builder()
				.data(user)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO deleteUser(long userId) {
		
		Boolean isUser = userMapper.isUser(userId);
		
		if(!isUser) {
			throw new ServiceException(ResultCode.ERROR_1001);
		}
		
		User user = User.builder()
				.userId(userId)
				.build();
		try {
			
			userMapper.deleteUser(user);
			
		} catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		} catch (Exception e) {
			System.out.println("error :"+ e.getMessage());
		}
		
		return ResVO.builder()
				.data(user)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO getUserList(int currentPage, int pageSize){
		
		List<Map<String, Object>> userList = new ArrayList<Map<String,Object>>();
		Map<String, Object> pageMap = CommonService.pageService(currentPage, pageSize);
		
		try {
			userList = userMapper.selectUser(pageMap);
		}catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			System.out.println("error :"+ e.getMessage());
		}
		
		Pagination pagination = CommonService.pageTotalCountService(userList, pageMap);
		
		return ResVO.builder()
				.data(userList)
				.pagingInfo(pagination)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO getUserDetail(long userId){
		
		Map<String, Object> userMap = new HashMap<String, Object>(); 
		Boolean isUser = userMapper.isUser(userId);
		
		if(!isUser) {
			throw new ServiceException(ResultCode.ERROR_1001);
		}
		
		User user = User.builder()
				.userId(userId)
				.build();
		try {
			
			userMap = userMapper.selectUserDetail(user);
			
		} catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		} catch (Exception e) {
			System.out.println("error :"+ e.getMessage());
		}
		
		return ResVO.builder()
				.data(userMap)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
}
