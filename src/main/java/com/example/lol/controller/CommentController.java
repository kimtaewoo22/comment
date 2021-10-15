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
import org.springframework.web.bind.annotation.RestController;

import com.example.lol.model.Comment;
import com.example.lol.model.common.CommentConst;
import com.example.lol.model.common.ResVO;
import com.example.lol.service.CommentService;

@RestController
@RequestMapping(CommentConst.API_ROOT+CommentConst.API_VERSION_V1+"/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@PostMapping("/{contentsId}")
	public ResVO createCommentController(@PathVariable("contentsId") long contentsId
										,@RequestBody Comment comment) {
		
		return commentService.createComment(contentsId, comment);
	}
	
	@PutMapping("/{contentsId}/{commentId}")
	public ResVO modifyCommentController(@PathVariable("contentsId") long contentsId
										, @PathVariable("commentId") long commentId
										, @RequestBody Comment comment) {
		
		return commentService.modfiyComment(contentsId,commentId,comment);
	}
	
	@DeleteMapping("/{contentsId}/{commentId}")
	public ResVO deleteCommentController(@PathVariable("contentsId") long contentsId
										, @PathVariable("commentId") long commentId) {
		
		return commentService.deleteComment(contentsId,commentId);
	}
	
	@GetMapping("/{contentsId}")
	public ResVO getCommentController(@PathVariable("contentsId") long contentsId){
		
		return commentService.getCommentList(contentsId);
	}
	
	@PostMapping("/{contentsId}/{commentId}/like")
	public ResVO insertLikeController(@PathVariable("contentsId") long contentsId
									, @PathVariable("commentId") long commentId
									, @RequestBody Map<String, Object> paramMap) {
		
		return commentService.createLike(contentsId,commentId,paramMap);
	}
	
	@PostMapping("/{contentsId}/{commentId}/report")
	public ResVO insertReportController(@PathVariable("contentsId") long contentsId
			, @PathVariable("commentId") long commentId
			, @RequestBody Map<String, Object> paramMap) {

		return commentService.createReport(contentsId,commentId,paramMap);
	}
}
