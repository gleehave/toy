package toproject.toy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import toproject.toy.config.SecurityConfig;
import toproject.toy.dto.LoginDto;
import toproject.toy.dto.PostDto;
import toproject.toy.entity.User;
import toproject.toy.repository.UserRepository;
import toproject.toy.service.PostService;
import toproject.toy.utils.WithMockCustomUser;

import java.util.Optional;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PostControllerTest {

    @Autowired
    private PostService postService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    private SecurityConfig securityConfig;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    @WithMockUser
    void createPost() throws Exception {
        /*
        PostDto postDto = new PostDto();

        postDto.setId(1L);
        postDto.setTitle("TITLE");
        postDto.setContent("Content");
        postDto.setDescription("This is desciprtion");

        String contentbody = objectMapper.writeValueAsString(postDto);

        LoginDto loginDto = new LoginDto();
        loginDto.setUsernameOrEmail("glee");
        loginDto.setPassword("password1");

        String loginbody = objectMapper.writeValueAsString(loginDto);

        mockMvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginbody)
                        .header("Bearer ").
                .accept(MediaType.APPLICATION_JSON)).andDo(print());


        mockMvc.perform(post("/api/posts/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(contentbody)
                .accept(MediaType.APPLICATION_JSON)).andDo(print());
        */

        PostDto postDto = new PostDto();

        postDto.setId(1L);
        postDto.setTitle("TITLE");
        postDto.setContent("Content");
        postDto.setDescription("This is desciprtion");

        String contentbody = objectMapper.writeValueAsString(postDto);
        ResultActions result = mockMvc.perform(post("/api/posts/create")
                .contentType(MediaType.APPLICATION_JSON).content(contentbody));

    }
}