package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.dto.comment.CommentInfoDto;
import com.nhnacademy.doorayProject.dto.comment.CommentInfoListDto;
import com.nhnacademy.doorayProject.dto.comment.CommentUpdateDto;
import com.nhnacademy.doorayProject.dto.comment.CommentUploadDto;
import com.nhnacademy.doorayProject.entity.Comment;
import com.nhnacademy.doorayProject.exeption.CommentNotFoundException;
import com.nhnacademy.doorayProject.repository.CommentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void getCommentList() {
        Integer taskId = 1;
        List<CommentInfoDto> comments = List.of();
        Mockito.when(commentRepository.getCommentInfoByTaskTaskId(taskId)).thenReturn(comments);
        Assertions.assertEquals(comments, commentService.getCommentList(taskId).getComments());
    }

    @Test
    void addComment() {
        Integer commentId = 1;
        CommentUploadDto commentUploadDto = new CommentUploadDto();
        commentUploadDto.setContent("content");
        commentUploadDto.setUserId("userId");
        commentService.addComment(commentId, commentUploadDto);
        Mockito.verify(commentRepository).save(any(Comment.class));
    }

    @Test
    void updateComment_exist(){
        Integer commentId = 1;
        CommentUpdateDto commentUpdateDto = new CommentUpdateDto();
        commentUpdateDto.setContent("content");
        Comment comment = new Comment();
        Mockito.doReturn(Optional.of(comment)).when(commentRepository).findById(commentId);
        commentService.updateComment(commentId, commentUpdateDto);
        Mockito.verify(commentRepository).save(comment);
    }

    @Test
    void updateComment_not_exist(){
        Integer commentId = 1;
        CommentUpdateDto commentUpdateDto = new CommentUpdateDto();
        commentUpdateDto.setContent("content");
        Mockito.doReturn(Optional.empty()).when(commentRepository).findById(commentId);
        Assertions.assertThrows(CommentNotFoundException.class, () -> commentService.updateComment(commentId, commentUpdateDto));
    }

    @Test
    void deleteComment_exist(){
        Integer commentId = 1;
        Comment comment = new Comment();
        Mockito.doReturn(Optional.of(comment)).when(commentRepository).findById(commentId);
        commentService.deleteComment(commentId);
        Mockito.verify(commentRepository).delete(comment);
    }

    @Test
    void deleteComment_not_exist(){
        Integer commentId = 1;
        Mockito.doReturn(Optional.empty()).when(commentRepository).findById(commentId);
        Assertions.assertThrows(CommentNotFoundException.class, () -> commentService.deleteComment(commentId));
    }
}