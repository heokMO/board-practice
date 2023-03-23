package com.study.boardflab.dto.image;

import java.io.Serializable;

public class SaveImageResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Long savedImageId;

    public SaveImageResponseDTO(Long savedImageId) {
        this.savedImageId = savedImageId;
    }

    public Long getSavedImageId() {
        return savedImageId;
    }
}
