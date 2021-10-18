package com.example.lol.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lol.config.exception.ServiceException;
import com.example.lol.mapper.CommunityMapper;
import com.example.lol.mapper.ContentsMapper;
import com.example.lol.model.Community;
import com.example.lol.model.Contents;
import com.example.lol.model.common.ResVO;
import com.example.lol.model.common.ResultCode;

@Service
public class ContentsService {
	
	@Autowired
	ContentsMapper contentsMapper;
	
	@Autowired
	CommunityMapper communityMapper;
	
	public ResVO createContents(long communityId, Contents contents) {
		
		Community community  = Community.builder()
								.communityId(communityId)
								.build();
		
		Boolean isCommunity = communityMapper.isCommunity(community);
		
		if(!isCommunity) {
			throw new ServiceException(ResultCode.ERROR_1002);
		}
		
		contents.setCommunityId(communityId);
		
		try {
			contentsMapper.insertContents(contents);
		}catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		return ResVO.builder()
				.data(contents)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO modifyContents(long contentsId,long communityId,Contents contents) {

		contents.setContentsId(contentsId);
		contents.setCommunityId(communityId);
		
		Boolean isContents = contentsMapper.isContents(contents);
 		
		if(!isContents) {
			throw new ServiceException(ResultCode.ERROR_2000);
		}
		
		try {
			contentsMapper.updateContents(contents);
		}catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		return ResVO.builder()
				.data(contents)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO deleteContents(long contentsId,long communityId) {
		
		Contents contents = Contents.builder()
				.contentsId(contentsId)
				.communityId(communityId)
				.build();
		
		Boolean isContents = contentsMapper.isContents(contents);
 		
		if(!isContents) {
			throw new ServiceException(ResultCode.ERROR_2000);
		}
		
		try {
			contentsMapper.deleteContents(contents);
		}catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		return ResVO.builder()
				.data(contents)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO getContentsList(long communityId){
		
		List<Map<String, Object>> contentsList = new ArrayList<Map<String,Object>>();
		
		Community community = Community.builder()
								.communityId(communityId)
								.build();
		
		Boolean isCommunity = communityMapper.isCommunity(community);
 		
		if(!isCommunity) {
			throw new ServiceException(ResultCode.ERROR_1002);
		}
		
		Contents contents = Contents.builder()
				.communityId(communityId)
				.build();
		
		try {
			contentsList = contentsMapper.selectContents(contents);
		}catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		return ResVO.builder()
				.data(contentsList)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO getContentsDetail(long contentsId,long communityId){
		
		Contents contents = Contents.builder()
				.contentsId(contentsId)
				.communityId(communityId)
				.build();
		
		Map<String, Object> contentsMap = new HashMap<String, Object>();
		Boolean isContents = contentsMapper.isContents(contents);
 		
		if(!isContents) {
			throw new ServiceException(ResultCode.ERROR_2000);
		}
		
		try {
			contentsMap = contentsMapper.selectContentsDetail(contents);
		}catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		return ResVO.builder()
				.data(contentsMap)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
}
