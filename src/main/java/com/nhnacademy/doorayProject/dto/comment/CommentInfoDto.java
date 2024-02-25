package com.nhnacademy.doorayProject.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CommentInfoDto {
    private Integer commentId;
    private String userId;
    private Integer taskId;
    private String commentContent;
}
