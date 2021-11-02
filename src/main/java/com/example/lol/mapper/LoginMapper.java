package com.example.lol.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.lol.model.User;

@Mapper
public interface LoginMapper {
	
	public boolean isLogin(User user);
	
	public boolean isLoginPw(User user);
	
	public int userUuid(User user);
}
