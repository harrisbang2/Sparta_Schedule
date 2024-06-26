package com.sparta.schedules.domain.schedule.repository;

import com.sparta.schedules.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long>,ScheduleRepositoryCustom {

}
