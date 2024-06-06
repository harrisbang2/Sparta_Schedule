package com.sparta.schedules.domain.schedule.dto;

import com.sparta.schedules.domain.schedule.entity.Schedule;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResponseDto {
    private String contents;
    private LocalDate date;

    public ScheduleResponseDto(Schedule sc) {
        this.contents = sc.getContents();
        this.date = sc.getDate();
    }
}

