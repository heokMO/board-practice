package com.study.boardflab.mybatis.dao;

import com.study.boardflab.dto.post.PostListResponseDTO;
import com.study.boardflab.mybatis.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostDAO {
    void createPost(PostVO vo);

    void deleteAll();

    List<PostListResponseDTO> getList(@Param("boardId") Integer boardId,
                                      @Param("limit") Integer limit,
                                      @Param("offset")Integer offset);

    PostVO getPost(Long postId);

    void increaseViews(Long postId);

    void updatePost(PostVO updateVO);
}
