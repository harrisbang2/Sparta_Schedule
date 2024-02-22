package com.sparta.schedules.entitydtotest;

import com.sparta.schedules.dto.CommentRequestDto;
import com.sparta.schedules.dto.ScheduleRequestDto;
import com.sparta.schedules.dto.SignupRequestDto;
import com.sparta.schedules.entity.Comment;
import com.sparta.schedules.entity.Schedule;
import com.sparta.schedules.entity.User;
import com.sparta.schedules.entity.UserRoleEnum;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class EntityTest extends TestTimeKeeper{
    // given
    String username = "user";
    String password = "password";
    String email = "harrisbang98@gmail.com";
    UserRoleEnum role = UserRoleEnum.USER;
    User user;
    Schedule sc;


    // 유저
    @Test
    @DisplayName("일반 유저 테스트")
    void userTest(){
        //given
        SignupRequestDto requestDto = new SignupRequestDto();
        boolean admin = role.equals(UserRoleEnum.ADMIN);
        requestDto.setAdmin(admin);
        requestDto.setEmail(email);
        requestDto.setPassword(password);
        requestDto.setUsername(username);

        //when
        user = new User(requestDto);

        //then
        assertEquals("user",user.getUsername());
        assertEquals("password",user.getPassword());
        assertEquals("harrisbang98@gmail.com",user.getEmail());
        assertEquals(UserRoleEnum.USER,user.getRole());
    }

    @Test
    @DisplayName("Admin 유저  테스트")
    void AdminTest(){
        //given
        SignupRequestDto requestDto = new SignupRequestDto();
        requestDto.setEmail(email);
        requestDto.setPassword(password);
        requestDto.setUsername(username);
        requestDto.setAdminToken("AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC");

        //when
        user = new User(requestDto);

        //then
        assertEquals("user",user.getUsername());
        assertEquals("password",user.getPassword());
        assertEquals("harrisbang98@gmail.com",user.getEmail());
        assertEquals(UserRoleEnum.ADMIN,user.getRole());
}


    // 스케줄
    @Test
    @DisplayName("스케줄  테스트")
    void ScheduleTest(){
        //given
        ScheduleRequestDto requestDto = new ScheduleRequestDto();
        //when
        sc = new Schedule(requestDto,user);
        //then
        assertNull(sc.getContents());
        //when
        sc.update("업데이트");
        //then
        assertEquals("업데이트",sc.getContents());
    }

    // 댓글 테스트
    @Test
    @DisplayName("댓글 테스트")
    void commentTest(){
        //given
        CommentRequestDto requestDto = new CommentRequestDto();
        //when
        Comment cm = new Comment(requestDto,sc,user);
        //then
        assertNull(cm.getComment());

        //when

        cm.update("커멘트 업뎃 입니다");
        //then
        assertEquals("커멘트 업뎃 입니다",cm.getComment());
    }
    //

}
