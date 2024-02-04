package com.sparta.schedules.repository;

import com.sparta.schedules.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    List<Schedule> findByDate(LocalDate date);
}
