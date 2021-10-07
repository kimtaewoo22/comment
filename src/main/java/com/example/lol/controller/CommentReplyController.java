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

import com.example.lol.model.Comment;
import com.example.lol.model.common.CommentConst;
import com.example.lol.service.CommentReplyService;

@RestController
@RequestMapping(CommentConst.API_ROOT+CommentConst.API_VERSION_V1+"/commentReply")
public class CommentReplyController {
	
	@Autowired
	CommentReplyService commentReplyService;
	
	@PostMapping("/{contentsId}/{commentId}")
	public Comment createCommentReplyController(@PathVariable("contentsId") long contentsId
												,@PathVariable("commentId") long commentId	
												,@RequestBody Comment comment) {
		
		return commentReplyService.createCommentReply(contentsId,commentId ,comment);
	}
	
	@PutMapping("/{contentsId}/{commentId}")
	public Comment modifyCommentReplyController(@PathVariable("contentsId") long contentsId
												,@PathVariable("commentId") long commentId
												,@RequestBody Comment comment ) {
		
		return commentReplyService.modifyCommentReply(contentsId,commentId, comment);
	}
	
	@DeleteMapping("/{contentsId}/{commentId}")
	public Comment deleteCommentReplyController(@PathVariable("contentsId") long contentsId
												,@PathVariable("commentId") long commentId) {
		
		return commentReplyService.deleteCommentReply(contentsId,commentId);
	}
	
	@GetMapping("/{contentsId}/{commentId}")
	public List<Map<String, Object>> getCommentReplyController(@PathVariable("contentsId") long contentsId
																,@PathVariable("commentId") long commentId){
		
		return commentReplyService.getCommentReplyList(contentsId,commentId);
	}
	
	@PostMapping("/{contentsId}/{commentId}/report")
	public Comment createCommentReplyReportController(@PathVariable("contentsId") long contentsId
														,@PathVariable("commentId") long commentId
														,@RequestBody Map<String, Object> paramMap) {
		
		return commentReplyService.createReport(contentsId,commentId, paramMap);
	}
}
