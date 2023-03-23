package com.study.boardflab.controller;

import com.study.boardflab.dto.image.ImageDeleteResponseDTO;
import com.study.boardflab.dto.image.ImagePostSetDTO;
import com.study.boardflab.dto.image.SaveImageResponseDTO;
import com.study.boardflab.dto.messageWrap.SuccessMessageDTO;
import com.study.boardflab.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public SuccessMessageDTO uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
        if(image == null){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Image file이 비어있습니다.");
        }

        Long saveImageId = imageService.saveImage(image);
        SaveImageResponseDTO dto = new SaveImageResponseDTO(saveImageId);

        return new SuccessMessageDTO(dto);
    }

    @PatchMapping
    public SuccessMessageDTO setPost(@RequestBody List<ImagePostSetDTO> settingInfos){
        imageService.setPost(settingInfos);

        return SuccessMessageDTO.builder()
                .message("All contents changed")
                .build();
    }

    @GetMapping(value = "/{id}")
    public SuccessMessageDTO get(@PathVariable Long id) throws IOException {

        return new SuccessMessageDTO(imageService.getFile(id));
    }

    @DeleteMapping(value = "/{id}")
    public SuccessMessageDTO delete(@PathVariable Long id) throws IOException {
        imageService.delete(id);

        return new SuccessMessageDTO(new ImageDeleteResponseDTO(id));
    }
}
