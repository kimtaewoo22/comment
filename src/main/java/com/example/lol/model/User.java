package com.example.lol.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	private long userId;
	private String userNm;
	private String password;
	private String adminYn;
	private String token;
	private int tokenTime;
	private long isDelete;
	private Date createDate;
	private Date modifyDate;
}
