package com.study.boardflab.mybatis.dao;

import com.study.boardflab.mybatis.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostDAO {
    void createPost(PostVO vo);

    void deleteAll();
}
