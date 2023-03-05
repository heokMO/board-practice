package com.study.boardflab.mybatis.serviceImpl;

import com.study.boardflab.dto.reply.ReplyCreateDTO;
import com.study.boardflab.dto.reply.ReplyListRequestDTO;
import com.study.boardflab.dto.reply.ReplyViewDTO;
import com.study.boardflab.mybatis.dao.ReplyDAO;
import com.study.boardflab.mybatis.dao.UserDAO;
import com.study.boardflab.mybatis.vo.ReplyVO;
import com.study.boardflab.service.ReplyService;
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
}
