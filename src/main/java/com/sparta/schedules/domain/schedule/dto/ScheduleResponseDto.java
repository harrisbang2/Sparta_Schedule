package com.sparta.schedules.domain.schedule.dto;

import com.sparta.schedules.domain.schedule.entity.Schedule;
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

