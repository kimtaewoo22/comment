package com.example.lol.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contents {
	private long contentsId;
	private String contentsNm;
	private long communityId;
	private String contents;
	private String createId;
	private Date createDate;
	private String modifyId;
	private Date modifyDate;
}
