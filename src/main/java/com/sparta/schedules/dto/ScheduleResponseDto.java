package com.sparta.schedules.dto;

import com.sparta.schedules.entity.Schedule;
import com.sparta.schedules.repository.projectionInterface.ScheduleCotentsDateOnly;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
    public class ScheduleResponseDto {
        private String contents;
        private LocalDate date;

    public String getDate() {
        return date.toString();
    }
        public ScheduleResponseDto(Schedule sc) {
            this.contents = sc.getContents();
            this.date = sc.getDate();
        }
    public ScheduleResponseDto(ScheduleCotentsDateOnly sc) {
        this.contents = sc.getContents();
        this.date = sc.getDate();
    }
    }

