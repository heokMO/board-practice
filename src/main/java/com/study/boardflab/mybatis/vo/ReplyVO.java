package com.study.boardflab.mybatis.vo;

import java.time.LocalDateTime;
import java.util.Objects;

public class ReplyVO {
    private Long id;
    private Long postId;
    private String content;
    private Long writtenUser;
    private LocalDateTime writtenTime;
    private LocalDateTime updateTime;
    private String nonMemNick;
    private String nonMemPw;

    public ReplyVO() {

    }

    public ReplyVO(Long id, Long postId, String content, Long writtenUser, LocalDateTime writtenTime, LocalDateTime updateTime, String nonMemNick, String nonMemPw) {
        this.id = id;
        this.postId = postId;
        this.content = content;
        this.writtenUser = writtenUser;
        this.writtenTime = writtenTime;
        this.updateTime = updateTime;
        this.nonMemNick = nonMemNick;
        this.nonMemPw = nonMemPw;
    }

    public Long getId() {
        return id;
    }

    public Long getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    public Long getWrittenUser() {
        return writtenUser;
    }

    public LocalDateTime getWrittenTime() {
        return writtenTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public String getNonMemNick() {
        return nonMemNick;
    }

    public String getNonMemPw() {
        return nonMemPw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        return o instanceof ReplyVO &&
                Objects.equals(id, ((ReplyVO) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static ReplyVOBuilder builder(){
        return new ReplyVOBuilder();
    }

    public static final class ReplyVOBuilder {
        private Long id;
        private Long postId;
        private String content;
        private Long writtenUser;
        private LocalDateTime writtenTime;
        private LocalDateTime updateTime;
        private String nonMemNick;
        private String nonMemPw;

        private ReplyVOBuilder() {
        }

        public static ReplyVOBuilder aReplyVO() {
            return new ReplyVOBuilder();
        }

        public ReplyVOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ReplyVOBuilder postId(Long postId) {
            this.postId = postId;
            return this;
        }

        public ReplyVOBuilder content(String content) {
            this.content = content;
            return this;
        }

        public ReplyVOBuilder writtenUser(Long writtenUser) {
            this.writtenUser = writtenUser;
            return this;
        }

        public ReplyVOBuilder writtenTime(LocalDateTime writtenTime) {
            this.writtenTime = writtenTime;
            return this;
        }

        public ReplyVOBuilder updateTime(LocalDateTime updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public ReplyVOBuilder nonMemNick(String nonMemNick) {
            this.nonMemNick = nonMemNick;
            return this;
        }

        public ReplyVOBuilder nonMemPw(String nonMemPw) {
            this.nonMemPw = nonMemPw;
            return this;
        }

        public ReplyVO build() {
            return new ReplyVO(id, postId, content, writtenUser, writtenTime, updateTime, nonMemNick, nonMemPw);
        }
    }
}
