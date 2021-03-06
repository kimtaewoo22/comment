package com.example.lol.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lol.model.Comment;
import com.example.lol.model.common.CommentConst;
import com.example.lol.model.common.ResVO;
import com.example.lol.service.CommentReplyService;

@RestController
@RequestMapping(CommentConst.API_ROOT+CommentConst.API_VERSION_V1+"/commentReply")
public class CommentReplyController {
	
	@Autowired
	CommentReplyService commentReplyService;
	
	@PostMapping("/{contentsId}/{commentId}")
	public ResVO createCommentReplyController(@PathVariable("contentsId") long contentsId
												,@PathVariable("commentId") long commentId	
												,@RequestBody Comment comment) {
		
		return commentReplyService.createCommentReply(contentsId,commentId ,comment);
	}
	
	@PutMapping("/{contentsId}/{commentId}")
	public ResVO modifyCommentReplyController(@PathVariable("contentsId") long contentsId
												,@PathVariable("commentId") long commentId
												,@RequestBody Comment comment ) {
		
		return commentReplyService.modifyCommentReply(contentsId,commentId, comment);
	}
	
	@DeleteMapping("/{contentsId}/{commentId}")
	public ResVO deleteCommentReplyController(@PathVariable("contentsId") long contentsId
												,@PathVariable("commentId") long commentId) {
		
		return commentReplyService.deleteCommentReply(contentsId,commentId);
	}
	
	@GetMapping("/{contentsId}/{commentId}")
	public ResVO getCommentReplyController(@PathVariable("contentsId") long contentsId
											,@PathVariable("commentId") long commentId
											,@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage
											,@RequestParam(value = "pageSize", required = false, defaultValue = "3") int pageSize){
		
		return commentReplyService.getCommentReplyList(contentsId,commentId,currentPage,pageSize);
	}
	
	@PostMapping("/{contentsId}/{commentId}/report")
	public ResVO createCommentReplyReportController(@PathVariable("contentsId") long contentsId
														,@PathVariable("commentId") long commentId
														,@RequestBody Map<String, Object> paramMap) {
		
		return commentReplyService.createReport(contentsId,commentId, paramMap);
	}
}
