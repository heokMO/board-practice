package com.study.boardflab.service;

import com.study.boardflab.dto.reply.*;
import com.study.boardflab.mybatis.dao.ReplyDAO;
import com.study.boardflab.mybatis.dao.UserDAO;
import com.study.boardflab.mybatis.vo.ReplyVO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyServiceImpl implements ReplyService {
    private final ReplyDAO replyDAO;
    private final UserDAO userDAO;

    public ReplyServiceImpl(ReplyDAO replyDAO, UserDAO userDAO) {
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

        if(replyDAO.update(updateVO) != 1){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "해당 ID에 해당하는 댓글이 없습니다.");
        }

    }

    @Override
    public void delete(Long id, ReplyDeleteDTO dto, String username) {
        ReplyVO vo = replyDAO.find(id);
        if(!isModifiable(vo, dto.getNonMemPw(), username)){
            throw new AccessDeniedException("권한이 없습니다.");
        }

        if(replyDAO.delete(id) != 1){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "해당 ID에 해당하는 댓글이 없습니다.");
        }
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
