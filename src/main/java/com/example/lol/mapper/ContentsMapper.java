package com.example.lol.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.lol.model.Contents;

@Mapper
public interface ContentsMapper {
	public int insertContents(Contents contents);
	public int updateContents(Contents contents);
	public int deleteContents(Contents contents);
	public List<Map<String, Object>> selectContents(Contents contents);
	public Map<String, Object> selectContentsDetail(Contents contents);
}
