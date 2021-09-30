package com.example.lol.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lol.mapper.CommentMapper;
import com.example.lol.model.Comment;
import com.example.lol.model.Like;
import com.example.lol.model.Report;

@Service
public class CommentService {
	
	@Autowired
	CommentMapper commentMapper;
	
	public Comment createComment(long contentsId,Comment comment) {
		
		comment.setContentsId(contentsId);
		
		commentMapper.insertComment(comment);
		
		return comment;
	}
	
	public Comment modfiyComment(long contentsId, long commentId, Comment comment) {
		
		comment.setContentsId(contentsId);
		comment.setCommentId(commentId);
		
		commentMapper.updateComment(comment);
		
		return comment;
	}
	
	public Comment deleteComment(long contentsId,long commentId) {
		
		Comment comment = Comment.builder()
				.commentId(commentId)
				.contentsId(contentsId)
				.build();
		
		commentMapper.deleteComment(comment);
		
		return comment;
	}
	
	public List<Map<String, Object>> getCommentList(long contentsId){
		
		Comment comment = Comment.builder()
				.contentsId(contentsId)
				.build();
		
		return commentMapper.selectComment(comment);
	}
	
	public Comment insertLike(long contentsId, long commentId, Map<String, Long> paramMap) {
		
		Comment comment = Comment.builder()
				.contentsId(contentsId)
				.commentId(commentId)
				.build();
		
		Like like = Like.builder()
				.commentId(commentId)
				.userId(paramMap.get("userId"))
				.build();
		
		Boolean islike = commentMapper.isLike(like);
		
		if(islike) {
			comment.setLikeCnt(likeCnt(comment)-1);
			
			commentMapper.updateLike(comment);
			commentMapper.deleteLike(like);
		}else {
			comment.setLikeCnt(likeCnt(comment)+1);
			
			commentMapper.updateLike(comment);
			commentMapper.insertLike(like);
		}
		
		return comment;
	}
	
	public Long likeCnt(Comment comment) {
		Map<String, Object> resultMap = commentMapper.selectCommentDetail(comment);
		return (Long) resultMap.get("likeCnt");
	}
	
	public Comment insertReport(long contentsId, long commentId, Map<String, Object> paramMap) {

		Comment comment = Comment.builder()
				.contentsId(contentsId)
				.commentId(commentId)
				.build();
		
		Report report = Report.builder()
				.commentId(commentId)
				.userId((int)paramMap.get("userId"))
				.reason((String)paramMap.get("reason"))
				.build();
		
		Boolean isReport = commentMapper.isReport(report);
		
		if(!isReport) {
			comment.setReportCnt(reportCnt(comment)+1);
			
			commentMapper.updateReport(comment);
			commentMapper.insertReport(report);
		}
		
		return comment;
	}
	
	public Long reportCnt(Comment comment) {
		Map<String, Object> resultMap = commentMapper.selectCommentDetail(comment);
		return (Long) resultMap.get("reportCnt");
	}
}
