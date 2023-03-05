package com.study.boardflab.mybatis.serviceImpl;

import com.study.boardflab.mybatis.dao.BoardDAO;
import com.study.boardflab.service.BoardService;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceMybatis implements BoardService {
    private final BoardDAO boardDAO;

    public BoardServiceMybatis(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @Override
    public boolean isLoginRequired(int boardId) {
        return boardDAO.isLoginRequired(boardId);
    }
}
