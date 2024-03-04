package com.nhnacademy.doorayProject.service;

<<<<<<< HEAD
import com.nhnacademy.doorayProject.dto.comment.CommentInfoListDto;
import com.nhnacademy.doorayProject.dto.comment.CommentUpdateDto;
import com.nhnacademy.doorayProject.dto.comment.CommentUploadDto;

public interface CommentService {
    CommentInfoListDto getCommentList(Integer taskId);

    void addComment(Integer taskId, CommentUploadDto commentUploadDto);

    void updateComment(Integer commentId, CommentUpdateDto commentUpdateDto);
=======
import com.nhnacademy.doorayProject.dto.CommentDto;
import com.nhnacademy.doorayProject.dto.CommentUpdateDto;
import com.nhnacademy.doorayProject.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getComments(Integer taskId);

    CommentDto addComment(Integer taskId, CommentDto commentDto);

    CommentUpdateDto updateCommemt(Integer commentId, CommentUpdateDto commentUpdateDto);
>>>>>>> origin/develop

    void deleteComment(Integer commentId);
}
