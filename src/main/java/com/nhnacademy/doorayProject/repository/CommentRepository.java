package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.entity.Comment;
import com.nhnacademy.doorayProject.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByTask_TaskId(Integer taskId);
    Comment findCommentByTask_TaskId(Integer taskId);
}
