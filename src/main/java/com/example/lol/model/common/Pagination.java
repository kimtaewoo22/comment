package com.example.lol.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagination {
		
	private int currentPage;
	
	private int pageSize;
	
	private int totalPage;
	
	private int totalCount;
	
	private int startIndex;
}
