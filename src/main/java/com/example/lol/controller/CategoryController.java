package com.example.lol.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lol.model.Category;
import com.example.lol.model.common.CommentConst;
import com.example.lol.model.common.ResVO;
import com.example.lol.service.CategoryService;

@RestController
@RequestMapping(CommentConst.API_ROOT+CommentConst.API_VERSION_V1+"/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("")
	public Category createCategoryController(@RequestBody Category category) {
		
		return categoryService.createCategory(category);
	}
	
	@PutMapping("/{categoryId}")
	public ResVO modiyCategoryController(@PathVariable("categoryId") long categoryId 
										, @RequestBody Category category) {
		
		return categoryService.modifyCategory(categoryId,category);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResVO deleteCategoryController(@PathVariable("categoryId") long categoryId) {
		
		return categoryService.deleteCategory(categoryId);
	}
	
	@GetMapping("")
	public List<Map<String, Object>> getCategoryListController(){
		
		return categoryService.getCategoryList();
	}
	
	@GetMapping("/{categoryId}")
	public Map<String, Object> getCategoryDetailController(@PathVariable("categoryId") long categoryId){
		
		return categoryService.getCategoryDetail(categoryId);
	}
}
