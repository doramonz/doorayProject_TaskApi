package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.dto.comment.CommentInfoListDto;
import com.nhnacademy.doorayProject.dto.comment.CommentUpdateDto;
import com.nhnacademy.doorayProject.dto.comment.CommentUploadDto;
import com.nhnacademy.doorayProject.entity.Comment;
import com.nhnacademy.doorayProject.entity.Task;
import com.nhnacademy.doorayProject.exeption.CommentNotFoundException;
import com.nhnacademy.doorayProject.repository.CommentRepository;
import com.nhnacademy.doorayProject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public CommentInfoListDto getCommentList(Integer taskId) {
        return new CommentInfoListDto(commentRepository.getCommentInfoByTaskTaskId(taskId));
    }

    @Override
    public void addComment(Integer taskId, CommentUploadDto commentUploadDto) {
        Task task = new Task();
        task.setTaskId(taskId);
        Comment comment = new Comment(null, commentUploadDto.getUserId(),task , commentUploadDto.getContent());
        commentRepository.save(comment);
    }

    @Override
    public void updateComment(Integer commentId, CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        comment.setCommentContent(commentUpdateDto.getContent());
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        commentRepository.delete(comment);
    }
}
