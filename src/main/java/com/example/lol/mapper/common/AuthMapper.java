package com.example.lol.mapper.common;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
	
	public Map<String, Object> selectToken(String token);
}
