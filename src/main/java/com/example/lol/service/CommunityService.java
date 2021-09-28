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
	
	public Community createCommunity(Community community) {
			communityMapper.insertCommunity(community);
		return community;
	}
	
	public Community modifyCommunity(Community community) {
			communityMapper.updateCommunity(community);
		return community;
	}
	
	public Community deleteCommunity(Community community) {
			communityMapper.deleteCommunity(community);
		return community;
	}
	
	public List<Map<String, Object>> getCommunity(Community community){
		return communityMapper.selectCommunity(community);
	}
	
	public Map<String, Object> getCommunityDetail(Community community){
		return communityMapper.selectCommunityDetail(community);
	}
}
