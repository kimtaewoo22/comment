package com.example.lol.model.common;

public enum ResultCode {
	SUCCESS("200", "정상"),
	
	ERROR_1000("ERROR_1000", "categoryId 값이 유효하지 않습니다."),
	ERROR_1001("ERROR_1001", "userId값이 유효하지 않습니다."),
	ERROR_1002("ERROR_1002", "communityId값이 유효하지 않습니다."),
	ERROR_1003("ERROR_1003", "contentsId값이 유효하지 않습니다."),
	ERROR_1004("ERROR_1004", "commentId값이 유효하지 않습니다."),
	ERROR_2000("ERROR_2000", "ID값을 확인하세요."),
	ERROR_3000("ERROR_3000", "Parameter값을 확인하세요."),
	ERROR_4000("ERROR_4000", "이미 신고처리가 되었습니다."),
	
	ERROR_9000("ERROR_9000", "아이디가 없습니다."),
	ERROR_9001("ERROR_9001", "패스워드가 맞지 않습니다."),
	
	ERROR_9999("ERROR_9999", "데이터베이스 처리 실패하였습니다.")
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
