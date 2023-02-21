package com.study.boardflab.dto.user;

import java.io.Serializable;

public class UserUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nickname;

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
