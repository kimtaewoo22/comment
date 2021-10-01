package com.example.lol.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.lol.model.CommentReply;

@Mapper
public interface CommentReplyMapper {
	public int insertCommentReply(CommentReply commentReply);
	public int updateCommentReply(CommentReply commentReply);
	public int deleteCommentReply(CommentReply commentReply);
	public List<Map<String, Object>> selectCommentReply(CommentReply commentReply);
	public Map<String, Object> selectCommentReplyDetail(CommentReply commentReply);
	public int updateReport(CommentReply commentReply);
}
