package com.sparta.schedules.domain.user.dto;

import com.sparta.schedules.domain.schedule.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    private String username;
    private String password;
    private List<Schedule> scheduleList = new ArrayList<>();
}
