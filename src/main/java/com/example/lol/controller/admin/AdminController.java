package com.example.lol.controller.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.lol.model.User;
import com.example.lol.service.admin.AdminService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping("/main")
	public ModelAndView main() {
		log.debug("[Admin main] start ==============");
		return new ModelAndView("adminMain");
	}
	
	@GetMapping("/user")
	public ModelAndView getUserController(@RequestParam Map<String, Object> paramMap) {
		log.debug("[Admin getUserController] start ==============");
		return adminService.getUserList(paramMap);
	}
	
	@GetMapping("/userDetail")
	public ModelAndView getUserDetailController(@RequestParam("userId") long userId) {
		log.debug("[Admin getUserDetailController] start ==============");
		return adminService.getUserDetail(userId);
	}
	
	@PostMapping("/userDetail/{userId}")
	public ModelAndView modifyUserController(@PathVariable("userId") long userId
											,@ModelAttribute User user) {
		log.debug("[Admin modifyUserController] start ==============");
		return adminService.modifyUser(userId, user);
	}
	
	@PostMapping("/userDelete/{userId}")
	public ModelAndView deleteUserController(@PathVariable("userId") long userId
											,@ModelAttribute User user) {
		log.debug("[Admin deleteUserController] start ==============");
		return adminService.deleteUser(userId,user);
	}
	
}
