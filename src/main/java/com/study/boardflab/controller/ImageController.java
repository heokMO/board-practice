package com.study.boardflab.controller;

import com.study.boardflab.dto.image.ImagePostSetDTO;
import com.study.boardflab.service.ImageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public Long uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
        return imageService.saveImage(image);
    }

    @PatchMapping
    public void setPost(@RequestBody List<ImagePostSetDTO> settingInfos){
        imageService.setPost(settingInfos);
    }

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] get(@PathVariable Long id) throws IOException {

        return imageService.getFile(id);
    }
}
