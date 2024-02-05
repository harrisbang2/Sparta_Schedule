package com.sparta.schedules.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
    public class ScheduleRequestDto {
    private LocalDate date;
    private String contents;
    }




