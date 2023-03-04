package com.study.boardflab.service;

import com.study.boardflab.dto.post.PostCreateDTO;
import com.study.boardflab.dto.post.PostListRequestDTO;
import com.study.boardflab.dto.post.PostListResponseDTO;
import com.study.boardflab.dto.post.PostReadDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostService {


    @Transactional
    Long createPost(PostCreateDTO dto, String username);

    List<PostListResponseDTO> getList(PostListRequestDTO dto);

    PostReadDTO getPost(Long postId, String username);
}
