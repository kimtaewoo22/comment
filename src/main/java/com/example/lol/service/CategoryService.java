package com.example.lol.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lol.mapper.CategoryMapper;
import com.example.lol.model.Category;

@Service
public class CategoryService {
	
	@Autowired
	CategoryMapper categoryMapper;
	
	public Category createCategory(Category category) {
		
		categoryMapper.insertCategory(category);
		
		return category;
	}
	
	public Category modifyCategory(Long categoryId, Category category) {
		category.setCategoryId(categoryId);
		
		categoryMapper.updateCategory(category);
		
		return category;
	}
	
	public Category deleteCategory(Long categoryId) {
		Category category = Category.builder()
				.categoryId(categoryId)
				.build();
		
		categoryMapper.deleteCategory(category);
		
		return category;
	}
	
	public List<Map<String, Object>> getCategoryList(){
		return categoryMapper.selectCategory();
	}
	
	public Map<String, Object> getCategoryDetail(Long categoryId){
		Category category = Category.builder()
				.categoryId(categoryId)
				.build();
		
		return categoryMapper.selectCategoryDetail(category);
	}
}
