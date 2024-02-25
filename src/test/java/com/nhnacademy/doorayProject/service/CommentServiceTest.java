package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.CommentDto;
import com.nhnacademy.doorayProject.dto.CommentUpdateDto;
import com.nhnacademy.doorayProject.entity.Comment;
import com.nhnacademy.doorayProject.repository.CommentRepository;
import com.nhnacademy.doorayProject.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentServiceTest {

    @Autowired
    private CommentServiceImpl commentService;

    @MockBean
    private CommentRepository commentRepository;

    @Test
    void testGetComments() {
        List<Comment> testCommentList = Arrays.asList(new Comment(), new Comment());
        given(commentRepository.findAllByTask_TaskId(any(Integer.class))).willReturn(testCommentList);

        Assertions.assertNotNull(commentService.getComments(1));

    }

    @Test
    void testAddComments() {

        Comment comment = new Comment();
        comment.setUserId("1234");
        comment.setCommentContent("qwer");

//        given(taskRepository.findBytaskId(any(Integer.class))).willReturn(comment);

        given(commentRepository.save(any(Comment.class))).willReturn(comment);

        // When
        CommentDto commentDto = commentService.addComment(1, new CommentDto("1234","qwer"));
        // Then
        assertThat(commentDto.getUserId()).isEqualTo("1234");
        assertThat(commentDto.getCommentContent()).isEqualTo("qwer");
    }

    @Test
    void testUpdateComment() {
        String commentId = "1234";
        CommentUpdateDto commentUpdateDto = new CommentUpdateDto("qwer");

        given(commentRepository.findById(any(Integer.class))).willReturn(Optional.of(new Comment()));

        Comment comment = new Comment();
        comment.setCommentContent("qwer");
        given(commentRepository.save(any(Comment.class))).willReturn(comment);

        CommentUpdateDto newCommentUpdateDto = commentService.updateCommemt(1,new CommentUpdateDto("qwer"));

        assertThat(newCommentUpdateDto.getCommentContent()).isEqualTo("qwer");
    }

    @Test
    void testDeleteComment() {
        given(commentRepository.findById(any(Integer.class))).willReturn(Optional.of(new Comment()));
        given(commentRepository.save(any(Comment.class))).willReturn(new Comment());

        commentService.deleteComment(1);

        verify(commentRepository).delete(any(Comment.class));
    }
}
