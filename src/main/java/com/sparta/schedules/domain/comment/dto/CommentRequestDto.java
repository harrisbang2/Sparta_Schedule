package com.sparta.schedules.domain.comment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CommentRequestDto {
    private String comment;
    private long schedule_id;
}
