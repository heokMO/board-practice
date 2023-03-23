package com.study.boardflab.dto.post;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.io.Serializable;

public class PostListRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Integer OFFSET_DEFAULT_VALUE = 0;
    private static final Integer LIMIT_DEFAULT_VALUE = 10;

    private Integer boardId;
    private Integer limit = LIMIT_DEFAULT_VALUE;
    private Integer offset = OFFSET_DEFAULT_VALUE;

    public PostListRequestDTO() {
    }

    public PostListRequestDTO(Integer boardId, Integer limit, Integer offset) {
        this.boardId = boardId;
        this.limit = limit;
        this.offset = offset;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "PostListRequestDTO{" +
                "boardId=" + boardId +
                ", limit=" + limit +
                ", offset=" + offset +
                '}';
    }

    public static PostListRequestDTOBuilder builder(){
        return new PostListRequestDTOBuilder();
    }

    public static final class PostListRequestDTOBuilder {
        private Integer boardId;
        private Integer limit;
        private Integer offset;

        private PostListRequestDTOBuilder() {
        }

        public static PostListRequestDTOBuilder aPostListRequestDTO() {
            return new PostListRequestDTOBuilder();
        }

        public PostListRequestDTOBuilder boardId(Integer boardId) {
            this.boardId = boardId;
            return this;
        }

        public PostListRequestDTOBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public PostListRequestDTOBuilder offset(Integer offset) {
            this.offset = offset;
            return this;
        }

        public PostListRequestDTO build() {
            return new PostListRequestDTO(boardId, limit, offset);
        }
    }
}
