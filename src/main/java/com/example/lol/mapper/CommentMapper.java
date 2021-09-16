package com.example.lol.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
	
	public Map<String, Object> getUser(Map<String, Object> params);
	
}
