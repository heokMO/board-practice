package com.study.boardflab.mybatis.serviceImpl;

import com.study.boardflab.dto.post.*;
import com.study.boardflab.mybatis.dao.BoardDAO;
import com.study.boardflab.mybatis.dao.ImageDAO;
import com.study.boardflab.mybatis.dao.PostDAO;
import com.study.boardflab.mybatis.dao.UserDAO;
import com.study.boardflab.mybatis.vo.PostVO;
import com.study.boardflab.service.PostService;
import org.springframework.security.access.AccessDeniedException;
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

        postDAO.create(vo);
        return vo.getId();
    }

    @Override
    public List<PostListResponseDTO> getList(PostListRequestDTO dto) {

        return postDAO.getList(dto.getBoardId(), dto.getLimit(), dto.getOffset());
    }

    @Override
    public PostReadDTO getPost(Long postId, String username) {

        PostVO vo = postDAO.find(postId);


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

    @Override
    public void updatePost(Long postId, PostUpdateDTO dto, String username) {
        PostVO vo = postDAO.find(postId);

        if(!isModifiable(vo, dto.getNonMemPw(), username)){
            throw new AccessDeniedException("권한이 없습니다.");
        }

        PostVO updateVO = PostVO.builder()
                .id(vo.getId())
                .content(dto.getContents())
                .build();

        postDAO.update(updateVO);
    }

    @Override
    public void deletePost(Long postId, PostDeleteDTO dto, String username) {
        PostVO vo = postDAO.find(postId);

        if(!isModifiable(vo, dto.getNonMemPw(), username)){
            throw new AccessDeniedException("권한이 없습니다.");
        }

        postDAO.delete(vo);
    }

    private boolean isModifiable(PostVO vo, String attemptedPassword, String username){
        String nonMemPw = vo.getNonMemPw();
        if(nonMemPw != null){
            return nonMemPw.equals(attemptedPassword);
        } else {
            return vo.getWittenUserId().equals(userDAO.getId(username));
        }

    }

    private void increaseViews(PostVO vo) {
        postDAO.increaseViews(vo.getId());
    }
}
