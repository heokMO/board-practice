package com.study.boardflab.mybatis.serviceImpl;

import com.study.boardflab.dto.post.PostCreateDTO;
import com.study.boardflab.mybatis.dao.PostDAO;
import com.study.boardflab.mybatis.dao.UserDAO;
import com.study.boardflab.mybatis.vo.PostVO;
import com.study.boardflab.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceMybatis implements PostService {
    private final PostDAO postDAO;
    private final UserDAO userDAO;

    public PostServiceMybatis(PostDAO postDAO, UserDAO userDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Long createPost(PostCreateDTO dto, String username) {
        PostVO vo = PostVO.builder()
                .boardId(dto.getBoardId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .nonMemNick(dto.getNonMemNick())
                .nonMemPw(dto.getNonMemPw())
                .wittenUserId(userDAO.getId(username))
                .build();

        postDAO.createPost(vo);
        return vo.getId();
    }
}
