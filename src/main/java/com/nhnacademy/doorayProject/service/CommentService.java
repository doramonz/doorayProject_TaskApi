package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.comment.CommentInfoListDto;
import com.nhnacademy.doorayProject.dto.comment.CommentUpdateDto;
import com.nhnacademy.doorayProject.dto.comment.CommentUploadDto;

public interface CommentService {
    CommentInfoListDto getCommentList(Integer taskId);

    void addComment(Integer taskId, CommentUploadDto commentUploadDto);

    void updateComment(Integer commentId, CommentUpdateDto commentUpdateDto);

    void deleteComment(Integer commentId);
}
