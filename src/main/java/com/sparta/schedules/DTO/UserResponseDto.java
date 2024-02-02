package com.sparta.schedules.DTO;

import com.sparta.schedules.entitiy.Schedule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
