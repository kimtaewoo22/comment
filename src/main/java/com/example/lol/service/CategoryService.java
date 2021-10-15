package com.example.lol.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lol.config.exception.ServiceException;
import com.example.lol.mapper.CategoryMapper;
import com.example.lol.mapper.UserMapper;
import com.example.lol.model.Category;
import com.example.lol.model.common.ResVO;
import com.example.lol.model.common.ResultCode;

@Service
public class CategoryService {
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired
	UserMapper userMapper;
	
	public Category createCategory(Category category) {
		
		try {
			categoryMapper.insertCategory(category);
		} catch (Exception e) {
			System.out.println("error :"+ e.getMessage());
		}
		
		return category;
	}
	
	public ResVO modifyCategory(Long categoryId, Category category) {
		
		Boolean isCategoryId = categoryMapper.isCategoryId(categoryId);
		Boolean isUserId = userMapper.isUserId(category.getModifyId());
		
		if(!isUserId) {
			throw new ServiceException(ResultCode.ERROR_1001);
		}
		
		if(!isCategoryId) {
			throw new ServiceException(ResultCode.ERROR_1000);
		}
		
		try {
			category.setCategoryId(categoryId);
			categoryMapper.updateCategory(category);
		} catch (Exception e) {
			System.out.println("error :"+ e.getMessage());
		}
		
		return ResVO.builder()
				.data(category)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO deleteCategory(Long categoryId) {
		
		Boolean isCategoryId = categoryMapper.isCategoryId(categoryId);
		
		if(!isCategoryId) {
			throw new ServiceException(ResultCode.ERROR_1000);
		}
		
		Category category = Category.builder()
				.categoryId(categoryId)
				.build();
		
		try {
			
			categoryMapper.deleteCategory(category);
			
		} catch (Exception e) {
			System.out.println("error :"+ e.getMessage());
		}
		
		return ResVO.builder()
				.data(category)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public List<Map<String, Object>> getCategoryList(){
		return categoryMapper.selectCategory();
	}
	
	public Map<String, Object> getCategoryDetail(Long categoryId){
		
		Boolean isCategoryId = categoryMapper.isCategoryId(categoryId);
		
		if(!isCategoryId) {
			throw new ServiceException(ResultCode.ERROR_1000);
		}
		
		Category category = Category.builder()
				.categoryId(categoryId)
				.build();
		
		return categoryMapper.selectCategoryDetail(category);
	}
}
