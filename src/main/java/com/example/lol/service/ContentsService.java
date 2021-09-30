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
	
	public Contents createContents(Long communityId, Contents contents) {
		
		contents.setCommunityId(communityId);
		contentsMapper.insertContents(contents);
		
		return contents;
	}
	
	public Contents modifyContents(Long contentsId,Long communityId,Contents contents) {
		
		contents.setContentsId(contentsId);
		contents.setCommunityId(communityId);
		contentsMapper.updateContents(contents);
		
		return contents;
	}
	
	public Contents deleteContents(Long contentsId,Long communityId) {
		
		Contents contents = Contents.builder()
				.contentsId(contentsId)
				.communityId(communityId)
				.build();
		
		contentsMapper.deleteContents(contents);
		
		return contents;
	}
	
	public List<Map<String, Object>> getContentsList(Long communityId){

		Contents contents = Contents.builder()
				.communityId(communityId)
				.build();
		
		return contentsMapper.selectContents(contents);
	}
	
	public Map<String, Object> getContentsDetail(Long contentsId,Long communityId){
		
		Contents contents = Contents.builder()
				.contentsId(contentsId)
				.communityId(communityId)
				.build();
		
		return contentsMapper.selectContentsDetail(contents);
	}
}
