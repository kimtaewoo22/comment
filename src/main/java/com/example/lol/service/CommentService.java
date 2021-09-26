package com.example.lol.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lol.mapper.CommentMapper;
import com.example.lol.model.Category;

@Service
public class CommentService {
	
	@Autowired
	CommentMapper commentMapper;

	public Map<String, Object> getUser(Map<String, Object> params) {
		System.out.println("getUser() start....................");
		
		Category category=new Category();
		category.setCategoryId(0);
		
		
		Category category2 = Category.builder()
				.categoryId(0)
				.categoryNm("aa")
				.build();
		
		
		
		return commentMapper.getUser(params);
	}
}
