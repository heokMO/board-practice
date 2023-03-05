package com.study.boardflab.service;

import com.study.boardflab.dto.reply.ReplyCreateDTO;

public interface ReplyService {
    void create(ReplyCreateDTO dto, String username);
}
