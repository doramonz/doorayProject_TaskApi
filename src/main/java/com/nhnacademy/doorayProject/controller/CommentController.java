package com.nhnacademy.doorayProject.controller;

import com.nhnacademy.doorayProject.dto.CommentDto;
import com.nhnacademy.doorayProject.dto.CommentUpdateDto;
import com.nhnacademy.doorayProject.entity.Comment;
import com.nhnacademy.doorayProject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects/tasks")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{taskId}/comments")
    public List<Comment> getComments(@PathVariable("taskId")Integer taskId){
        return commentService.getComments(taskId);
    }

    @PostMapping("/{taskId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createComment(@PathVariable("taskId")Integer taskId, @RequestBody CommentDto commentDto){
        return commentService.addComment(taskId, commentDto);
    }

    @PutMapping("/comments/{commentId}/update")
    public CommentUpdateDto updateComment(@PathVariable("commentId")Integer commentId, @RequestBody CommentUpdateDto commentUpdateDto) {
        return commentService.updateCommemt(commentId, commentUpdateDto);
    }

    @DeleteMapping("/comments/{commentId}/delete")
    public void deleteComment(@PathVariable("commentId")Integer commentId) {
        commentService.deleteComment(commentId);
    }
}
