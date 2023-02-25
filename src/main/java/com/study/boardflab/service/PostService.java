package com.study.boardflab.service;

import com.study.boardflab.dto.post.PostCreateDTO;
import org.springframework.transaction.annotation.Transactional;

public interface PostService {


    @Transactional
    Long createPost(PostCreateDTO dto, String username);
}
