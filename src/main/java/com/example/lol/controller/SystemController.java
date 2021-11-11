package com.example.lol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lol.model.User;
import com.example.lol.model.common.CommentConst;
import com.example.lol.model.common.ResVO;
import com.example.lol.service.common.SystemService;

@RestController
@RequestMapping(CommentConst.API_ROOT+CommentConst.API_VERSION_V1+"/system/oauth")
public class SystemController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SystemService systemService;
	
	@PostMapping("/token")
	public ResVO tokenController(@RequestBody User user) {
		logger.debug("tokenController start....................");
		return systemService.tokenService(user);
	}
}
