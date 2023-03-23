package com.study.boardflab.dto.reply;

import java.io.Serializable;

public class ReplyCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long postId;
    private String content;
    private String nonMemNick;
    private String nonMemPw;

    public ReplyCreateDTO() {

    }

    public ReplyCreateDTO(Long postId, String content, String nonMemNick, String nonMemPw) {
        this.postId = postId;
        this.content = content;
        this.nonMemNick = nonMemNick;
        this.nonMemPw = nonMemPw;
    }

    public Long getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    public String getNonMemNick() {
        return nonMemNick;
    }

    public String getNonMemPw() {
        return nonMemPw;
    }
}
