package com.study.boardflab.dto.reply;

import java.io.Serializable;

public class ReplyUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nonMemPw;
    private String content;

    public ReplyUpdateDTO() {
    }

    public ReplyUpdateDTO(String nonMemPw, String content) {
        this.nonMemPw = nonMemPw;
        this.content = content;
    }

    public String getNonMemPw() {
        return nonMemPw;
    }

    public String getContent() {
        return content;
    }
}
