package com.study.boardflab.dto.image;

import java.io.Serializable;

public class ImageDeleteResponseDTO implements Serializable {
    private final Long deletedImageId;

    public ImageDeleteResponseDTO(Long deletedImageId) {
        this.deletedImageId = deletedImageId;
    }

    public Long getDeletedImageId() {
        return deletedImageId;
    }
}
