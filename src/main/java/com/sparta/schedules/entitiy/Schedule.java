package com.sparta.schedules.entitiy;

import com.sparta.schedules.DTO.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private Long id;
    private String password;
    private String contents;

    public Schedule(ScheduleRequestDto requestDto) {
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }

    public void update(ScheduleRequestDto requestDto) {
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }
}
