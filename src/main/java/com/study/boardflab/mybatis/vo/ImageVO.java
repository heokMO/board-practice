package com.study.boardflab.mybatis.vo;

import java.util.Objects;

public class ImageVO {
    private Long id;
    private Long postId;
    private String originName;
    private String path;

    public ImageVO() {

    }

    public ImageVO(Long id, Long postId, String originName, String path) {
        this.id = id;
        this.postId = postId;
        this.originName = originName;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public Long getPostId() {
        return postId;
    }

    public String getOriginName() {
        return originName;
    }

    public String getPath() {
        return path;
    }

    public static ImageVOBuilder builder(){
        return new ImageVOBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        return o instanceof ImageVO &&
                Objects.equals(id, ((ImageVO) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static final class ImageVOBuilder {
        private Long id;
        private Long postId;
        private String originName;
        private String path;

        private ImageVOBuilder() {
        }


        public ImageVOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ImageVOBuilder postId(Long postId) {
            this.postId = postId;
            return this;
        }

        public ImageVOBuilder originName(String originName) {
            this.originName = originName;
            return this;
        }

        public ImageVOBuilder path(String path) {
            this.path = path;
            return this;
        }

        public ImageVO build() {
            return new ImageVO(id, postId, originName, path);
        }
    }
}
