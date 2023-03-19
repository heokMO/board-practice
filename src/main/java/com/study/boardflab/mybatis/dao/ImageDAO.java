package com.study.boardflab.mybatis.dao;

import com.study.boardflab.mybatis.vo.ImageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ImageDAO {
    List<Integer> getIds(Long postId);

    void save(ImageVO vo);

    Integer setPost(@Param("infos") List<ImageVO> infos);

    ImageVO find(Long id);

    Integer delete(Long id);
}
