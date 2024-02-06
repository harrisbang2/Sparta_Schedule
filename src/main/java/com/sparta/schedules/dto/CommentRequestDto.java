package com.sparta.schedules.dto;

import com.sparta.schedules.entity.Schedule;
import com.sparta.schedules.entity.User;
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
