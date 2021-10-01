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

import com.example.lol.model.Community;
import com.example.lol.model.common.CommentConst;
import com.example.lol.service.CommunityService;

@RestController
@RequestMapping(CommentConst.API_ROOT+CommentConst.API_VERSION_V1+"/community")
public class CommunityController {

	@Autowired
	CommunityService communityService;
	
	@PostMapping("/{categoryId}")
	public Community createCommunityController(@PathVariable("categoryId") long categoryId 
										, @RequestBody Community community) {
		
		return communityService.createCommunity(categoryId ,community);
	}
	
	@PutMapping("/{categoryId}/{communityId}")
	public Community modiftyCommunityController(@PathVariable("categoryId") long categoryId
												,@PathVariable("communityId") long communityId	
												, @RequestBody Community community) {
			
		return communityService.modifyCommunity(communityId,categoryId,community);
	}
	
	@DeleteMapping("/{categoryId}/{communityId}")
	public Community deleteCommunityController(@PathVariable("communityId") long communityId
										 		,@PathVariable("categoryId") long categoryId) {
		
		return communityService.deleteCommunity(communityId,categoryId);
	}
	
	@GetMapping("/{categoryId}")
	public List<Map<String, Object>> getCommunity(@PathVariable("categoryId") long categoryId){
		
		return communityService.getCommunity(categoryId);
	}
	
	@GetMapping("/{categoryId}/{communityId}")
	public Map<String, Object> getCommunityDetail(@PathVariable("categoryId") long categoryId
													,@PathVariable("communityId") long communityId){
		
		return communityService.getCommunityDetail(communityId,categoryId);
	}
}
