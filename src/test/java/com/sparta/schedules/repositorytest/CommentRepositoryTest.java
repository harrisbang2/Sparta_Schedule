package com.sparta.schedules.repositorytest;

import com.sparta.schedules.entity.Comment;
import com.sparta.schedules.entity.Schedule;
import com.sparta.schedules.entity.User;
import com.sparta.schedules.entity.UserRoleEnum;
import com.sparta.schedules.repository.CommentRepository;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE,
        connection = EmbeddedDatabaseConnection.H2)
public class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("댓글 테스트")
    void test1(){
        Comment comment = new Comment();
        User user = new User();
        Schedule schedule = new Schedule();

        comment.setComment("댓글 생성");
        comment.setUser(user);
        comment.setSchedule(schedule);

        Comment save = commentRepository.save(comment);
        assertEquals("댓글 생성",save.getComment());

        // 검색
        Comment save2 = commentRepository.findById(1L).orElseThrow(()-> new NoSuchElementException("해당댓글 없음"));
        assertEquals("댓글 생성",save2.getComment());

        //수정
        save2.update("댓글이 수정 된");
        assertEquals("댓글이 수정 된",save2.getComment());

        // 삭제
        commentRepository.delete(save2);
        try{
            commentRepository.findById(1L).orElseThrow(()-> new NoSuchElementException("해당댓글 없음"));
        }catch (NoSuchElementException e){
            assertTrue(true);
        }

    }

}
