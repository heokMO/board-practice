package com.study.boardflab.controller;

import com.study.boardflab.dto.post.PostCreateDTO;
import com.study.boardflab.dto.reply.ReplyCreateDTO;
import com.study.boardflab.service.PostService;
import com.study.boardflab.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;

@RestController
@RequestMapping("reply")
public class ReplyController {
    private final PostService postService;
    private final ReplyService replyService;

    public ReplyController(PostService postService, ReplyService replyService) {
        this.postService = postService;
        this.replyService = replyService;
    }

    @PostMapping
    public void create(@RequestBody ReplyCreateDTO dto,
                       @AuthenticationPrincipal User user){

        boolean loginRequired = isLoginRequired(dto.getPostId());

        checkWriterNull(dto, user, loginRequired);

        checkLoginRequired(user, loginRequired);

        replyService.create(dto, getUsername(user));
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
    private static String getUsername(User user) {
        String username = null;
        if(user != null){
            username = user.getUsername();
        }
        return username;
    }

}
