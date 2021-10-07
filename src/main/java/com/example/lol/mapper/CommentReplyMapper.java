package com.example.lol.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.lol.model.Comment;

@Mapper
public interface CommentReplyMapper {
	public int insertCommentReply(Comment comment);
	public int updateCommentReply(Comment comment);
	public int deleteCommentReply(Comment comment);
	public List<Map<String, Object>> selectCommentReply(Comment comment);
	public Map<String, Object> selectCommentReplyDetail(Comment comment);
	public int updateReport(Comment comment);
}
