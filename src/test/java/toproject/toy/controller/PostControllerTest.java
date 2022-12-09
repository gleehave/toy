package toproject.toy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import toproject.toy.dto.PostDto;
import toproject.toy.service.PostService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PostControllerTest {

    @Mock
    private User user;

    @Autowired
    private PostService postService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createPost() throws Exception {
        PostDto postDto = new PostDto();
        postDto.setId(1L);
        postDto.setTitle("TITLE");
        postDto.setContent("Content");
        postDto.setDescription("This is desciprtion");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/posts/create")
                .content(objectMapper.writeValueAsString(postDto)))
                .andExpect(status().isUnauthorized());
    }
}