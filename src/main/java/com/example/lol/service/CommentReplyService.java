package com.example.lol.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lol.mapper.CommentMapper;
import com.example.lol.mapper.CommentReplyMapper;
import com.example.lol.model.Comment;
import com.example.lol.model.Report;

@Service
public class CommentReplyService {
	
	@Autowired
	CommentReplyMapper commentReplyMapper;
	
	@Autowired
	CommentMapper commentMapper;
	
	public Comment createCommentReply(long contentsId,long commentId, Comment comment) {

		comment.setContentsId(contentsId);
		comment.setCommentId(commentId);
		commentReplyMapper.insertCommentReply(comment);
		
		return comment;
	}
	
	public Comment modifyCommentReply(long contentsId,long commentId, Comment comment) {
		
		comment.setContentsId(contentsId);
		comment.setCommentId(commentId);
		commentReplyMapper.updateCommentReply(comment);
		
		return comment;
	}
	
	public Comment deleteCommentReply(long contentsId,long commentId) {
		
		Comment comment = Comment.builder()
								.contentsId(contentsId)
								.commentId(commentId)
								.build();
		
		commentReplyMapper.deleteCommentReply(comment);
		
		return comment;
	}
	
	public List<Map<String, Object>> getCommentReplyList(long contentsId,long commentId){
		
		Comment comment = Comment.builder()
						.contentsId(contentsId)
						.commentId(commentId)
						.build();
		
		return commentReplyMapper.selectCommentReply(comment);
	}
	
	public Map<String, Object> getCommentReplyDetail(long contentsId,long commentId){
		
		Comment comment = Comment.builder()
						.contentsId(contentsId)
						.commentId(commentId)
						.build();
		
		return commentReplyMapper.selectCommentReplyDetail(comment);
	}
	
	public Comment createReport(long contentsId,long commentId, Map<String, Object> paramMap) {

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
			
			commentReplyMapper.updateReport(comment);
			commentMapper.insertReport(report);
		}
		
		return comment;
	}
	
	public Long reportCnt(Comment comment) {
		Map<String, Object> resultMap = commentReplyMapper.selectCommentReplyDetail(comment);
		return (Long) resultMap.get("reportCnt");
	}
}
