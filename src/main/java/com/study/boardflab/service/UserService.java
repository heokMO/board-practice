package com.study.boardflab.service;

import com.study.boardflab.dto.user.UserCreateDTO;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;


public interface UserService {

    @Transactional
    Long create(UserCreateDTO userCreateDTO);

    boolean checkGenerateAccountId(String id);

    boolean checkGenerateNickname(String nickname);
}
