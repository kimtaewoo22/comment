package com.example.lol.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lol.mapper.CommunityMapper;
import com.example.lol.model.Community;

@Service
public class CommunityService {
	
	@Autowired
	CommunityMapper communityMapper;
	
	public Community createCommunity(Long categoryId,Community community) {
		
		community.setCategoryId(categoryId);
		communityMapper.insertCommunity(community);
			
		return community;
	}
	
	public Community modifyCommunity(Long communityId,Long categoryId,Community community) {
		
		community.setCommunityId(communityId);
		community.setCategoryId(categoryId);
		communityMapper.updateCommunity(community);
		
		return community;
	}
	
	public Community deleteCommunity(Long communityId,Long categoryId) {
		
		Community community = Community.builder()
				.communityId(communityId)
				.categoryId(categoryId)
				.build();
		
		communityMapper.deleteCommunity(community);
		
		return community;
	}
	
	public List<Map<String, Object>> getCommunity(Long categoryId){
		
		Community community = Community.builder()
				.categoryId(categoryId)
				.build();
		
		return communityMapper.selectCommunity(community);
	}
	
	public Map<String, Object> getCommunityDetail(Long communityId,Long categoryId){
		
		Community community = Community.builder()
				.communityId(communityId)
				.categoryId(categoryId)
				.build();	
		
		return communityMapper.selectCommunityDetail(community);
	}
}
