package com.study.boardflab.service;

import com.study.boardflab.mybatis.dao.BoardDAO;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardDAO boardDAO;

    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @Override
    public boolean isLoginRequired(int boardId) {
        return boardDAO.isLoginRequired(boardId);
    }
}
