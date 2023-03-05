package com.study.boardflab.dto.post;

import java.io.Serializable;

public class PostUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String contents;
    private String nonMemPw;

    public PostUpdateDTO() {
    }

    public PostUpdateDTO(String contents, String nonMemPw) {
        this.contents = contents;
        this.nonMemPw = nonMemPw;
    }

    public String getContents() {
        return contents;
    }

    public String getNonMemPw() {
        return nonMemPw;
    }

    public static PostUpdateDTOBuilder builder(){
        return new PostUpdateDTOBuilder();
    }


    public static final class PostUpdateDTOBuilder {
        private String contents;
        private String nonMemPw;

        private PostUpdateDTOBuilder() {
        }


        public PostUpdateDTOBuilder contents(String contents) {
            this.contents = contents;
            return this;
        }

        public PostUpdateDTOBuilder nonMemPw(String nonMemPw) {
            this.nonMemPw = nonMemPw;
            return this;
        }

        public PostUpdateDTO build() {
            return new PostUpdateDTO(contents, nonMemPw);
        }
    }
}
