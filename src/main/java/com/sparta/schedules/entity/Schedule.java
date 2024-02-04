package com.sparta.schedules.entity;

import com.sparta.schedules.Dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "contents", nullable = false)
    private String contents;
    @Column(name = "date", nullable = false)
    private LocalDate date;
    //
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public Schedule(ScheduleRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.date = requestDto.getDate();
    }

    public void update(ScheduleRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.date = requestDto.getDate();
    }
}