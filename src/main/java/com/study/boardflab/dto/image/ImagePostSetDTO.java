package com.study.boardflab.dto.image;

import java.io.Serializable;

public class ImagePostSetDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long imageId;
    private Long postId;

    public ImagePostSetDTO() {

    }

    public ImagePostSetDTO(Long imageId, Long postId) {
        this.imageId = imageId;
        this.postId = postId;
    }

    public Long getImageId() {
        return imageId;
    }

    public Long getPostId() {
        return postId;
    }
}
