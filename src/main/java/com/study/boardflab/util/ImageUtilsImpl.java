package com.study.boardflab.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
public class ImageUtilsImpl implements ImageUtils {


    @Override
    public String save(MultipartFile image) throws IOException{
        //TODO: Image Save 구현

        return UUID.randomUUID().toString() + ".jpeg";
    }

    @Override
    public byte[] get(String path) throws IOException {
        //TODO: Image Read 구현
        return new byte[0];
    }

    @Override
    public void delete(String path) throws IOException {
        //TODO: Image Delete 구현
    }
}
