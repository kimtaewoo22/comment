package com.example.lol.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.lol.model.Community;

@Mapper
public interface CommunityMapper {
	
	public int insertCommunity(Community community);
	
	public int updateCommunity(Community community);
	
	public int deleteCommunity(Community community);
	
	public List<Map<String, Object>> selectCommunity(Community community);
	
	public Map<String,Object> selectCommunityDetail(Community community);
	
	public Boolean isCommunity(Community community);
}
