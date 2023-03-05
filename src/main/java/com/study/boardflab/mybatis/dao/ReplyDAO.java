package com.study.boardflab.mybatis.dao;

import com.study.boardflab.mybatis.vo.ReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyDAO {
    void create(ReplyVO vo);


    List<ReplyVO> getList(@Param("postId") Long postId,
                          @Param("limit") Integer limit,
                          @Param("offset") Integer offset);

    ReplyVO find(Long id);

    void update(ReplyVO updateVO);
}
