package com.study.boardflab.dto.user;

import java.io.Serializable;

public class UserCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * DTO: id -> Table: account_id
     */
    private String id;
    private String password;
    private String nickname;
    private String email;

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "UserCreateDTO{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public UserCreateDTO() {
    }

    public UserCreateDTO(String id, String password, String nickname, String email) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    public static UserCreateDTOBuilder builder(){
        return new UserCreateDTOBuilder();
    }

    public static final class UserCreateDTOBuilder {
        private String id;
        private String password;
        private String nickname;
        private String email;

        private UserCreateDTOBuilder() {
        }

        public static UserCreateDTOBuilder anUserCreateDTO() {
            return new UserCreateDTOBuilder();
        }

        public UserCreateDTOBuilder id(String id) {
            this.id = id;
            return this;
        }

        public UserCreateDTOBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserCreateDTOBuilder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public UserCreateDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserCreateDTO build() {
            return new UserCreateDTO(id, password, nickname, email);
        }
    }
}
