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
public class Report {
	private long reportId;
	private long commentId;
	private long replyId;
	private long userId;
	private	String reason;
	private Date createDate;
}
