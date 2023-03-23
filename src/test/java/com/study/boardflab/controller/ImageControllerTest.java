package com.study.boardflab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.boardflab.dto.image.ImagePostSetDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doThrow;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ImageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void uploadImage() throws Exception {
        MockMultipartFile imageFile = new MockMultipartFile("image", "test.image", MediaType.IMAGE_PNG_VALUE, "test".getBytes());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.multipart("/image").file(imageFile);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("success"));
    }

    @Test
    void setPost() throws Exception {
        List<ImagePostSetDTO> imagePostSetDTOS = Arrays.asList(new ImagePostSetDTO(1L, 2L), new ImagePostSetDTO(2L, 3L));

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/image")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(imagePostSetDTOS)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void get() {
    }

    @Test
    void delete() {
    }
}