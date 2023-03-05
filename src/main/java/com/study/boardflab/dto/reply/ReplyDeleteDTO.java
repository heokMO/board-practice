package com.study.boardflab.dto.reply;

import java.io.Serializable;

public class ReplyDeleteDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nonMemPw;

    public ReplyDeleteDTO() {

    }

    public ReplyDeleteDTO(String nonMemPw) {
        this.nonMemPw = nonMemPw;
    }

    public String getNonMemPw() {
        return nonMemPw;
    }
}
