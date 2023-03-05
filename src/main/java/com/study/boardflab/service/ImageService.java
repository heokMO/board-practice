package com.study.boardflab.service;

import com.study.boardflab.dto.image.ImagePostSetDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    Long saveImage(MultipartFile image) throws IOException;

    void setPost(List<ImagePostSetDTO> settingInfos);

    byte[] getFile(Long id) throws IOException;

    void delete(Long id) throws IOException;
}
