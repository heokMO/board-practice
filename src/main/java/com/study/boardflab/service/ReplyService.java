package com.study.boardflab.service;

import com.study.boardflab.dto.reply.ReplyCreateDTO;
import com.study.boardflab.dto.reply.ReplyListRequestDTO;
import com.study.boardflab.dto.reply.ReplyUpdateDTO;
import com.study.boardflab.dto.reply.ReplyViewDTO;

import java.util.List;

public interface ReplyService {
    void create(ReplyCreateDTO dto, String username);

    List<ReplyViewDTO> getList(ReplyListRequestDTO dto, String username);

    void update(Long id, ReplyUpdateDTO dto, String username);
}
