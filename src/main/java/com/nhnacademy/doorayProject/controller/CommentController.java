package com.nhnacademy.doorayProject.controller;

import com.nhnacademy.doorayProject.dto.CommentDto;
import com.nhnacademy.doorayProject.dto.CommentUpdateDto;
import com.nhnacademy.doorayProject.entity.Comment;
import com.nhnacademy.doorayProject.service.CommentService;
import lombok.RequiredArgsConstructor;
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
    public List<Comment> getComments(@RequestParam("taskId")Integer taskId){
        return commentService.getComments(taskId);
    }

    @PostMapping("/{taskId}/comments")
    public CommentDto createComment(@RequestParam("taskId")Integer taskId, @ModelAttribute CommentDto commentDto){
        return commentService.addComment(taskId, commentDto);
    }

    @PutMapping("/comments/{commentId}/update")
    public CommentUpdateDto updateComment(@RequestParam("commentId")Integer commentId, @ModelAttribute CommentUpdateDto commentUpdateDto) {
        return commentService.updateCommemt(commentId, commentUpdateDto);
    }

    @DeleteMapping("/comments/{commentId}/delete")
    public void deleteComment(@RequestParam("commentId")Integer commentId) {
        commentService.deleteComment(commentId);
    }
}
