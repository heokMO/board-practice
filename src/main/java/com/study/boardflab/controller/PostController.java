package com.study.boardflab.controller;

import com.study.boardflab.dto.post.*;
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

        return postService.createPost(dto, getUsername(user));
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

        return postService.getPost(postId, getUsername(user));
    }



    @PatchMapping("/{postId}")
    public void updatePost(@PathVariable Long postId,
                           @RequestBody PostUpdateDTO dto,
                           @AuthenticationPrincipal User user){

        postService.updatePost(postId, dto, getUsername(user));
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId,
                           @RequestBody PostDeleteDTO dto,
                           @AuthenticationPrincipal User user){
        postService.deletePost(postId, dto, getUsername(user));
    }

    private static String getUsername(User user) {
        String username = null;
        if(user != null){
            username = user.getUsername();
        }
        return username;
    }



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
