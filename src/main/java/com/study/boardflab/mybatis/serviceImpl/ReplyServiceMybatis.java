package com.study.boardflab.mybatis.serviceImpl;

import com.study.boardflab.dto.reply.ReplyCreateDTO;
import com.study.boardflab.dto.reply.ReplyListRequestDTO;
import com.study.boardflab.dto.reply.ReplyUpdateDTO;
import com.study.boardflab.dto.reply.ReplyViewDTO;
import com.study.boardflab.mybatis.dao.ReplyDAO;
import com.study.boardflab.mybatis.dao.UserDAO;
import com.study.boardflab.mybatis.vo.PostVO;
import com.study.boardflab.mybatis.vo.ReplyVO;
import com.study.boardflab.service.ReplyService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Override
    public List<ReplyViewDTO> getList(ReplyListRequestDTO dto, String username) {

        List<ReplyVO> list = replyDAO.getList(dto.getPostId(), dto.getLimit(), dto.getOffset());

        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.stream().map(v -> {
            String writer = v.getMemberNick() != null ? v.getMemberNick() : v.getNonMemNick();
            boolean updatable = v.getWrittenUser() == null
                    || v.getWrittenUser().equals(userDAO.getId(username));

            return ReplyViewDTO.builder()
                    .id(v.getId())
                    .writer(writer)
                    .content(v.getContent())
                    .updatable(updatable)
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public void update(Long id, ReplyUpdateDTO dto, String username) {
        ReplyVO vo = replyDAO.find(id);
        if(!isModifiable(vo, dto.getNonMemPw(), username)){
            throw new AccessDeniedException("권한이 없습니다.");
        }
        ReplyVO updateVO = ReplyVO.builder()
                .id(id)
                .content(dto.getContent())
                .build();
        replyDAO.update(updateVO);

    }

    private boolean isModifiable(ReplyVO vo, String attemptedPassword, String username){
        String nonMemPw = vo.getNonMemPw();
        if(nonMemPw != null){
            return nonMemPw.equals(attemptedPassword);
        } else {
            return vo.getWrittenUser().equals(userDAO.getId(username));
        }

    }
}
