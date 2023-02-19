package com.study.boardflab.controller;

import com.study.boardflab.dto.user.UserCreateDTO;
import com.study.boardflab.service.UserService;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void create(@RequestBody UserCreateDTO userCreateDTO) {
        userService.create(userCreateDTO);
    }

    @GetMapping("/id/{id}")
    public boolean checkGenerateAccountId(@PathVariable String id){
        return userService.checkGenerateAccountId(id);
    }

    @GetMapping("/nickname/{nickname}")
    public boolean checkGenerateNickname(@PathVariable String nickname){
        return userService.checkGenerateNickname(nickname);
    }


    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleSqlException(SQLException exception){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 파라미터 입니다.");
    }
}
