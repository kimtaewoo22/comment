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
	private String createId;
	private Date createDate;
	private String modifyId;
	private Date modifyDate;
	
}
