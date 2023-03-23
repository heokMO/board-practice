package com.study.boardflab.dto.messageWrap;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class SuccessMessageDTO extends MessageDTO{
    private static final long serialVersionUID = 1L;

    public static final String DEFAULT_MESSAGE = "success";

    private final Object data;

    public SuccessMessageDTO(Object data) {
        super(HttpStatus.OK.value(), DEFAULT_MESSAGE);
        this.data = data;
    }

    public SuccessMessageDTO(int statusCode, String message, Object data){
        super(statusCode, message);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public static SuccessMessageDTOBuilder builder(){
        return new SuccessMessageDTOBuilder();
    }

    public static final class SuccessMessageDTOBuilder {
        private int status = HttpStatus.OK.value();
        private String message = DEFAULT_MESSAGE;
        private Object data;

        private SuccessMessageDTOBuilder() {
        }

        public static SuccessMessageDTOBuilder aSuccessMessageDTO() {
            return new SuccessMessageDTOBuilder();
        }

        public SuccessMessageDTOBuilder status(int status) {
            this.status = status;
            return this;
        }

        public SuccessMessageDTOBuilder message(String message) {
            this.message = message;
            return this;
        }

        public SuccessMessageDTOBuilder data(Object data) {
            this.data = data;
            return this;
        }

        public SuccessMessageDTOBuilder data(String key, Object data) {
            this.data = Map.of(key, data);
            return this;
        }

        public SuccessMessageDTO build() {
            return new SuccessMessageDTO(status, message, data);
        }
    }
}
