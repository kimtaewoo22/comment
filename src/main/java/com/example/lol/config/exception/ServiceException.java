package com.example.lol.config.exception;

import com.example.lol.model.common.ResultCode;

public class ServiceException extends RuntimeException {
	
	private ResultCode resultCode;
	
	public ServiceException(ResultCode resultCode) {
		super();
		this.resultCode = resultCode;
	}

	public String getResultCode() {
		return resultCode.getResultCode();
	}

	public String getResultMsg() {
		return resultCode.getResultMsg();
	}
}
