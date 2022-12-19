package toproject.toy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import toproject.toy.dto.SignUpDto;
import toproject.toy.entity.Role;
import toproject.toy.entity.User;
import toproject.toy.repository.RoleRepository;
import toproject.toy.repository.UserRepository;
import toproject.toy.security.JwtTokenProvider;
import toproject.toy.service.PostService;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void setup() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");

        roleRepository.save(role);
    }

    @AfterEach
    void delete_user() {
        Optional<User> target = userRepository.findByUsername("istest");
        userRepository.deleteById(target.get().getId());
    }

    @Test
    void SignUpTest() throws Exception {
        SignUpDto testDto = new SignUpDto();

        testDto.setName("test");
        testDto.setUsername("istest");
        testDto.setEmail("test@gmail.com");
        testDto.setPassword("test");

        String request = objectMapper.writeValueAsString(testDto);

        mockMvc.perform(post("/api/auth/signup")
                .content(request).contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());

    }
}