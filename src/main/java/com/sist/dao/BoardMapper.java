package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
public interface BoardMapper {
	// <select>
    @Select("SELECT no,subject,name,regdate,hit,group_tab,num "
    	   +"FROM (SELECT no,subject,name,regdate,hit,group_tab,rownum as num "
    	   +"FROM (SELECT no,subject,name,regdate,hit,group_tab "
    	   +"FROM board ORDER BY group_id DESC,group_step ASC )) "
    	   +"WHERE num BETWEEN #{start} AND #{end}")
    public List<BoardVO> boardListData(Map map);
    //     resultType                 parameterType
    @Select("SELECT CEIL(COUNT(*)/10) FROM board")
    public int boardTotalPage();
    
    // insert
    @SelectKey(keyProperty="no",before=true,
    	resultType=int.class,statement="SELECT NVL(MAX(no)+1,1) as no FROM board")
    @Insert("INSERT INTO board VALUES("
    	+"#{no},#{name},#{subject},#{content},"
    	+"#{pwd},SYSDATE,0,"
    	+"(SELECT NVL(Max(group_id)+1,1) FROM board),"
    	+"0,0,0,0)")
    public void boardInsert(BoardVO vo);
    
    // content
    @Update("UPDATE board SET "
    	   +"hit=hit+1 "
    	   +"WHERE no=#{no}")
    public void boardHitIncrement(int no);
    
    @Select("SELECT no,name,subject,content,regdate,hit "
    	   +"FROM board "
    	   +"WHERE no=#{no}")
    public BoardVO boardContentData(int no);
    
    // ´äº¯ 
    @Select("SELECT group_id,group_step,group_tab "
    	   +"FROM board "
    	   +"WHERE no=#{no}")
    public BoardVO replyParentData(int no);
    
    @Update("UPDATE board SET "
    	   +"group_step=group_step+1 "
    	   +"WHERE group_id=#{group_id} AND group_step>#{group_step}")
    public void replyGroupStepIncrement(BoardVO vo);
    
    @SelectKey(keyProperty="no",before=true,
        	resultType=int.class,statement="SELECT NVL(MAX(no)+1,1) as no FROM board")
    @Insert("INSERT INTO board VALUES("
        	+"#{no},#{name},#{subject},#{content},"
        	+"#{pwd},SYSDATE,0,"
        	+"#{group_id},"
        	+"#{group_step},#{group_tab},#{root},0)")
    public void replyInsert(BoardVO vo);
    
    @Update("UPDATE board SET "
    	   +"depth=depth+1 "
    	   +"WHERE no=#{no}")
    public void replyDepthIncrement(int no);
    
    @Update("UPDATE board SET "
				  +"name=#{name},subject=#{subject},"
				  +"content=#{content} "
				  +"WHERE no=#{no} ")
    public void contentUpdate(BoardVO vo);
}






