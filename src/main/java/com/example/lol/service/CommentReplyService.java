package com.example.lol.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lol.mapper.CommentMapper;
import com.example.lol.mapper.CommentReplyMapper;
import com.example.lol.model.Comment;
import com.example.lol.model.CommentReply;
import com.example.lol.model.Report;

@Service
public class CommentReplyService {
	
	@Autowired
	CommentReplyMapper commentReplyMapper;
	
	@Autowired
	CommentMapper commentMapper;
	
	public CommentReply createCommentReply(long commentId, CommentReply commentReply) {
		
		commentReply.setCommentId(commentId);
		commentReplyMapper.insertCommentReply(commentReply);
		
		return commentReply;
	}
	
	public CommentReply modifyCommentReply(long commentId, long replyId, CommentReply commentReply) {
		
		commentReply.setCommentId(commentId);
		commentReply.setReplyId(replyId);
		commentReplyMapper.updateCommentReply(commentReply);
		
		return commentReply;
	}
	
	public CommentReply deleteCommentReply(long commentId, long replyId) {
		
		CommentReply commentReply = CommentReply.builder()
								.commentId(commentId)
								.replyId(replyId)
								.build();
		
		commentReplyMapper.deleteCommentReply(commentReply);
		
		return commentReply;
	}
	
	public List<Map<String, Object>> getCommentReplyList(long commentId){
		
		CommentReply commentReply = CommentReply.builder()
								.commentId(commentId)
								.build();
		
		return commentReplyMapper.selectCommentReply(commentReply);
	}
	
	public Map<String, Object> getCommentReplyDetail(long commentId, long replyId){
		
		CommentReply commentReply = CommentReply.builder()
								.commentId(commentId)
								.replyId(replyId)
								.build();
		
		return commentReplyMapper.selectCommentReplyDetail(commentReply);
	}
	
	public CommentReply createReport(long commentId, long replyId, Map<String, Object> paramMap) {

		CommentReply commentReply = CommentReply.builder()
								.commentId(commentId)
								.replyId(replyId)
								.build();
		
		Report report = Report.builder()
					.commentId(commentId)
					.replyId(replyId)
					.userId((int)paramMap.get("userId"))
					.reason((String)paramMap.get("reason"))
					.build();
		
		Boolean isReport = commentMapper.isReport(report);
		
		if(!isReport) {
			commentReply.setReportCnt(reportCnt(commentReply)+1);
			
			commentReplyMapper.updateReport(commentReply);
			commentMapper.insertReport(report);
		}
		
		return commentReply;
	}
	
	public Long reportCnt(CommentReply commentReply) {
		Map<String, Object> resultMap = commentReplyMapper.selectCommentReplyDetail(commentReply);
		return (Long) resultMap.get("reportCnt");
	}
}
