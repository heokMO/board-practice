package com.study.boardflab.dto.post;

import java.io.Serializable;

public class PostCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    public static PostCreateDTOBuilder builder(){
        return new PostCreateDTOBuilder();
    }

    private Integer boardId;
    private String title;
    private String content;
    private String nonMemNick;
    private String nonMemPw;

    public PostCreateDTO() {
    }

    public PostCreateDTO(Integer boardId, String title, String content, String nonMemNick, String nonMemPw) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.nonMemNick = nonMemNick;
        this.nonMemPw = nonMemPw;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
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

    @Override
    public String toString() {
        return "PostCreateDTO{" +
                "boardId=" + boardId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", nonMemNick='" + nonMemNick + '\'' +
                ", nonMemPw='" + nonMemPw + '\'' +
                '}';
    }

    public static final class PostCreateDTOBuilder {
        private Integer boardId;
        private String title;
        private String content;
        private String nonMemNick;
        private String nonMemPw;

        private PostCreateDTOBuilder() {
        }

        public static PostCreateDTOBuilder aPostCreateDTO() {
            return new PostCreateDTOBuilder();
        }

        public PostCreateDTOBuilder boardId(Integer boardId) {
            this.boardId = boardId;
            return this;
        }

        public PostCreateDTOBuilder title(String title) {
            this.title = title;
            return this;
        }

        public PostCreateDTOBuilder content(String content) {
            this.content = content;
            return this;
        }

        public PostCreateDTOBuilder nonMemNick(String nonMemNick) {
            this.nonMemNick = nonMemNick;
            return this;
        }

        public PostCreateDTOBuilder nonMemPw(String nonMemPw) {
            this.nonMemPw = nonMemPw;
            return this;
        }

        public PostCreateDTO build() {
            return new PostCreateDTO(boardId, title, content, nonMemNick, nonMemPw);
        }
    }
}
