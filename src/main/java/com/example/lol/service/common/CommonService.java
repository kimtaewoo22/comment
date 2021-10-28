package com.example.lol.service.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.lol.model.common.Pagination;

@Service
public class CommonService {
	
	public static Map<String, Object> pageService(int currentPage, int pageSize){
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		
		pageMap.put("currentPage", currentPage);
		pageMap.put("pageSize", pageSize);
		pageMap.put("startIndex", (currentPage-1) * pageSize);
		
		return pageMap;
	}
	
	public static Pagination pageTotalCountService(List<Map<String, Object>> list, Map<String, Object> pageMap) {
		
		int currentPage = Integer.parseInt(pageMap.get("currentPage").toString());
		int pageSize = Integer.parseInt(pageMap.get("pageSize").toString());
		int startIndex = Integer.parseInt(pageMap.get("startIndex").toString());
		int totalCount = 0;
		
		if(list != null && !list.isEmpty()) {
			totalCount = Integer.parseInt(list.get(0).get("totalCount").toString());
		}
		
		Pagination pagination = Pagination.builder()
				.currentPage(currentPage)
				.pageSize(pageSize)
				.startIndex(startIndex)
				.totalCount(totalCount)
				.build();
		
		if(totalCount%pageSize > 0) {
			pagination.setTotalPage((totalCount/pageSize)+1);
		}else {
			pagination.setTotalPage(totalCount/pageSize);
		}
		
		return pagination;
	}
}
