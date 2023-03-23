package com.study.boardflab.dto.reply;

import java.io.Serializable;

public class ReplyViewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String writer;
    private String content;
    private Boolean updatable;

    public ReplyViewDTO() {
    }

    public ReplyViewDTO(Long id, String writer, String content, Boolean updatable) {
        this.id = id;
        this.writer = writer;
        this.content = content;
        this.updatable = updatable;
    }

    public Long getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    public Boolean getUpdatable() {
        return updatable;
    }


    public static ReplyViewDTOBuilder builder(){
        return new ReplyViewDTOBuilder();
    }

    public static final class ReplyViewDTOBuilder {
        private Long id;
        private String writer;
        private String content;
        private Boolean updatable;

        private ReplyViewDTOBuilder() {
        }

        public static ReplyViewDTOBuilder aReplyViewDTO() {
            return new ReplyViewDTOBuilder();
        }

        public ReplyViewDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ReplyViewDTOBuilder writer(String writer) {
            this.writer = writer;
            return this;
        }

        public ReplyViewDTOBuilder content(String content) {
            this.content = content;
            return this;
        }

        public ReplyViewDTOBuilder updatable(Boolean updatable) {
            this.updatable = updatable;
            return this;
        }

        public ReplyViewDTO build() {
            return new ReplyViewDTO(id, writer, content, updatable);
        }
    }
}
