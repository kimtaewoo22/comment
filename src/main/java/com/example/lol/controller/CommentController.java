package com.example.lol.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.jsf.FacesContextUtils;

import com.example.lol.service.CommentService;

@RestController
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public Map<String, Object> getUserList(){
		System.out.println("main() start....................");
		Map<String, Object> params = new HashMap<String, Object>();
		return commentService.getUser(params);
	}
}
