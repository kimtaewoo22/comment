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

import com.example.lol.model.User;
import com.example.lol.model.common.CommentConst;
import com.example.lol.model.common.ResVO;
import com.example.lol.service.UserService;

@RestController
@RequestMapping(CommentConst.API_ROOT+CommentConst.API_VERSION_V1+"/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("")
	public ResVO createUserController(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@PutMapping("/{userId}")
	public ResVO modifyUserController(@PathVariable("userId") long userId
								, @RequestBody User user) {
		return userService.modifyUser(userId, user);
	}
	
	@DeleteMapping("/{userId}")
	public ResVO deleteUserController(@PathVariable("userId") long userId) {
		return userService.deleteUser(userId);
	}
	
	@GetMapping("")
	public ResVO getUserController(){
		return userService.getUserList();
	}
	
	@GetMapping("/{userId}")
	public ResVO getUserDetailController(@PathVariable("userId") long userId) {
		return userService.getUserDetail(userId);
	}
}
