package com.study.boardflab.mybatis.dao;

import com.study.boardflab.mybatis.vo.ImageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ImageDAO {
    List<Integer> getIds(Long postId);

    Long save(ImageVO vo);

    void setPost(@Param("infos") List<ImageVO> infos);

    ImageVO find(Long id);

    void delete(Long id);
}
