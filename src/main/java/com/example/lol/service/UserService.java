package com.example.lol.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lol.mapper.UserMapper;
import com.example.lol.model.User;

@Service
public class UserService {
	
	@Autowired
	UserMapper userMapper;
	
	public User createUser(User user) {
		userMapper.insertUser(user);
		return user;
	}
	
	public User modifyUser(long userId,User user) {
		
		user.setUserId(userId);
		userMapper.updateUser(user);
		
		return user;
	}
	
	public User deleteUser(long userId) {
		
		User user = User.builder()
				.userId(userId)
				.build();
		
		userMapper.deleteUser(user);
		
		return user;
	}
	
	public List<Map<String, Object>> getUserList(){
		return userMapper.selectUser();
	}
	
	public Map<String, Object> getUserDetail(long userId){
		
		User user = User.builder()
				.userId(userId)
				.build();
		
		return userMapper.selectUserDetail(user);
	}
}
