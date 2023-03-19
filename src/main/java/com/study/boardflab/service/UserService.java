package com.study.boardflab.service;

import com.study.boardflab.dto.user.UserCreateDTO;
import com.study.boardflab.dto.user.UserUpdateDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;


public interface UserService extends UserDetailsService {


    void create(UserCreateDTO userCreateDTO);

    boolean checkGenerateAccountId(String id);

    boolean checkGenerateNickname(String nickname);

    void updateUser(String username, UserUpdateDTO userUpdateDTO);

    void deleteUser(String username);
}
