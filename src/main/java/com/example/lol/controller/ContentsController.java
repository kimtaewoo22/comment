package com.example.lol.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lol.model.Contents;
import com.example.lol.model.common.CommentConst;
import com.example.lol.service.ContentsService;

@RestController
@RequestMapping(CommentConst.API_ROOT+CommentConst.API_VERSION_V1+"/contents")
public class ContentsController {

	@Autowired
	ContentsService contentsService;
	
	@PostMapping("/{communityId}")
	public Contents createContentsController(@PathVariable("communityId") long communityId
											,@RequestBody Contents contents) {
		
		return contentsService.createContents(communityId,contents);
	}
	
	@PutMapping("/{communityId}/{contentsId}")
	public Contents modifyContentsController(@PathVariable("contentsId") long contentsId
											,@PathVariable("communityId") long communityId
											,@RequestBody Contents contents) {
		
		return contentsService.modifyContents(contentsId,communityId,contents);
	}
	
	@DeleteMapping("/{communityId}/{contentsId}")
	public Contents deleteContentsController(@PathVariable("contentsId") long contentsId
											,@PathVariable("communityId") long communityId) {
		
		return contentsService.deleteContents(contentsId,communityId);
	}
	
	@GetMapping("/{communityId}")
	public List<Map<String, Object>> getContentsController(@PathVariable("communityId") long communityId){
		
		return contentsService.getContentsList(communityId);
	}
	
	@GetMapping("/{communityId}/{contentsId}")
	public Map<String, Object> getContentsDetailController(@PathVariable("communityId") long communityId
														,@PathVariable("contentsId") long contentsId){
		
		return contentsService.getContentsDetail(contentsId,communityId);
	}
}
