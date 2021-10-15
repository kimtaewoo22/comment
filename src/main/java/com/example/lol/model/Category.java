package com.example.lol.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
		
	private long categoryId;
	private String categoryNm;
	private long createId;
//	@JsonIgnore
	private LocalDateTime createDate;
	private long modifyId;
//	@JsonIgnore
	private LocalDateTime modifyDate;
	
	
}
