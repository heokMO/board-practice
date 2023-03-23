package com.study.boardflab.dto.post;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PostListResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String title;
    private String writer;

    private String nonMemNick;
    private LocalDateTime writtenTime;
    private Integer views;
    private Integer replys;

    public PostListResponseDTO() {

    }

    public PostListResponseDTO(Integer id, String title, String writer, String nonMemNick, LocalDateTime writtenTime, Integer views, Integer replys) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.nonMemNick = nonMemNick;
        this.writtenTime = writtenTime;
        this.views = views;
        this.replys = replys;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public LocalDateTime getWrittenTime() {
        return writtenTime;
    }

    public Integer getViews() {
        return views;
    }

    public Integer getReplys() {
        return replys;
    }

    public String getNonMemNick() {
        return nonMemNick;
    }

    public static PostListResponseDTOBuilder builder(){
        return new PostListResponseDTOBuilder();
    }


    public static final class PostListResponseDTOBuilder {
        private Integer id;
        private String title;
        private String writer;
        private String nonMemNick;
        private LocalDateTime writtenTime;
        private Integer views;
        private Integer replys;

        private PostListResponseDTOBuilder() {
        }

        public static PostListResponseDTOBuilder aPostListResponseDTO() {
            return new PostListResponseDTOBuilder();
        }

        public PostListResponseDTOBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public PostListResponseDTOBuilder title(String title) {
            this.title = title;
            return this;
        }

        public PostListResponseDTOBuilder writer(String writer) {
            this.writer = writer;
            return this;
        }

        public PostListResponseDTOBuilder nonMemNick(String nonMemNick) {
            this.nonMemNick = nonMemNick;
            return this;
        }

        public PostListResponseDTOBuilder writtenTime(LocalDateTime writtenTime) {
            this.writtenTime = writtenTime;
            return this;
        }

        public PostListResponseDTOBuilder views(Integer views) {
            this.views = views;
            return this;
        }

        public PostListResponseDTOBuilder replys(Integer replys) {
            this.replys = replys;
            return this;
        }

        public PostListResponseDTO build() {
            return new PostListResponseDTO(id, title, writer, nonMemNick, writtenTime, views, replys);
        }
    }
}
