package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.dto.CommentDto;
import com.nhnacademy.doorayProject.dto.CommentUpdateDto;
import com.nhnacademy.doorayProject.entity.Comment;
import com.nhnacademy.doorayProject.entity.Task;
import com.nhnacademy.doorayProject.exeption.NotFoundCommentException;
import com.nhnacademy.doorayProject.exeption.NotFoundTaskException;
import com.nhnacademy.doorayProject.repository.CommentRepository;
import com.nhnacademy.doorayProject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    @Override
    public List<Comment> getComments(Integer taskId) {
        Task task;
        if((task = commentRepository.findTaskByTaskId(taskId))== null){
            throw new NotFoundTaskException();
        }
        // taskId가 같은 거 다 들고오기 findById로 알아보기
        return commentRepository.findAllByTask(task);
    }

    @Override
    public CommentDto addComment(Integer taskId, CommentDto commentDto) {
        // 차피 추가로 comment 다는 거랏서 taskId에 따라 comment를 달기만 하면 된다
        Task task;
        if((task = commentRepository.findTaskByTaskId(taskId))== null){
            throw new NotFoundTaskException();
        }
        Comment comment = new Comment();
        comment.setTask(task);
        comment.setCommentContent(commentDto.getCommentContent());
        comment.setUserId(commentDto.getUserId());
        Comment newComment = commentRepository.save(comment);
        return new CommentDto(newComment.getUserId(), newComment.getCommentContent());
    }

    @Override
    public CommentUpdateDto updateCommemt(Integer commentId, CommentUpdateDto commentUpdateDto) {
        Optional<Comment> originalComment = commentRepository.findById(commentId);
        if(originalComment.isEmpty()){
            throw new NotFoundCommentException();
        }
        Comment comment = new Comment();

        comment.setCommentId(commentId);
        comment.setUserId(originalComment.get().getUserId());
        comment.setTask(originalComment.get().getTask());
        comment.setCommentContent(commentUpdateDto.getCommentContent());

        Comment updateComment = commentRepository.save(comment);

        CommentUpdateDto newUpdateDto = new CommentUpdateDto(updateComment.getCommentContent());
        return newUpdateDto;
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if(Objects.isNull(comment)){
            throw new NotFoundCommentException();
        }
        commentRepository.delete(comment);

    }
}
