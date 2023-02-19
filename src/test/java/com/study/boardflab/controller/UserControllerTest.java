package com.study.boardflab.controller;

import com.study.boardflab.dto.user.UserCreateDTO;
import com.study.boardflab.mybatis.dao.UserDAO;
import com.study.boardflab.mybatis.vo.UserVO;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserDAO userDAO;

    @After
    public void tearDown() throws Exception{
        userDAO.deleteAll();
    }

    @Test
    public void createTest() throws Exception{
        //given
        String url = "http://localhost:" + port + "/user";
        String id = "test123";
        String password = "test";
        String nickname = "test";
        String email = "test@sdf.com";
        UserCreateDTO requestDTO = UserCreateDTO.builder()
                .id(id)
                .password(password)
                .nickname(nickname)
                .email(email)
                .build();

        //when
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestDTO, String.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void checkGenerateAccountIdTest() throws Exception{
        //given
        String accountId = "test123";
        String password = "test";
        String nickname = "test";
        String email = "test@sdf.com";

        UserVO vo = UserVO.builder()
                .accountId(accountId)
                .password(password)
                .nickname(nickname)
                .email(email)
                .build();

        userDAO.create(vo);

        String url = "http://localhost:" + port + "/user/id/" + accountId;

        //when
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("false");
    }

    @Test
    public void checkGenerateNicknameTest() throws Exception{
        //given
        String accountId = "test123";
        String password = "test";
        String nickname = "test";
        String email = "test@sdf.com";

        UserVO vo = UserVO.builder()
                .accountId(accountId)
                .password(password)
                .nickname(nickname)
                .email(email)
                .build();

        userDAO.create(vo);

        String url = "http://localhost:" + port + "/user/nickname/" + nickname;

        //when
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("false");
    }
}
