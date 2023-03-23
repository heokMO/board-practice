package com.study.boardflab.controller;

import com.study.boardflab.dto.messageWrap.SuccessMessageDTO;
import com.study.boardflab.dto.post.*;
import com.study.boardflab.service.BoardService;
import com.study.boardflab.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

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
    public SuccessMessageDTO writePost(@RequestBody PostCreateDTO dto,
                           @AuthenticationPrincipal User user) throws IOException {
        boolean loginRequired = isLoginRequired(dto.getBoardId());

        checkWriterNull(dto, user, loginRequired);

        checkLoginRequired(user, loginRequired);

        Long createdPostId = postService.createPost(dto, getUsername(user));

        return SuccessMessageDTO.builder()
                .data(new PostCreateResponseDTO(createdPostId))
                .build();
    }

    @GetMapping("/list")
    public SuccessMessageDTO getList(@ModelAttribute PostListRequestDTO dto,
                                             @AuthenticationPrincipal User user){

        checkLoginRequired(user, isLoginRequired(dto.getBoardId()));

        return SuccessMessageDTO.builder()
                .data("list", postService.getList(dto))
                .build();
    }

    @GetMapping("/{postId}")
    public SuccessMessageDTO getPost(@PathVariable Long postId,
                               @AuthenticationPrincipal User user){

        return SuccessMessageDTO.builder()
                .data("post", postService.getPost(postId, getUsername(user)))
                .build();
    }



    @PatchMapping("/{postId}")
    public SuccessMessageDTO updatePost(@PathVariable Long postId,
                           @RequestBody PostUpdateDTO dto,
                           @AuthenticationPrincipal User user){

        postService.updatePost(postId, dto, getUsername(user));

        return SuccessMessageDTO.builder()
                .data("updatedPostId", postId)
                .build();
    }

    @DeleteMapping("/{postId}")
    public SuccessMessageDTO deletePost(@PathVariable Long postId,
                           @RequestBody PostDeleteDTO dto,
                           @AuthenticationPrincipal User user){
        postService.deletePost(postId, dto, getUsername(user));

        return SuccessMessageDTO.builder()
                .data("deletedPostId", postId)
                .build();
    }

    private String getUsername(User user) {
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
                && user == null){
            checkNullNonMemberInfo(dto);
        }
    }

    private void checkNullNonMemberInfo(PostCreateDTO dto){
        if(dto.getNonMemNick() == null){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "닉네임을 입력해주세요.");
        }
        if(dto.getNonMemPw() == null){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "비밀번호를 입력해주세요.");
        }
    }
}
