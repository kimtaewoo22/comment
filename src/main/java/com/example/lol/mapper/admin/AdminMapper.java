package com.example.lol.mapper.admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.lol.model.User;

@Mapper
public interface AdminMapper {
	
	public List<Map<String, Object>> selectUser(Map<String, Object> pageMap);
	
	public Map<String, Object> selectUserDetail(long userId);
	
}
