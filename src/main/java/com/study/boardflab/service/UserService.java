package com.study.boardflab.service;

import com.study.boardflab.dto.user.UserCreateDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;


public interface UserService extends UserDetailsService {

    @Transactional
    Long create(UserCreateDTO userCreateDTO);

    boolean checkGenerateAccountId(String id);

    boolean checkGenerateNickname(String nickname);
}
