package com.study.boardflab.security;

import com.study.boardflab.mybatis.vo.UserVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AccountContext extends User {
    private final UserVO account;

    public AccountContext(UserVO account, Collection<? extends GrantedAuthority> authorities){
        super(account.getAccountId(), account.getPassword(), authorities);
        this.account = account;
    }

    public UserVO getAccount(){
        return account;
    }
}
