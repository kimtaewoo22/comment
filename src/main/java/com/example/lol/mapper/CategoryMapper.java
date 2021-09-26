package com.example.lol.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.lol.model.Category;

@Mapper
public interface CategoryMapper {
	
	public int insertCategory(Category category);
	
	public int updateCategory(Category category);
	
	public int deleteCategory(Category category);
	
	public List<Map<String, Object>> selectCategory();
	
	public Map<String, Object> selectCategoryDetail(Category category);
	
}
