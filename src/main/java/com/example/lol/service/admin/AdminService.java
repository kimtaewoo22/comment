package com.example.lol.service.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.lol.config.exception.ServiceException;
import com.example.lol.mapper.UserMapper;
import com.example.lol.mapper.admin.AdminMapper;
import com.example.lol.model.User;
import com.example.lol.model.common.Pagination;
import com.example.lol.model.common.ResultCode;
import com.example.lol.service.common.CommonService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminService {
	
	@Autowired
	AdminMapper adminMapper;
	
	@Autowired
	UserMapper userMapper;
	
	public ModelAndView getUserList(Map<String, Object> paramMap) {
		
		ModelAndView mv = new ModelAndView();
		List<Map<String, Object>> userList = new ArrayList<Map<String,Object>>();
		try {
			paramMap = CommonService.pageSetting(paramMap);
			userList = adminMapper.selectUser(paramMap);
			mv.setViewName("userMain");
			mv.addObject("userList", userList);
			Pagination pg = CommonService.pageTotalCountService(userList, paramMap);
			mv.addObject("pagination", pg);
		} catch (ServiceException e) {
			log.error("[getUserList] Service Exception {}" +e.getMessage());
			throw new ServiceException(ResultCode.ERROR_9999);
		} catch (Exception e) {
			log.error("[getUserList] Exception {}" +e.getMessage());
		}
		
		return mv;
	}
	
	public ModelAndView getUserDetail(long userId){
		ModelAndView mv = new ModelAndView();
		try {
			mv.addObject("userMap", adminMapper.selectUserDetail(userId));
			mv.setViewName("userDetail");
		}catch (ServiceException e) {
			log.error("[getUserDetail] Service Exception {}" +e.getMessage());
			throw new ServiceException(ResultCode.ERROR_9999);
		} 
		catch (Exception e) {
			log.error("[getUserDetail] Exception {}" +e.getMessage());
		}
		
		return mv;
	}
	
	public ModelAndView modifyUser(long userId, User user) {
		ModelAndView mv = new ModelAndView();
		try {
			user.setUserId(userId);
			userMapper.updateUser(user);
			mv.setViewName("adminMain");
		}catch (ServiceException e) {
			log.error("[getUserDetail] Service Exception {}" +e.getMessage());
			throw new ServiceException(ResultCode.ERROR_9999);
		} 
		catch (Exception e) {
			log.error("[getUserDetail] Exception {}" +e.getMessage());
		}
		
		return mv;
	}
	
	public ModelAndView deleteUser(long userId, User user) {
		ModelAndView mv = new ModelAndView();
		try {
			user.setUserId(userId);
			userMapper.deleteUser(user);
			mv.setViewName("adminMain");
		}catch (ServiceException e) {
			log.error("[getUserDetail] Service Exception {}" +e.getMessage());
			throw new ServiceException(ResultCode.ERROR_9999);
		} 
		catch (Exception e) {
			log.error("[getUserDetail] Exception {}" +e.getMessage());
		}
		
		return mv;
	}
}
