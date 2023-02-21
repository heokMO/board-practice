package com.study.boardflab.controller;

import com.study.boardflab.dto.user.UserCreateDTO;
import com.study.boardflab.dto.user.UserUpdateDTO;
import com.study.boardflab.service.UserService;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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


    @PatchMapping
    public void updateUser(@RequestBody UserUpdateDTO userUpdateDTO,
                           @AuthenticationPrincipal User user){
        if(user == null){
            throw new AuthenticationCredentialsNotFoundException("로그인이 필요합니다");
        }

        userService.updateUser(user.getUsername(), userUpdateDTO);
    }
}
