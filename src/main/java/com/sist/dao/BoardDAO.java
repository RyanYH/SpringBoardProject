package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class BoardDAO {
	@Autowired
    private BoardMapper mapper;
	public List<BoardVO> boardListData(Map map)
	{
		return mapper.boardListData(map);
	}
	public int boardTotalPage()
	{
		return mapper.boardTotalPage();
	}
	public void boardInsert(BoardVO vo)
	{
		mapper.boardInsert(vo);
	}
	public BoardVO boardContentData(int no)
	{
		mapper.boardHitIncrement(no);
		return mapper.boardContentData(no);
	}
	// ´äº¯
	public BoardVO replyParentData(int no)
	{
		return mapper.replyParentData(no);
	}
	public void replyGroupStepIncrment(BoardVO vo)
	{
		mapper.replyGroupStepIncrement(vo);
	}
	public void replyInsert(BoardVO vo)
	{
		mapper.replyInsert(vo);
	}
	public void replyDepthIncrement(int no)
	{
		mapper.replyDepthIncrement(no);
	}
	public void contentUpdate(BoardVO vo)
	{
		mapper.contentUpdate(vo);
	}
	
}





