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
public class Like {
	private long likeId;
	private long commentId;
	private long userId;
	private Date createDate;
}
