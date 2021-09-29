package com.example.lol.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lol.mapper.ContentsMapper;
import com.example.lol.model.Contents;

@Service
public class ContentsService {
	
	@Autowired
	ContentsMapper contentsMapper;
	
	public Contents createContents(Contents contents) {
		
		contentsMapper.insertContents(contents);
		
		return contents;
	}
	
	public Contents modifyContents(Contents contents) {
		
		contentsMapper.updateContents(contents);
		
		return contents;
	}
	
	public Contents deleteContents(Contents contents) {
		
		contentsMapper.deleteContents(contents);
		
		return contents;
	}
	
	public List<Map<String, Object>> getContentsList(Contents contents){
		return contentsMapper.selectContents(contents);
	}
	
	public Map<String, Object> getContentsDetail(Contents contents){
		return contentsMapper.selectContentsDetail(contents);
	}
}
