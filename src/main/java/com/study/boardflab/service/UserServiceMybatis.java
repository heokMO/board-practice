package com.study.boardflab.service;

import com.study.boardflab.dto.user.UserCreateDTO;
import com.study.boardflab.mybatis.dao.UserDAO;
import com.study.boardflab.mybatis.vo.UserVO;
import com.study.boardflab.security.AccountContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceMybatis implements UserService{
    private static final String DEFAULT_ROLE = "user";

    private final UserDAO userDAO;

    private final PasswordEncoder passwordEncoder;

    public UserServiceMybatis(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Long create(UserCreateDTO userCreateDTO){
        UserVO vo = UserVO.builder()
                .accountId(userCreateDTO.getId())
                .password(passwordEncoder.encode(userCreateDTO.getPassword()))
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO account = userDAO.findByAccountId(username);
        if(Objects.isNull(account)){
            throw new UsernameNotFoundException("유효하지 않은 ID입니다.");
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(DEFAULT_ROLE));

        return new AccountContext(account, roles);
    }
}
