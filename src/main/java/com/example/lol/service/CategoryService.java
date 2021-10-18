package com.example.lol.service;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	public ResVO createCategory(Category category) {
		
		try {
			
			categoryMapper.insertCategory(category);
			
		} catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		} catch (Exception e) {
			System.out.println("error :"+ e.getMessage());
		}
		
		return ResVO.builder()
				.data(category)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO modifyCategory(long categoryId, Category category) {
		
		Boolean isCategoryId = categoryMapper.isCategoryId(categoryId);
		Boolean isUserId = userMapper.isUser(category.getModifyId());
		
		if(!isUserId) {
			throw new ServiceException(ResultCode.ERROR_1001);
		}
		
		if(!isCategoryId) {
			throw new ServiceException(ResultCode.ERROR_1000);
		}
		
		try {
			category.setCategoryId(categoryId);
			categoryMapper.updateCategory(category);
		} catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		} catch (Exception e) {
			System.out.println("error :"+ e.getMessage());
		}
		
		return ResVO.builder()
				.data(category)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO deleteCategory(long categoryId) {
		
		Boolean isCategoryId = categoryMapper.isCategoryId(categoryId);
		
		if(!isCategoryId) {
			throw new ServiceException(ResultCode.ERROR_1000);
		}
		
		Category category = Category.builder()
				.categoryId(categoryId)
				.build();
		
		try {
			
			categoryMapper.deleteCategory(category);
			
		} catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		} catch (Exception e) {
			System.out.println("error :"+ e.getMessage());
		}
		
		return ResVO.builder()
				.data(category)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO getCategoryList(){
		
		List<Map<String, Object>> categoryList = new ArrayList<Map<String,Object>>();
		
		try {
			
			categoryList = categoryMapper.selectCategory();
			
		} catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		} catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		return ResVO.builder()
				.data(categoryList)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO getCategoryDetail(long categoryId){
		
		Map<String, Object> categoryMap = new HashMap<String, Object>();
		Boolean isCategoryId = categoryMapper.isCategoryId(categoryId);
		
		if(!isCategoryId) {
			throw new ServiceException(ResultCode.ERROR_1000);
		}
		
		Category category = Category.builder()
				.categoryId(categoryId)
				.build();
		
		try {
			
			categoryMap = categoryMapper.selectCategoryDetail(category);
			
		} catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		} catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		return ResVO.builder()
				.data(categoryMap)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
}
