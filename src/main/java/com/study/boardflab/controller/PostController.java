package com.study.boardflab.controller;

import com.study.boardflab.dto.post.PostCreateDTO;
import com.study.boardflab.dto.post.PostListRequestDTO;
import com.study.boardflab.dto.post.PostListResponseDTO;
import com.study.boardflab.dto.post.PostReadDTO;
import com.study.boardflab.service.BoardService;
import com.study.boardflab.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final BoardService boardService;

    public PostController(PostService postService, BoardService boardService) {
        this.postService = postService;
        this.boardService = boardService;
    }

    @PostMapping
    public Long writePost(@RequestBody PostCreateDTO dto,
                           @AuthenticationPrincipal User user){
        boolean loginRequired = isLoginRequired(dto.getBoardId());

        checkWriterNull(dto, user, loginRequired);

        checkLoginRequired(user, loginRequired);

        String username = null;
        if(user != null){
            username = user.getUsername();
        }

        return postService.createPost(dto, username);
    }

    @GetMapping("/list")
    public List<PostListResponseDTO> getList(@ModelAttribute PostListRequestDTO dto,
                                             @AuthenticationPrincipal User user){

        checkLoginRequired(user, isLoginRequired(dto.getBoardId()));

        return postService.getList(dto);
    }

    @GetMapping("/{postId}")
    public PostReadDTO getPost(@PathVariable Long postId,
                               @AuthenticationPrincipal User user){

        String username = null;
        if(user != null){
            username = user.getUsername();
        }

        return postService.getPost(postId, username);
    }

//    @PatchMapping("/post/{postId}")
//    public void updatePost(@PathVariable Long postId)
//
//


    private boolean isLoginRequired(int boardNum) {
        try{
            return boardService.isLoginRequired(boardNum);
        } catch (NullPointerException exception){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "존재하지 않는 게시판입니다.");
        }
    }

    private static void checkLoginRequired(User user, boolean loginRequired) {
        if(loginRequired && user == null){
            throw new AuthenticationCredentialsNotFoundException("로그인이 필요합니다");
        }
    }

    private void checkWriterNull(PostCreateDTO dto, User user, boolean loginRequired) {
        if(!loginRequired
                && isNullNonMemberInfo(dto)
                && user == null){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "닉네임 또는 비밀번호를 입력해주세요.");
        }
    }

    private boolean isNullNonMemberInfo(PostCreateDTO dto){
        return Objects.isNull(dto.getNonMemNick()) || Objects.isNull(dto.getNonMemPw());
    }
}
