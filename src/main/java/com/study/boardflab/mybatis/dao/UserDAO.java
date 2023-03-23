package com.study.boardflab.mybatis.dao;

import com.study.boardflab.mybatis.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    void create(UserVO vo);

    boolean checkGenerateAccountId(String accountId);

    void deleteAll();

    boolean checkGenerateNickname(String nickname);

    UserVO findByAccountId(String username);

    Integer updateUser(UserVO vo);

    Integer deleteUser(String username);

    Long getId(String username);

    String getUserName(Long id);
}
