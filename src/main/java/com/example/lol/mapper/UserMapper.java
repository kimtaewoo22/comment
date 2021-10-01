package com.example.lol.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.lol.model.User;

@Mapper
public interface UserMapper {
	public int insertUser(User user);
	public int updateUser(User user);
	public int deleteUser(User user);
	public List<Map<String, Object>> selectUser();
	public Map<String, Object> selectUserDetail(User user);
}
