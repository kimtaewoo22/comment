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
public class Comment {
	private long commentId;
	private long contentsId;
	private long userId;
	private String contents;
	private long likeCnt;
	private long isDelete;
	private long reportCnt;
	private Date createDate;
	private Date modfiyDate;
}
