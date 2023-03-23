package com.study.boardflab.controller;

import com.study.boardflab.dto.messageWrap.ExceptionMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ExceptionMessageDTO> handleSqlException(HttpServletRequest request, SQLException exception){
        LOGGER.error(exception.getMessage());

        return getMessage(request, exception, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<ExceptionMessageDTO> handleAuthenticationCredentialsNotFoundException(HttpServletRequest request, AuthenticationCredentialsNotFoundException exception){

        return getMessage(request, exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ExceptionMessageDTO> handleHttpClientErrorException(HttpServletRequest request, HttpClientErrorException exception){

        return getMessage(request, exception, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionMessageDTO> handleAccessDeniedException(HttpServletRequest request, AccessDeniedException exception){

        return getMessage(request, exception, HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionMessageDTO> handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException exception){

        return getMessage(request, exception, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ExceptionMessageDTO> handleIOException(HttpServletRequest request, IOException exception){

        return getMessage(request, exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ExceptionMessageDTO> getMessage(HttpServletRequest request, Exception e, HttpStatus status){
        ExceptionMessageDTO dto = ExceptionMessageDTO.builder()
                .status(status.value())
                .error(status.name())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity
                .status(status.value())
                .body(dto);
    }

}
