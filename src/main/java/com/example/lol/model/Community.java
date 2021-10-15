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
public class Community {
	
	private long communityId;
	private long categoryId;
	private String communityNm;
	private long createId;
	private Date createDate;
	private long modifyId;
	private Date modifyDate;
	
}
