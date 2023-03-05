package com.study.boardflab.mybatis.serviceImpl;

import com.study.boardflab.dto.reply.ReplyCreateDTO;
import com.study.boardflab.mybatis.dao.ReplyDAO;
import com.study.boardflab.mybatis.dao.UserDAO;
import com.study.boardflab.mybatis.vo.ReplyVO;
import com.study.boardflab.service.ReplyService;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceMybatis implements ReplyService {
    private final ReplyDAO replyDAO;
    private final UserDAO userDAO;

    public ReplyServiceMybatis(ReplyDAO replyDAO, UserDAO userDAO) {
        this.replyDAO = replyDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void create(ReplyCreateDTO dto, String username) {
        ReplyVO vo = ReplyVO.builder()
                .postId(dto.getPostId())
                .content(dto.getContent())
                .writtenUser(userDAO.getId(username))
                .nonMemNick(dto.getNonMemNick())
                .nonMemPw(dto.getNonMemPw())
                .build();
        replyDAO.create(vo);
    }
}
