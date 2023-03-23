package com.study.boardflab.controller;

import com.study.boardflab.dto.messageWrap.SuccessMessageDTO;
import com.study.boardflab.dto.reply.*;
import com.study.boardflab.service.PostService;
import com.study.boardflab.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/reply")
public class ReplyController {
    private final PostService postService;
    private final ReplyService replyService;

    public ReplyController(PostService postService, ReplyService replyService) {
        this.postService = postService;
        this.replyService = replyService;
    }

    @PostMapping
    public SuccessMessageDTO create(@RequestBody ReplyCreateDTO dto,
                                    @AuthenticationPrincipal User user){

        boolean loginRequired = isLoginRequired(dto.getPostId());

        checkWriterNull(dto, user, loginRequired);

        checkLoginRequired(user, loginRequired);

        replyService.create(dto, getUsername(user));

        return SuccessMessageDTO.builder().build();
    }

    @GetMapping
    public SuccessMessageDTO getList(@RequestBody ReplyListRequestDTO dto,
                                      @AuthenticationPrincipal User user){

        checkLoginRequired(user, isLoginRequired(dto.getPostId()));

        return SuccessMessageDTO.builder()
                .data("list", replyService.getList(dto, getUsername(user)))
                .build();
    }

    @PatchMapping("{id}")
    public SuccessMessageDTO update(@PathVariable Long id,
                       @RequestBody ReplyUpdateDTO dto,
                       @AuthenticationPrincipal User user){
        replyService.update(id, dto, getUsername(user));

        return SuccessMessageDTO.builder()
                .data("updatedReplyId", id)
                .build();
    }

    @DeleteMapping("{id}")
    public SuccessMessageDTO delete(@PathVariable Long id,
                       @RequestBody ReplyDeleteDTO dto,
                       @AuthenticationPrincipal User user){
        replyService.delete(id, dto, getUsername(user));

        return SuccessMessageDTO.builder()
                .data("deletedReplyId", id)
                .build();
    }


    private void checkWriterNull(ReplyCreateDTO dto, User user, boolean loginRequired) {
        if(!loginRequired
                && isNullNonMemberInfo(dto)
                && user == null){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "닉네임 또는 비밀번호를 입력해주세요.");
        }
    }
    private boolean isNullNonMemberInfo(ReplyCreateDTO dto){
        return Objects.isNull(dto.getNonMemNick()) || Objects.isNull(dto.getNonMemPw());
    }

    private boolean isLoginRequired(Long postId) {
        return postService.isLoginRequired(postId);
    }

    private static void checkLoginRequired(User user, boolean loginRequired) {
        if(loginRequired && user == null){
            throw new AuthenticationCredentialsNotFoundException("로그인이 필요합니다");
        }
    }
    private String getUsername(User user) {
        String username = null;
        if(user != null){
            username = user.getUsername();
        }
        return username;
    }

}
