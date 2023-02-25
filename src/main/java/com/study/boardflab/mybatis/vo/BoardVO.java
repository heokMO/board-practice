package com.study.boardflab.mybatis.vo;

import java.util.Objects;

public class BoardVO {

    private Integer id;
    private String name;
    private Boolean loginReq;
    private String description;

    public BoardVO() {
    }

    public BoardVO(Integer id, String name, Boolean loginReq, String description) {
        this.id = id;
        this.name = name;
        this.loginReq = loginReq;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getLoginReq() {
        return loginReq;
    }

    public String getDescription() {
        return description;
    }

    public static BoardVOBuilder builder(){
        return new BoardVOBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        return o instanceof BoardVO &&
                Objects.equals(this.id, ((BoardVO) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static final class BoardVOBuilder {
        private Integer id;
        private String name;
        private Boolean loginReq;
        private String description;

        private BoardVOBuilder() {
        }

        public static BoardVOBuilder aBoardVO() {
            return new BoardVOBuilder();
        }

        public BoardVOBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public BoardVOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BoardVOBuilder loginReq(Boolean loginReq) {
            this.loginReq = loginReq;
            return this;
        }

        public BoardVOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public BoardVO build() {
            return new BoardVO(id, name, loginReq, description);
        }
    }
}
