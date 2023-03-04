package com.study.boardflab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.boardflab.dto.post.PostCreateDTO;
import com.study.boardflab.mybatis.dao.BoardDAO;
import com.study.boardflab.mybatis.dao.PostDAO;
import com.study.boardflab.mybatis.dao.UserDAO;
import com.study.boardflab.mybatis.vo.BoardVO;
import com.study.boardflab.mybatis.vo.UserVO;
import com.study.boardflab.util.TestUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.study.boardflab.util.TestUser.ACCOUNT_ID;
import static com.study.boardflab.util.TestUser.PASSWORD;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PostControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private BoardDAO boardDAO;
    @Autowired
    private PostDAO postDAO;

    private UserVO testUser;
    private String defaultUrl;

    private final BoardVO loginReqBoard = BoardVO.builder()
            .name("loginReq")
            .loginReq(true)
            .build();
    private Integer loginReqBoardId;

    private final BoardVO loginUnReqBoard = BoardVO.builder()
            .name("loginUnReq")
            .loginReq(false)
            .build();

    private Integer loginUnReqBoardId;

    @Before
    public void initTestUser(){
        testUser = TestUser.getTestUserVO();
    }

    @Before
    public void createTestUser(){
        userDAO.create(testUser);
    }

    @Before
    public void setDefaultUrl(){
        defaultUrl = "http://localhost:" + port;
    }

    @Before
    public void createBoards(){
        boardDAO.createBoard(loginReqBoard);
        boardDAO.createBoard(loginUnReqBoard);
        loginReqBoardId = loginReqBoard.getId();
        loginUnReqBoardId = loginUnReqBoard.getId();
    }

    @After
    public void deleteTestUser(){
        userDAO.deleteAll();
    }

    @After
    public void deleteTestBoard(){
        boardDAO.deleteAll();
    }

    @After
    public void deleteTestPost(){
        postDAO.deleteAll();
    }


    @Test
    @WithMockUser(username = ACCOUNT_ID, password = PASSWORD)
    public void writePostAfterLoginTest() throws Exception{
        //given
        String url = defaultUrl + "/post";

        PostCreateDTO dto = PostCreateDTO.builder()
                .boardId(loginReqBoardId)
                .title("test")
                .content("test")
                .build();

        //when
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(greaterThanOrEqualTo(0)));
    }
}
