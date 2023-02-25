package com.study.boardflab.mybatis.dao;

import com.study.boardflab.mybatis.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDAO {

    void createBoard(BoardVO vo);

    Boolean isLoginRequired(int boardId);

    void deleteAll();
}
