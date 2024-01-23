package com.sparta.schedules.DTO;

import lombok.Getter;

import java.time.LocalDate;

@Getter
    public class ScheduleRequestDto {
        private String password;
        private String contents;
        private LocalDate date;
    }




