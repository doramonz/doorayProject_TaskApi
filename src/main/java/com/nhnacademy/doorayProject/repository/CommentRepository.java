package com.nhnacademy.doorayProject.repository;

<<<<<<< HEAD
import com.nhnacademy.doorayProject.dto.comment.CommentInfoDto;
import com.nhnacademy.doorayProject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
=======
import com.nhnacademy.doorayProject.entity.Comment;
import com.nhnacademy.doorayProject.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
>>>>>>> origin/develop

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
<<<<<<< HEAD

    @Query("select new com.nhnacademy.doorayProject.dto.comment.CommentInfoDto(c.commentId, c.user, c.task.taskId, c.commentContent) from Comment c where c.task.taskId = :taskId")
    List<CommentInfoDto> getCommentInfoByTaskTaskId(Integer taskId);
=======
    List<Comment> findAllByTask_TaskId(Integer taskId);
>>>>>>> origin/develop
}
