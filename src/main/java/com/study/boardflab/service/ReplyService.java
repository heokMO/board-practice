package com.study.boardflab.service;

import com.study.boardflab.dto.reply.*;

import java.util.List;

public interface ReplyService {
    void create(ReplyCreateDTO dto, String username);

    List<ReplyViewDTO> getList(ReplyListRequestDTO dto, String username);

    void update(Long id, ReplyUpdateDTO dto, String username);

    void delete(Long id, ReplyDeleteDTO dto, String username);
}
