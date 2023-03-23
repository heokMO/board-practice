package com.study.boardflab.service;

import com.study.boardflab.dto.image.ImageGetDTO;
import com.study.boardflab.dto.image.ImagePostSetDTO;
import com.study.boardflab.mybatis.dao.ImageDAO;
import com.study.boardflab.mybatis.vo.ImageVO;
import com.study.boardflab.util.ImageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageUtils imageUtil;

    private final ImageDAO imageDAO;



    public ImageServiceImpl(ImageUtils imageUtil, ImageDAO imageDAO) {
        this.imageUtil = imageUtil;
        this.imageDAO = imageDAO;
    }

    @Override
    public Long saveImage(MultipartFile image) throws IOException {
        String path =  imageUtil.save(image);

        ImageVO vo = ImageVO.builder()
                .path(path)
                .originName(image.getName())
                .build();
        imageDAO.save(vo);
        return vo.getId();
    }

    @Override
    public void setPost(List<ImagePostSetDTO> settingInfos) {
        if(CollectionUtils.isEmpty(settingInfos)){
            throw new IllegalArgumentException("설정 값이 비어있습니다.");
        }

        List<ImageVO> infos = settingInfos
                .stream()
                .map(dto -> ImageVO.builder()
                        .id(dto.getImageId())
                        .postId(dto.getPostId())
                        .build())
                .collect(Collectors.toList());

        if(imageDAO.setPost(infos) != 1){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "변경되지 않은 Image가 있습니다.");
        }
    }

    @Override
    public ImageGetDTO getFile(Long id) throws IOException {
        ImageVO vo = imageDAO.find(id);

        byte[] imageData = imageUtil.get(vo.getPath());

        return ImageGetDTO.builder()
                .id(vo.getId())
                .imageName(vo.getOriginName())
                .image(imageData)
                .build();
    }

    @Override
    public void delete(Long id) throws IOException {
        ImageVO vo = imageDAO.find(id);
        imageUtil.delete(vo.getPath());

        if(imageDAO.delete(id) != 1){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "해당 Image가 존재하지 않습니다.");
        }
    }
}
