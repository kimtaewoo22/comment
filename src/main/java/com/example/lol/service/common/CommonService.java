package com.example.lol.service.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.lol.model.common.Pagination;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommonService {
	
	private static final int CURRENT_PAGE = 1;
	
	private static final int PAGE_SIZE = 5;
	
	public static Map<String, Object> pageSetting(Map<String, Object> paramMap){
		
		if(paramMap.get("currentPage") == null || "".equals(paramMap.get("currentPage"))){
			paramMap.put("currentPage", CURRENT_PAGE);
		}
		
		if(paramMap.get("pageSize") == null || "".equals(paramMap.get("pageSize"))){
			paramMap.put("pageSize", PAGE_SIZE);
		}
		int currentPage = Integer.parseInt(paramMap.get("currentPage").toString());
		int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
		
		paramMap.put("startIndex", (currentPage-1) * pageSize);
		
		return paramMap;
	}
	
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
	
	/**
	 * 현재 날짜 비교
	 * @param date
	 * @return boolean(현재 날짜 보다 작으면 true 높으면 false)
	 */
	public boolean currentTimeCompare(Date dt){

		Boolean result = false;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentTime = new Date();
		
		String currentDate = simpleDateFormat.format(currentTime);
		String date = simpleDateFormat.format(dt);
		
		int compare = date.compareTo(currentDate);
		
		if(compare >= 0) {
			result = true;
		}else {
			result = false;
		}
		
		return result;
	}
	
	/**
	 * String을 Date형으로 형변환 및 포맷 (yyyy-MM-dd HH:mm:ss)
	 * @param data
	 * @return
	 */
	public Date StringToDateCasting(String st) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date resultData = null;
		
		try {
			resultData = simpleDateFormat.parse(st);
			resultData = calDate(resultData);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return resultData;
	}
	
	/**
	 * 날짜 더하기 (+2일,+1시간)
	 * @param dt
	 * @return
	 */
	public Date calDate(Date dt) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
//		cal.add(Calendar.DATE,2);
		cal.add(Calendar.HOUR, 1);
		dt = cal.getTime();
		
		return dt;
	}
}
