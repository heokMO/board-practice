package com.study.boardflab.dto.image;

public class ImageGetDTO {
    private final Long id;
    private final String imageName;
    private final byte[] image;

    public ImageGetDTO(Long id, String imageName, byte[] image) {
        this.id = id;
        this.imageName = imageName;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getImageName() {
        return imageName;
    }

    public byte[] getImage() {
        return image;
    }

    public static ImageGetDTOBuilder builder(){
        return new ImageGetDTOBuilder();
    }

    public static final class ImageGetDTOBuilder {
        private Long id;
        private String imageName;
        private byte[] image;

        private ImageGetDTOBuilder() {
        }

        public static ImageGetDTOBuilder anImageGetDTO() {
            return new ImageGetDTOBuilder();
        }

        public ImageGetDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ImageGetDTOBuilder imageName(String imageName) {
            this.imageName = imageName;
            return this;
        }

        public ImageGetDTOBuilder image(byte[] image) {
            this.image = image;
            return this;
        }

        public ImageGetDTO build() {
            return new ImageGetDTO(id, imageName, image);
        }
    }
}
