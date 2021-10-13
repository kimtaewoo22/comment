package com.example.lol.model.common;

public enum ResultCode {
	SUCCESS("200", "정상"),
	ERROR_2000("ERROR_2000", "Id값이 유효하지 않습니다.")
	;
	
	private String resultCode;
	private String resultMsg;
	
	//생성자 추가
	ResultCode(String resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}
	
	//인스턴스 필드 get메소드 추가
	public String getResultCode() {
		return resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}
}
