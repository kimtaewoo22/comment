package com.example.lol.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lol.config.exception.ServiceException;
import com.example.lol.mapper.CommentMapper;
import com.example.lol.mapper.ContentsMapper;
import com.example.lol.mapper.UserMapper;
import com.example.lol.model.Comment;
import com.example.lol.model.Like;
import com.example.lol.model.Report;
import com.example.lol.model.common.ResVO;
import com.example.lol.model.common.ResultCode;

@Service
public class CommentService {
	
	@Autowired
	CommentMapper commentMapper;
	
	@Autowired
	ContentsMapper contentsMapper;
	
	@Autowired
	UserMapper userMapper;
	
	public ResVO createComment(long contentsId,Comment comment) {

		comment.setContentsId(contentsId);
		
		Boolean isContentsId = contentsMapper.isContentsId(contentsId);
		
		if(!isContentsId) {
			throw new ServiceException(ResultCode.ERROR_1003);
		}
		
		try {
			commentMapper.insertComment(comment);
		}catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		return ResVO.builder()
				.data(comment)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO modfiyComment(long contentsId, long commentId, Comment comment) {
		
		comment.setContentsId(contentsId);
		comment.setCommentId(commentId);
		
		Boolean isComment = commentMapper.isComment(comment);

		if(!isComment) {
			throw new ServiceException(ResultCode.ERROR_2000);
		}
		
		try {
			commentMapper.updateComment(comment);
		}catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		return ResVO.builder()
				.data(comment)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO deleteComment(long contentsId,long commentId) {
		
		Comment comment = Comment.builder()
				.commentId(commentId)
				.contentsId(contentsId)
				.build();
		
		Boolean isComment = commentMapper.isComment(comment);
		
		if(!isComment) {
			throw new ServiceException(ResultCode.ERROR_2000);
		}
		
		try {
			commentMapper.deleteComment(comment);
		}catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		return ResVO.builder()
				.data(comment)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO getCommentList(long contentsId){
		
		List<Map<String, Object>> commentList = new ArrayList<Map<String,Object>>();
		Boolean isContentsId = contentsMapper.isContentsId(contentsId);
		
		if(!isContentsId) {
			throw new ServiceException(ResultCode.ERROR_1003);
		}
		
		Comment comment = Comment.builder()
				.contentsId(contentsId)
				.build();
		try {
			commentList = commentMapper.selectComment(comment);
		}catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		return ResVO.builder()
				.data(commentList)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO createLike(long contentsId, long commentId, Map<String, Object> paramMap) {
		
		Comment comment = Comment.builder()
				.contentsId(contentsId)
				.commentId(commentId)
				.build();
		
		Like like = Like.builder()
				.commentId(commentId)
				.userId(Long.valueOf(paramMap.get("userId").toString()))
				.build();
		
		Boolean isComment = commentMapper.isComment(comment);
		Boolean isUserId = userMapper.isUserId(Long.valueOf(paramMap.get("userId").toString()));
		
		if(!isComment) {
			throw new ServiceException(ResultCode.ERROR_2000);
		}
		if(!isUserId) {
			throw new ServiceException(ResultCode.ERROR_1001);
		}
		
		try {
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
			
		}catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		return ResVO.builder()
				.data(comment)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public long likeCnt(Comment comment) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			resultMap = commentMapper.selectCommentDetail(comment);
		}catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		return (Long) resultMap.get("likeCnt");
	}
	
	public ResVO createReport(long contentsId, long commentId, Map<String, Object> paramMap) {
		
		Comment comment = Comment.builder()
				.contentsId(contentsId)
				.commentId(commentId)
				.build();
		
		Report report = Report.builder()
				.commentId(commentId)
				.userId(Long.valueOf(paramMap.get("userId").toString()))
				.reason(paramMap.get("reason").toString())
				.build();
		
		Boolean isComment = commentMapper.isComment(comment);
		Boolean isUserId = userMapper.isUserId(Long.valueOf(paramMap.get("userId").toString()));
		
		if(!isComment) {
			throw new ServiceException(ResultCode.ERROR_2000);
		}
		if(!isUserId) {
			throw new ServiceException(ResultCode.ERROR_1001);
		}
		
		try {
			Boolean isReport = commentMapper.isReport(report);
			
			if(!isReport) {
				comment.setReportCnt(reportCnt(comment)+1);
				
				commentMapper.updateReport(comment);
				commentMapper.insertReport(report);
			}
			
		}catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		return ResVO.builder()
				.data(comment)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public long reportCnt(Comment comment) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			resultMap = commentMapper.selectCommentDetail(comment);
			
		}catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		return (Long) resultMap.get("reportCnt");
	}
}
