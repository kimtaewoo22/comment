package com.example.lol.config.handle;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.lol.config.exception.ServiceException;
import com.example.lol.model.common.ResVO;

@ControllerAdvice
public class ExceptionsAdvice {
	
	@ResponseBody
	@ExceptionHandler(ServiceException.class)
	public ResVO servceExceptionHandle(ServiceException serviceException) {
		return ResVO.builder().resultCode(serviceException.getResultCode()).resultMsg(serviceException.getResultMsg()).build();
	}
}
