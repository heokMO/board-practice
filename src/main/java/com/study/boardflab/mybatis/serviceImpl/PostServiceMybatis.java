package com.study.boardflab.mybatis.serviceImpl;

import com.study.boardflab.dto.post.PostCreateDTO;
import com.study.boardflab.dto.post.PostListRequestDTO;
import com.study.boardflab.dto.post.PostListResponseDTO;
import com.study.boardflab.dto.post.PostReadDTO;
import com.study.boardflab.mybatis.dao.BoardDAO;
import com.study.boardflab.mybatis.dao.ImageDAO;
import com.study.boardflab.mybatis.dao.PostDAO;
import com.study.boardflab.mybatis.dao.UserDAO;
import com.study.boardflab.mybatis.vo.PostVO;
import com.study.boardflab.service.PostService;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PostServiceMybatis implements PostService {
    private final PostDAO postDAO;
    private final UserDAO userDAO;
    private final BoardDAO boardDAO;
    private final ImageDAO imageDAO;

    public PostServiceMybatis(PostDAO postDAO, UserDAO userDAO, BoardDAO boardDAO, ImageDAO imageDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
        this.boardDAO = boardDAO;
        this.imageDAO = imageDAO;
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

    @Override
    public List<PostListResponseDTO> getList(PostListRequestDTO dto) {

        return postDAO.getList(dto.getBoardId(), dto.getLimit(), dto.getOffset());
    }

    @Override
    public PostReadDTO getPost(Long postId, String username) {

        PostVO vo = postDAO.getPost(postId);


        if(boardDAO.isLoginRequired(vo.getBoardId()) && username == null){
            throw new AuthenticationCredentialsNotFoundException("로그인이 필요합니다");
        }

        List<Integer> images = imageDAO.getIds(vo.getId());
        boolean updatable = vo.getNonMemNick() != null ||
                (vo.getWittenUserId() != null && Objects.equals(userDAO.getUserName(vo.getWittenUserId()), username));

        PostReadDTO dto = PostReadDTO.builder()
                .id(vo.getId())
                .title(vo.getTitle())
                .writer(vo.getWriterNickname())
                .updateTime(vo.getUpdateTime())
                .contents(vo.getContent())
                .views(vo.getViews() + 1)
                .images(images)
                .updatable(updatable)
                .build();

        increaseViews(vo);
        return dto;
    }

    private void increaseViews(PostVO vo) {
        postDAO.increaseViews(vo.getId());
    }
}
