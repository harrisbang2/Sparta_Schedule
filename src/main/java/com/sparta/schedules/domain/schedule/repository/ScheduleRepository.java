package com.sparta.schedules.domain.schedule.repository;

import com.sparta.schedules.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Long>,ScheduleRepositoryCustom {

}
