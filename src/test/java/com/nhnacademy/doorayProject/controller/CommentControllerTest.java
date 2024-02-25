package com.nhnacademy.doorayProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.doorayProject.dto.CommentDto;
import com.nhnacademy.doorayProject.dto.CommentUpdateDto;
import com.nhnacademy.doorayProject.entity.Comment;
import com.nhnacademy.doorayProject.service.CommentService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Test
    void testGetController() throws Exception {
        List<Comment> mockCommentList = Arrays.asList(new Comment(), new Comment());
        when(commentService.getComments(any(Integer.class))).thenReturn(mockCommentList);

        mockMvc.perform(get("/projects/tasks/1/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(commentService,atLeastOnce()).getComments(1);
    }

    @Test
    void testPostController() throws Exception {
        CommentDto commentDto = new CommentDto("1234","qwer");

        when(commentService.addComment(any(Integer.class),any(CommentDto.class))).thenReturn(commentDto);

        mockMvc.perform(post("/projects/tasks/1/comments")
                .content(new ObjectMapper().writeValueAsString(commentDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.commentContent", equalTo("qwer")))
                .andExpect(jsonPath("$.userId",equalTo("1234")));
    }

    @Test
    void testUpdateComment() throws Exception {
        CommentUpdateDto commentUpdateDto = new CommentUpdateDto("qwer");

        when(commentService.updateCommemt(any(Integer.class),any(CommentUpdateDto.class))).thenReturn(commentUpdateDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/projects/tasks/comments/1/update")
                .content(new ObjectMapper().writeValueAsString(commentUpdateDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commentContent", equalTo("qwer")));
    }

    @Test
    void testDeleteComment() throws Exception {
        doNothing().when(commentService).deleteComment(any(Integer.class));

        mockMvc.perform(MockMvcRequestBuilders.delete("/projects/tasks/comments/1/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(commentService,atLeastOnce()).deleteComment(1);
    }
}
