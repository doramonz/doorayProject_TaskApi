package com.nhnacademy.doorayProject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.doorayProject.dto.comment.CommentInfoListDto;
import com.nhnacademy.doorayProject.dto.comment.CommentUpdateDto;
import com.nhnacademy.doorayProject.dto.comment.CommentUploadDto;
import com.nhnacademy.doorayProject.exeption.CommentNotFoundException;
import com.nhnacademy.doorayProject.service.CommentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentRestController.class)
@AutoConfigureMockMvc
class CommentRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Test
    void getCommentList() throws Exception {
        CommentInfoListDto commentInfoListDto = new CommentInfoListDto(List.of());
        Integer taskId = 1;
        Mockito.when(commentService.getCommentList(taskId)).thenReturn(commentInfoListDto);
        mockMvc.perform(get("/projects/tasks/{taskId}/comments", taskId))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(commentInfoListDto)));
    }

    @Test
    void addComment() throws Exception {
        Integer taskId = 1;
        CommentUploadDto commentUploadDto = new CommentUploadDto();
        commentUploadDto.setContent("content");
        Mockito.doNothing().when(commentService).addComment(taskId, commentUploadDto);
        mockMvc.perform(post("/projects/tasks/{taskId}/comments", taskId)
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(commentUploadDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateComment_exist() throws Exception{
        Integer commentId = 1;
        CommentUploadDto commentUploadDto = new CommentUploadDto();
        CommentUpdateDto commentUpdateDto = new CommentUpdateDto();
        commentUpdateDto.setContent("content");
        Mockito.doNothing().when(commentService).updateComment(commentId, commentUpdateDto);
        mockMvc.perform(put("/projects/tasks/comments/{commentId}/update", commentId)
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(commentUploadDto)))
                .andExpect(status().isOk());
    }

    @Test
    void updateComment_notExist() throws Exception{
        Integer commentId = 1;
        CommentUploadDto commentUploadDto = new CommentUploadDto();
        CommentUpdateDto commentUpdateDto = new CommentUpdateDto();
        commentUpdateDto.setContent("content");
        Mockito.doThrow(CommentNotFoundException.class).when(commentService).updateComment(anyInt(), any(CommentUpdateDto.class));
        mockMvc.perform(put("/projects/tasks/comments/{commentId}/update", commentId)
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(commentUploadDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteComment_exist() throws Exception{
        Integer commentId = 1;
        Mockito.doNothing().when(commentService).deleteComment(commentId);
        mockMvc.perform(delete("/projects/tasks/comments/{commentId}/delete", commentId))
                .andExpect(status().isOk());
    }

    @Test
    void deleteComment_notExist() throws Exception{
        Integer commentId = 1;
        Mockito.doThrow(CommentNotFoundException.class).when(commentService).deleteComment(commentId);
        mockMvc.perform(delete("/projects/tasks/comments/{commentId}/delete", commentId))
                .andExpect(status().isBadRequest());
    }
}