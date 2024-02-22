package com.sparta.schedules.dto;

import com.sparta.schedules.entity.Schedule;
import lombok.Getter;

import java.time.LocalDate;

@Getter
    public class ScheduleResponseDto {
        private String contents;
        private LocalDate date;

        public ScheduleResponseDto(Schedule sc) {
            this.contents = sc.getContents();
            this.date = sc.getDate();
        }
    }

