package com.sparta.schedules.dto;

import com.sparta.schedules.entity.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentResponseDto {
    private String comment;

    public CommentResponseDto(Comment savecomment) {
        this.comment = savecomment.getComment();
    }
}
