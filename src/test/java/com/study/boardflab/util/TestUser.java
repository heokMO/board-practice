package com.study.boardflab.util;

import com.study.boardflab.config.SecurityConfig;
import com.study.boardflab.mybatis.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.ContextLoader;


import javax.annotation.PostConstruct;


public class TestUser {
    public final static String ACCOUNT_ID = "test123";
    public final static String PASSWORD = "test";
    public final static String NICKNAME = "test";
    public final static String EMAIL = "test@sdf.com";



    private static UserVO testUser;

    public static UserVO getTestUserVO(){
        if(testUser == null){
            PasswordEncoder passwordEncoder = new AnnotationConfigApplicationContext(SecurityConfig.class).getBean(PasswordEncoder.class);

            testUser = UserVO.builder()
                    .accountId(ACCOUNT_ID)
                    .password(passwordEncoder.encode(PASSWORD))
                    .nickname(NICKNAME)
                    .email(EMAIL)
                    .build();
        }
        return testUser;
    }


}
