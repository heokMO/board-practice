package com.study.boardflab.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
public class ImageUtilsImpl implements ImageUtils {


    @Override
    public String save(MultipartFile image) throws IOException{

        return UUID.randomUUID().toString() + ".jpeg";
    }

    @Override
    public byte[] get(String path) throws IOException {
        return new byte[0];
    }
}
