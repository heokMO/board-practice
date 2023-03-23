package com.study.boardflab.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageUtils {

    /**
     * @return Image path name
     */
    String save(MultipartFile image) throws IOException;

    byte[] get(String path) throws IOException;

    void delete(String path) throws IOException;
}
