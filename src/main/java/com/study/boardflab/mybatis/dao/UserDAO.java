package com.study.boardflab.mybatis.dao;

import com.study.boardflab.mybatis.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    Long create(UserVO vo);

    boolean checkGenerateAccountId(String accountId);

    void deleteAll();

    boolean checkGenerateNickname(String nickname);

    UserVO findByAccountId(String username);
}
