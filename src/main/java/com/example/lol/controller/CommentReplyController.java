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

import com.example.lol.model.CommentReply;
import com.example.lol.model.common.CommentConst;
import com.example.lol.service.CommentReplyService;

@RestController
@RequestMapping(CommentConst.API_ROOT+CommentConst.API_VERSION_V1+"/commentReply")
public class CommentReplyController {
	
	@Autowired
	CommentReplyService commentReplyService;
	
	@PostMapping("/{commentId}")
	public CommentReply createCommentReplyController(@PathVariable("commentId") long commentId 
													, @RequestBody CommentReply commentReply) {
		
		return commentReplyService.createCommentReply(commentId ,commentReply);
	}
	
	@PutMapping("/{commentId}/{replyId}")
	public CommentReply modifyCommentReplyController(@PathVariable("commentId") long commentId 
													, @PathVariable("replyId") long replyId 
													, @RequestBody CommentReply commentReply ) {
		
		return commentReplyService.modifyCommentReply(commentId, replyId, commentReply);
	}
	
	@DeleteMapping("/{commentId}/{replyId}")
	public CommentReply deleteCommentReplyController(@PathVariable("commentId") long commentId 
													, @PathVariable("replyId") long replyId ) {
		
		return commentReplyService.deleteCommentReply(commentId, replyId);
	}
	
	@GetMapping("/{commentId}")
	public List<Map<String, Object>> getCommentReplyController(@PathVariable("commentId") long commentId){
		
		return commentReplyService.getCommentReplyList(commentId);
	}
	
	@GetMapping("/{commentId}/{replyId}")
	public Map<String, Object> getCommentReplyDetailController(@PathVariable("commentId") long commentId
																	,@PathVariable("replyId") long replyId){
		
		return commentReplyService.getCommentReplyDetail(commentId, replyId);
	}
	
	@PostMapping("/{commentId}/{replyId}/report")
	public CommentReply createCommentReplyReportController(@PathVariable("commentId") long commentId
														, @PathVariable("replyId") long replyId
														, @RequestBody Map<String, Object> paramMap) {
		
		return commentReplyService.createReport(commentId, replyId, paramMap);
	}
}
