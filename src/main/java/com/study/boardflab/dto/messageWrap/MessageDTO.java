package com.study.boardflab.dto.messageWrap;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class MessageDTO implements Serializable {
    private final int status;
    private final String message;
    private final LocalDateTime timestamp;

    public MessageDTO(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
