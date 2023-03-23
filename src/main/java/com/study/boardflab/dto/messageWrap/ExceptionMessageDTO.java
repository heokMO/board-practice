package com.study.boardflab.dto.messageWrap;

public class ExceptionMessageDTO extends MessageDTO{
    private static final long serialVersionUID = 1L;
    private final String error;
    private final String path;


    private ExceptionMessageDTO(int status, String error, String message, String path) {
        super(status, message);
        this.error = error;
        this.path = path;
    }


    public String getError() {
        return error;
    }


    public String getPath() {
        return path;
    }

    public static ExceptionMessageDTOBuilder builder(){
        return new ExceptionMessageDTOBuilder();
    }


    public static final class ExceptionMessageDTOBuilder {
        private String error;
        private String path;
        private int status;
        private String message;

        private ExceptionMessageDTOBuilder() {
        }

        public static ExceptionMessageDTOBuilder anExceptionMessageDTO() {
            return new ExceptionMessageDTOBuilder();
        }

        public ExceptionMessageDTOBuilder error(String error) {
            this.error = error;
            return this;
        }

        public ExceptionMessageDTOBuilder path(String path) {
            this.path = path;
            return this;
        }

        public ExceptionMessageDTOBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ExceptionMessageDTOBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ExceptionMessageDTO build() {
            return new ExceptionMessageDTO(status, error, message, path);
        }
    }
}
