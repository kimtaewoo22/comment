package com.example.lol.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lol.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentMapper commentMapper;

	public Map<String, Object> getUser(Map<String, Object> params) {
		return commentMapper.getUser(params);
	}
	
	
}
