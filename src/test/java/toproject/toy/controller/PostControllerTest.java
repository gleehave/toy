package toproject.toy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import toproject.toy.dto.PostDto;
import toproject.toy.dto.PostResponse;
import toproject.toy.service.PostService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PostController.class)
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private PostService postService;

    @Test
    void createPost() throws Exception{
        String title = "This is Title";
        String desciption = "This is Description";
        String content = "This is Content";

        PostDto postDto = new PostDto();
        postDto.setId(1L);
        postDto.setTitle(title);
        postDto.setDescription(desciption);
        postDto.setContent(content);

        ResponseEntity<PostDto> response = new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

        Mockito.when(postService.createPost(postDto)).thenReturn(postDto);

    }
}