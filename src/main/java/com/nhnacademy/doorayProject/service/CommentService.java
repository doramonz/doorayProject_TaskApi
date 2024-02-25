package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.CommentDto;
import com.nhnacademy.doorayProject.dto.CommentUpdateDto;
import com.nhnacademy.doorayProject.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getComments(Integer taskId);

    CommentDto addComment(Integer taskId, CommentDto commentDto);

    CommentUpdateDto updateCommemt(Integer commentId, CommentUpdateDto commentUpdateDto);

    void deleteComment(Integer commentId);
}
