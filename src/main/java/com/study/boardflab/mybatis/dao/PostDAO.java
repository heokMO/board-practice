package com.study.boardflab.mybatis.dao;

import com.study.boardflab.dto.post.PostListResponseDTO;
import com.study.boardflab.mybatis.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostDAO {
    void create(PostVO vo);

    void deleteAll();

    List<PostListResponseDTO> getList(@Param("boardId") Integer boardId,
                                      @Param("limit") Integer limit,
                                      @Param("offset")Integer offset);

    PostVO find(Long postId);

    void increaseViews(Long postId);

    Integer update(PostVO updateVO);

    void delete(PostVO vo);

    Boolean isLoginRequired(Long postId);
}
