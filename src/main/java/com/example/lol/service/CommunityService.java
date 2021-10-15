package com.example.lol.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lol.config.exception.ServiceException;
import com.example.lol.mapper.CategoryMapper;
import com.example.lol.mapper.CommunityMapper;
import com.example.lol.mapper.UserMapper;
import com.example.lol.model.Community;
import com.example.lol.model.common.ResVO;
import com.example.lol.model.common.ResultCode;

@Service
public class CommunityService {
	
	@Autowired
	CommunityMapper communityMapper;
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired
	UserMapper userMapper;
	
	public ResVO createCommunity(long categoryId,Community community) {
		
		Boolean isCategoryId = categoryMapper.isCategoryId(categoryId);
		Boolean isUserId = userMapper.isUserId(community.getCreateId());
		
		if(!isCategoryId) {
			throw new ServiceException(ResultCode.ERROR_1000);
		}
		
		if(!isUserId) {
			throw new ServiceException(ResultCode.ERROR_1001);
		}
		
		try {
			community.setCategoryId(categoryId);
			communityMapper.insertCommunity(community);
		} catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		} catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
			
		return ResVO.builder()
				.data(community)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO modifyCommunity(long communityId,long categoryId,Community community) {
		
		Boolean isCommunityId = communityMapper.isCommunityId(communityId);
		Boolean isCategoryId = categoryMapper.isCategoryId(categoryId);
		Boolean isUserId = userMapper.isUserId(community.getModifyId());
		
		if(!isCommunityId) {
			throw new ServiceException(ResultCode.ERROR_1002);
		}
		if(!isCategoryId) {
			throw new ServiceException(ResultCode.ERROR_1000);
		}
		if(!isUserId) {
			throw new ServiceException(ResultCode.ERROR_1001);
		}
		
		try {
			community.setCommunityId(communityId);
			community.setCategoryId(categoryId);
			communityMapper.updateCommunity(community);
		}catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		return ResVO.builder()
				.data(community)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO deleteCommunity(long communityId,long categoryId) {
		
		Boolean isCommunityId = communityMapper.isCommunityId(communityId);
		Boolean isCategoryId = categoryMapper.isCategoryId(categoryId);
		
		if(!isCommunityId) {
			throw new ServiceException(ResultCode.ERROR_1002);
		}
		if(!isCategoryId) {
			throw new ServiceException(ResultCode.ERROR_1000);
		}
		
		Community community = Community.builder()
				.communityId(communityId)
				.categoryId(categoryId)
				.build();
		try {
			communityMapper.deleteCommunity(community);
		} catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		} catch (Exception e){
			System.out.println("error :"+e.getMessage());
		}
		
		return ResVO.builder()
				.data(community)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO getCommunity(long categoryId){
		
		List<Map<String, Object>> communityList = new ArrayList<Map<String,Object>>();
		Boolean isCategoryId = categoryMapper.isCategoryId(categoryId);
		if(!isCategoryId) {
			throw new ServiceException(ResultCode.ERROR_1000);
		}
		
		Community community = Community.builder()
				.categoryId(categoryId)
				.build();
		try {
			communityList= communityMapper.selectCommunity(community);
		} catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
	
		return ResVO.builder()
				.data(communityList)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO getCommunityDetail(long communityId,long categoryId){
		
		Map<String, Object> communityMap = new HashMap<String, Object>();
		Boolean isCommunityId = communityMapper.isCommunityId(communityId);
		Boolean isCategoryId = categoryMapper.isCategoryId(categoryId);
		
		if(!isCommunityId) {
			throw new ServiceException(ResultCode.ERROR_1002);
		}
		if(!isCategoryId) {
			throw new ServiceException(ResultCode.ERROR_1000);
		}
		
		Community community = Community.builder()
				.communityId(communityId)
				.categoryId(categoryId)
				.build();	
		try {
			communityMap = communityMapper.selectCommunityDetail(community);
		} catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		return ResVO.builder()
				.data(communityMap)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
}
