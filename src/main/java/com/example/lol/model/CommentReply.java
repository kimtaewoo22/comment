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
public class CommentReply {
	private long replyId;
	private String contents;
	private long userId;
	private long commentId;
	private long isDelete;
	private long reportCnt;
	private Date createDate;
	private Date modfiyDate;
}
