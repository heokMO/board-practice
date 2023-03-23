package com.study.boardflab.dto.reply;

import java.io.Serializable;

public class ReplyListRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final Integer OFFSET_DEFAULT_VALUE = 0;
    private static final Integer LIMIT_DEFAULT_VALUE = 10;

    private Long postId;
    private Integer offset = OFFSET_DEFAULT_VALUE;

    private Integer limit = LIMIT_DEFAULT_VALUE;

    public ReplyListRequestDTO() {

    }

    public ReplyListRequestDTO(Long postId, Integer offset, Integer limit) {
        this.postId = postId;
        this.offset = offset;
        this.limit = limit;
    }

    public Long getPostId() {
        return postId;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }
}
