package com.sparta.schedules.repositorytest;

import com.sparta.schedules.entity.Schedule;
import com.sparta.schedules.entity.User;
import com.sparta.schedules.entity.UserRoleEnum;
import com.sparta.schedules.repository.ScheduleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE,
        connection = EmbeddedDatabaseConnection.H2)
public class ScheduleRepoTest {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Test
    @DisplayName("스케줄 생성")
    void create(){
        Schedule schedule = new Schedule();
        schedule.setContents("스케줄 생성");
        schedule.setDate(LocalDate.now());
        User user = new User("user10","user10","user10@10.com", UserRoleEnum.USER);
        user.setId(1L);
        schedule.setUser(user);

        Schedule save = scheduleRepository.save(schedule);
        assertEquals("스케줄 생성",save.getContents());
    }

    @Test
    @DisplayName("스케줄 찾기")
    void find(){
        User user = new User();
        user.setId(1L);
        Schedule save = scheduleRepository.findByIdAndUser(1L,user);

        assertEquals("스케줄 생성",save.getContents());
    }

    @Test
    @DisplayName("스케줄 변경")
    void put(){
        User user = new User("user10","user10","user10@10.com", UserRoleEnum.USER);
        user.setId(1L);
        Schedule save = scheduleRepository.findByIdAndUser(1L,user);
        save.update("스케줄을 변경 합니다");
        assertEquals("스케줄을 변경 합니다",save.getContents());
    }

    @Test
    @DisplayName("스케줄 제거")
    void delete(){
        User user = new User("user10","user10","user10@10.com", UserRoleEnum.USER);
        user.setId(1L);
        Schedule save = scheduleRepository.findByIdAndUser(1L,user);
        scheduleRepository.delete(save);
        try{
            scheduleRepository.findByIdAndUser(1L,user);
        }catch (Exception e){
            assertTrue(true);
        }
    }

    //
    @Test
    @DisplayName("스케줄 repo 한번에 테스트")
    void overAll(){
        Schedule schedule = new Schedule();
        schedule.setContents("스케줄 생성");
        schedule.setDate(LocalDate.now());
        User user = new User("user10","user10","user10@10.com", UserRoleEnum.USER);
        user.setId(1L);
        schedule.setUser(user);

        Schedule save = scheduleRepository.save(schedule);
        assertEquals("스케줄 생성",save.getContents());

        //검색
        Schedule save2 = scheduleRepository.findById(1L).orElseThrow();
        assertEquals("스케줄 생성",save2.getContents());

        //수정
        save2.update("스케줄을 변경 합니다");
        assertEquals("스케줄을 변경 합니다",save2.getContents());

        //삭제
        scheduleRepository.delete(save2);
        //삭제 되었기에 검색이 불가. NoSuchElementException
        try{
            scheduleRepository.findByIdAndUser(1L,user);
        }catch (NoSuchElementException e){
            assertTrue(true);
        }
    }
}
