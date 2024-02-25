package com.nhnacademy.doorayProject.controller;

import com.nhnacademy.doorayProject.dto.comment.CommentInfoListDto;
import com.nhnacademy.doorayProject.dto.comment.CommentUpdateDto;
import com.nhnacademy.doorayProject.dto.comment.CommentUploadDto;
import com.nhnacademy.doorayProject.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/projects/tasks")
@RequiredArgsConstructor
@RestController
public class CommentRestController {
    private final CommentService commentService;

    @GetMapping("/{taskId}/comments")
    public ResponseEntity<CommentInfoListDto> getCommentList(@PathVariable("taskId") Integer taskId) {
        ResponseEntity<CommentInfoListDto> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(commentService.getCommentList(taskId));
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @PostMapping("/{taskId}/comments")
    public ResponseEntity<Void> addComment(@PathVariable("taskId") Integer taskId, @RequestBody CommentUploadDto commentUploadDto) {
        ResponseEntity<Void> responseEntity;
        try {
            commentService.addComment(taskId, commentUploadDto);
            responseEntity = ResponseEntity.created(null).build();
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @PutMapping("/comments/{commentId}/update")
    public ResponseEntity<Void> updateComment(@PathVariable("commentId") Integer commentId, @RequestBody CommentUpdateDto commentUpdateDto) {
        ResponseEntity<Void> responseEntity;
        try {
            commentService.updateComment(commentId, commentUpdateDto);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @DeleteMapping("/comments/{commentId}/delete")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") Integer commentId) {
        ResponseEntity<Void> responseEntity;
        try {
            commentService.deleteComment(commentId);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }
}
