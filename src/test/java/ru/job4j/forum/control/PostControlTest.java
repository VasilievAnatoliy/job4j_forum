package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class PostControlTest {

    @MockBean
    private PostService posts;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void whenDescriptionPost() throws Exception {
        when(posts.findById(1)).thenReturn(
                Post.of("name", "desc", new User()));
        this.mockMvc.perform(get("/post/{postId}", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));

    }

    @Test
    @WithMockUser
    public void whenCreatePost() throws Exception {
        this.mockMvc.perform(get("/createPost")
                        .param("id", "1")
                        .param("name", "name")
                        .param("description", "description"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    @WithMockUser
    public void whenUpdatePost() throws Exception {
        when(posts.findById(1)).thenReturn(
                Post.of("name", "desc", new User()));
        this.mockMvc.perform(get("/updatePost/{postId}", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    @WithMockUser
    public void whenDeletePost() throws Exception {
        when(posts.findById(1)).thenReturn(
                Post.of("name", "desc", new User()));
        this.mockMvc.perform(get("/deletePost/{postId}", 1))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));
    }
}