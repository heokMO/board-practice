package com.study.boardflab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.boardflab.dto.user.UserCreateDTO;
import com.study.boardflab.dto.user.UserUpdateDTO;
import com.study.boardflab.mybatis.dao.UserDAO;
import com.study.boardflab.mybatis.vo.UserVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {
    private final static String ACCOUNT_ID = "test123";
    private final static String PASSWORD = "test";
    private final static String NICKNAME = "test";
    private final static String EMAIL = "test@sdf.com";


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void createTestUser(){
        UserVO testUser = UserVO.builder()
                .accountId(ACCOUNT_ID)
                .password(passwordEncoder.encode(PASSWORD))
                .nickname(NICKNAME)
                .email(EMAIL)
                .build();

        userDAO.create(testUser);
    }


    @After
    public void tearDown() throws Exception{
        userDAO.deleteAll();
    }


    @Test
    public void createTest() throws Exception{
        //given
        String url = "http://localhost:" + port + "/user";
        String id = ACCOUNT_ID + 1;
        String password = PASSWORD + 1;
        String nickname = NICKNAME + 1;
        String email = EMAIL + 1;
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
        String url = "http://localhost:" + port + "/user/id/" + ACCOUNT_ID;

        //when
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("false");
    }

    @Test
    public void checkGenerateNicknameTest() throws Exception{
        //given
        String url = "http://localhost:" + port + "/user/nickname/" + NICKNAME;

        //when
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("false");
    }

    @Test
    public void loginTest() throws Exception{

        mockMvc.perform(formLogin().user(ACCOUNT_ID).password(PASSWORD))
                .andExpect(authenticated().withUsername(ACCOUNT_ID));
    }

    @Test
    @WithMockUser(username = ACCOUNT_ID, password = PASSWORD)
    public void updateUserTest() throws Exception{
        //given
        String url = "http://localhost:" + port + "/user";
        UserUpdateDTO dto = new UserUpdateDTO("test123");

        mockMvc.perform(patch(url).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

}
