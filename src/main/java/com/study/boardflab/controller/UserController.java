package com.study.boardflab.controller;

import com.study.boardflab.dto.messageWrap.SuccessMessageDTO;
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
    public SuccessMessageDTO create(@RequestBody UserCreateDTO userCreateDTO) {
        userService.create(userCreateDTO);

        return SuccessMessageDTO.builder()
                .data("username", userCreateDTO.getId())
                .build();
    }

    @GetMapping("/id/{id}")
    public SuccessMessageDTO checkGenerateAccountId(@PathVariable String id){

        return SuccessMessageDTO.builder()
                .data("canGenerate", userService.checkGenerateAccountId(id))
                .build();
    }

    @GetMapping("/nickname/{nickname}")
    public SuccessMessageDTO checkGenerateNickname(@PathVariable String nickname){

        return SuccessMessageDTO.builder()
                .data("canGenerate", userService.checkGenerateNickname(nickname))
                .build();
    }


    @PatchMapping
    public SuccessMessageDTO updateUser(@RequestBody UserUpdateDTO userUpdateDTO,
                           @AuthenticationPrincipal User user){
        checkLogin(user);

        userService.updateUser(user.getUsername(), userUpdateDTO);
        return SuccessMessageDTO.builder()
                .message("Update success")
                .build();
    }



    @DeleteMapping
    public SuccessMessageDTO deleteUser(@AuthenticationPrincipal User user){
        checkLogin(user);

        userService.deleteUser(user.getUsername());
        return SuccessMessageDTO.builder()
                .data("deleted username", user.getUsername())
                .build();
    }

    private static void checkLogin(User user) {
        if(user == null){
            throw new AuthenticationCredentialsNotFoundException("로그인이 필요합니다");
        }
    }
}
