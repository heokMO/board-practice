package com.study.boardflab.dto.post;

import java.io.Serializable;

public class PostCreateResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Long createdPostId;

    public PostCreateResponseDTO(Long createdPostId) {
        this.createdPostId = createdPostId;
    }

    public Long getCreatedPostId() {
        return createdPostId;
    }
}
