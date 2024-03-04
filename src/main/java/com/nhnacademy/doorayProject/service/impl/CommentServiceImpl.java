package com.nhnacademy.doorayProject.service.impl;

<<<<<<< HEAD
import com.nhnacademy.doorayProject.dto.comment.CommentInfoListDto;
import com.nhnacademy.doorayProject.dto.comment.CommentUpdateDto;
import com.nhnacademy.doorayProject.dto.comment.CommentUploadDto;
import com.nhnacademy.doorayProject.entity.Comment;
import com.nhnacademy.doorayProject.entity.Task;
import com.nhnacademy.doorayProject.exeption.CommentNotFoundException;
=======
import com.nhnacademy.doorayProject.dto.CommentDto;
import com.nhnacademy.doorayProject.dto.CommentUpdateDto;
import com.nhnacademy.doorayProject.entity.Comment;
import com.nhnacademy.doorayProject.entity.Task;
import com.nhnacademy.doorayProject.exeption.NotFoundCommentException;
import com.nhnacademy.doorayProject.exeption.NotFoundTaskException;
>>>>>>> origin/develop
import com.nhnacademy.doorayProject.repository.CommentRepository;
import com.nhnacademy.doorayProject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
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
=======
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    @Override
    public List<Comment> getComments(Integer taskId) {
        if(commentRepository.findAllByTask_TaskId(taskId).isEmpty()){
            throw new NotFoundCommentException();
        }

        return commentRepository.findAllByTask_TaskId(taskId);
    }

    @Override
    public CommentDto addComment(Integer taskId, CommentDto commentDto) {
        // todo taskId가 있는지 확인해야한다
//        Comment comment;
//        if((comment = taskRepository.findById(taskId))== null){
//            throw new NotFoundTaskException();
//        }
        Comment comment = new Comment();
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
>>>>>>> origin/develop
    }

    @Override
    public void deleteComment(Integer commentId) {
<<<<<<< HEAD
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        commentRepository.delete(comment);
=======
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if(Objects.isNull(comment)){
            throw new NotFoundCommentException();
        }
        commentRepository.delete(comment);

>>>>>>> origin/develop
    }
}
