package com.example.lol.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.lol.model.Comment;
import com.example.lol.model.Like;
import com.example.lol.model.Report;

@Mapper
public interface CommentMapper {
	
	public int insertComment(Comment comment);
	public int updateComment(Comment comment);
	public int deleteComment(Comment comment);
	public List<Map<String, Object>> selectComment(Comment comment);
	public int insertLike(Like like);
	public int updateLike(Comment comment);
	public boolean isLike(Like like);
	public Map<String, Object> selectCommentDetail(Comment comment);
	public int deleteLike(Like like);
	public int insertReport(Report report);
	public int updateReport(Comment comment);
	public boolean isReport(Report report);
}
