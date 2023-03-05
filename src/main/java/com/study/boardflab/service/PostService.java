package com.study.boardflab.service;

import com.study.boardflab.dto.post.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostService {


    @Transactional
    Long createPost(PostCreateDTO dto, String username);

    List<PostListResponseDTO> getList(PostListRequestDTO dto);

    PostReadDTO getPost(Long postId, String username);

    void updatePost(Long postId, PostUpdateDTO dto, String username);
}
