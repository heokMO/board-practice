package com.study.boardflab.dto.post;

import java.io.Serializable;

public class PostDeleteDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nonMemPw;

    public PostDeleteDTO() {

    }

    public PostDeleteDTO(String nonMemPw) {

        this.nonMemPw = nonMemPw;
    }

    public String getNonMemPw() {
        return nonMemPw;
    }

    public static PostDeleteDTOBuilder builder(){
        return new PostDeleteDTOBuilder();
    }


    public static final class PostDeleteDTOBuilder {
        private String nonMemPw;

        private PostDeleteDTOBuilder() {
        }


        public PostDeleteDTOBuilder nonMemPw(String nonMemPw) {
            this.nonMemPw = nonMemPw;
            return this;
        }

        public PostDeleteDTO build() {
            return new PostDeleteDTO(nonMemPw);
        }
    }
}
