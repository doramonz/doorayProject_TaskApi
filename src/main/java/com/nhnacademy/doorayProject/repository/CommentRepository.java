package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
