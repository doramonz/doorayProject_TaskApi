package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.repository.CommentRepository;
import com.nhnacademy.doorayProject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
}
