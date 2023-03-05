package com.study.boardflab.mybatis.serviceImpl;

import com.study.boardflab.dto.image.ImagePostSetDTO;
import com.study.boardflab.mybatis.dao.ImageDAO;
import com.study.boardflab.mybatis.vo.ImageVO;
import com.study.boardflab.service.ImageService;
import com.study.boardflab.util.ImageUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceMybatis implements ImageService {
    private final ImageUtils imageUtil;

    private final ImageDAO imageDAO;

    public ImageServiceMybatis(ImageUtils imageUtil, ImageDAO imageDAO) {
        this.imageUtil = imageUtil;
        this.imageDAO = imageDAO;
    }

    @Override
    public Long saveImage(MultipartFile image) throws IOException {
        String path =  imageUtil.save(image);
        ImageVO vo = ImageVO.builder()
                .path(path)
                .originName(image.getOriginalFilename())
                .build();
        return imageDAO.save(vo);
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
        imageDAO.setPost(infos);
    }

    @Override
    public byte[] getFile(Long id) throws IOException {
        ImageVO vo = imageDAO.find(id);

        return imageUtil.get(vo.getPath());
    }
}
