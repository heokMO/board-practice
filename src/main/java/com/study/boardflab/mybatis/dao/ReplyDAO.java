package com.study.boardflab.mybatis.dao;

import com.study.boardflab.mybatis.vo.ReplyVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyDAO {
    void create(ReplyVO vo);
}
