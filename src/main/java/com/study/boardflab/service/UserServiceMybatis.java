package com.study.boardflab.service;

import com.study.boardflab.dto.user.UserCreateDTO;
import com.study.boardflab.mybatis.dao.UserDAO;
import com.study.boardflab.mybatis.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceMybatis implements UserService{
    private final UserDAO userDAO;

    public UserServiceMybatis(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Long create(UserCreateDTO userCreateDTO){
        UserVO vo = UserVO.builder()
                .accountId(userCreateDTO.getId())
                .password(userCreateDTO.getPassword())
                .nickname(userCreateDTO.getNickname())
                .email(userCreateDTO.getEmail())
                .build();
        return userDAO.create(vo);
    }

    @Override
    public boolean checkGenerateAccountId(String accountId) {
        return userDAO.checkGenerateAccountId(accountId);
    }

    @Override
    public boolean checkGenerateNickname(String nickname) {
        return userDAO.checkGenerateNickname(nickname);
    }
}
