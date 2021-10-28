package com.example.lol.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lol.config.exception.ServiceException;
import com.example.lol.mapper.CommentMapper;
import com.example.lol.mapper.CommentReplyMapper;
import com.example.lol.mapper.UserMapper;
import com.example.lol.model.Comment;
import com.example.lol.model.Report;
import com.example.lol.model.common.Pagination;
import com.example.lol.model.common.ResVO;
import com.example.lol.model.common.ResultCode;
import com.example.lol.service.common.CommonService;

@Service
public class CommentReplyService {
	
	@Autowired
	CommentReplyMapper commentReplyMapper;
	
	@Autowired
	CommentMapper commentMapper;
	
	@Autowired
	UserMapper userMapper;
	
	public ResVO createCommentReply(long contentsId,long commentId, Comment comment) {

		comment.setContentsId(contentsId);
		comment.setCommentId(commentId);
		
		Boolean isUser = userMapper.isUser(Long.valueOf(comment.getUserId()));
		Boolean isComment = commentMapper.isComment(comment);
		
		if(!isComment) {
			throw new ServiceException(ResultCode.ERROR_2000);
		}
		if(!isUser) {
			throw new ServiceException(ResultCode.ERROR_3000);
		}
		
		try {
			commentReplyMapper.insertCommentReply(comment);
		} catch (ServiceException e) {
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
	
	public ResVO modifyCommentReply(long contentsId,long commentId, Comment comment) {
		
		comment.setContentsId(contentsId);
		comment.setCommentId(commentId);
		
		Boolean isComment = commentMapper.isComment(comment);
		
		if(!isComment) {
			throw new ServiceException(ResultCode.ERROR_2000);
		}
		
		try {
			commentReplyMapper.updateCommentReply(comment);
		} catch (ServiceException e) {
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
	
	public ResVO deleteCommentReply(long contentsId,long commentId) {
		
		Comment comment = Comment.builder()
								.contentsId(contentsId)
								.commentId(commentId)
								.build();
		
		Boolean isComment = commentMapper.isComment(comment);
		
		if(!isComment) {
			throw new ServiceException(ResultCode.ERROR_2000);
		}
		
		try {
			commentReplyMapper.deleteCommentReply(comment);
		} catch (ServiceException e) {
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
	
	public ResVO getCommentReplyList(long contentsId,long commentId, int currentPage, int pageSize){
		
		Comment comment = Comment.builder()
						.contentsId(contentsId)
						.commentId(commentId)
						.build();
		
		List<Map<String, Object>> commentList = new ArrayList<Map<String,Object>>();
		Boolean isComment = commentMapper.isComment(comment);
		
		if(!isComment) {
			throw new ServiceException(ResultCode.ERROR_2000);
		}
		
		Map<String, Object> paramMap = CommonService.pageService(currentPage, pageSize);
		paramMap.put("parentsId", commentId);
		
		try {
			commentList = commentReplyMapper.selectCommentReply(paramMap);
		} catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		Pagination pagination = CommonService.pageTotalCountService(commentList, paramMap);
		
		return ResVO.builder()
				.data(commentList)
				.pagingInfo(pagination)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO getCommentReplyDetail(long contentsId,long commentId){
		
		Comment comment = Comment.builder()
						.contentsId(contentsId)
						.commentId(commentId)
						.build();
		
		Map<String, Object> commentMap = new HashMap<String, Object>();
		Boolean isComment = commentMapper.isComment(comment);
		
		if(!isComment) {
			throw new ServiceException(ResultCode.ERROR_2000);
		}
		
		try {
			commentMap = commentReplyMapper.selectCommentReplyDetail(comment);
		} catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		return ResVO.builder()
				.data(commentMap)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public ResVO createReport(long contentsId,long commentId, Map<String, Object> paramMap) {

		Comment comment = Comment.builder()
						.contentsId(contentsId)
						.commentId(commentId)
						.build();
		
		Report report = Report.builder()
					.commentId(commentId)
					.userId((int)paramMap.get("userId"))
					.reason((String)paramMap.get("reason"))
					.build();
		
		Boolean isComment = commentMapper.isComment(comment);
		Boolean isUser = userMapper.isUser(Long.valueOf(paramMap.get("userId").toString()));
		
		if(!isComment) {
			throw new ServiceException(ResultCode.ERROR_2000);
		}
		if(!isUser) {
			throw new ServiceException(ResultCode.ERROR_3000);
		}
		
		try {
			Boolean isReport = commentMapper.isReport(report);
			
			if(!isReport) {
				comment.setReportCnt(reportCnt(comment)+1);
				
				commentReplyMapper.updateReport(comment);
				commentMapper.insertReport(report);
			}else {
				throw new ServiceException(ResultCode.ERROR_9999);
			}
		} catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_4000);
		}catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		return ResVO.builder()
				.data(comment)
				.resultCode(ResultCode.SUCCESS.getResultCode())
				.resultMsg(ResultCode.SUCCESS.getResultMsg())
				.build();
	}
	
	public Long reportCnt(Comment comment) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			resultMap = commentReplyMapper.selectCommentReplyDetail(comment);
		} catch (ServiceException e) {
			throw new ServiceException(ResultCode.ERROR_9999);
		}catch (Exception e) {
			System.out.println("error : "+e.getMessage());
		}
		
		return (long) resultMap.get("reportCnt");
	}
}
