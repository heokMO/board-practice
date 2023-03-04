package com.study.boardflab.mybatis.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageDAO {
    List<Integer> getIds(Long postId);
}
