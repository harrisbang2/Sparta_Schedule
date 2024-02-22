package com.sparta.schedules.controllertest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.schedules.config.WebSecurityConfig;
import com.sparta.schedules.controller.CommentController;
import com.sparta.schedules.dto.CommentRequestDto;
import com.sparta.schedules.entity.User;
import com.sparta.schedules.entity.UserRoleEnum;
import com.sparta.schedules.security.UserDetailsImpl;
import com.sparta.schedules.service.CommentService;
import com.sparta.schedules.service.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = {CommentController.class},
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = WebSecurityConfig.class
                )
        }
)

public class CommentControllerTest {
    public MockMvc mvc;
    public Principal mockPrincipal;
    @MockBean
    CommentService Service;
    @MockBean
    ScheduleService scheduleService;
    @Autowired
    public WebApplicationContext context;
    @Autowired
    public ObjectMapper objectMapper;
    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity(new MockSpringSecurityFilter()))
                .build();
    }
    private void mockUserSetup() {
        // Mock 테스트 유져 생성
        String username = "user";
        String password = "user";
        String email = "user@user.com";
        UserRoleEnum role = UserRoleEnum.USER;
        User testUser = new User(username, password, email, role);
        UserDetailsImpl testUserDetails = new UserDetailsImpl(testUser);
        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails, "", testUserDetails.getAuthorities());
    }


    @Test
    @DisplayName("Comment post")
    void test1() throws Exception {
        //given
        this.mockUserSetup();

        CommentRequestDto  requestDto= new CommentRequestDto();
        requestDto.setComment("comment post");
        requestDto.setSchedule_id(1L);

        String postform  = objectMapper.writeValueAsString(requestDto);
        // when - then
        this.mvc.perform(post("/api/comment")
                        .content(postform)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal)
                )
                .andExpect(status().is(201))
                .andDo(print());
    }

    @Test
    @DisplayName("comment get")
    void test2() throws Exception {
        //given
        this.mockUserSetup();

        // when - then
        this.mvc.perform(get("/api/comment/1")
                        .principal(mockPrincipal)
                )
                .andExpect(status().is(200))
                .andDo(print());
    }
    @Test
    @DisplayName("Schedule put")
    void test3() throws Exception {
//given
        this.mockUserSetup();

        String contents = "new comment";
        CommentRequestDto  requestDto= new CommentRequestDto();
        requestDto.setComment(contents);
        requestDto.setSchedule_id(1L);

        String platform  = objectMapper.writeValueAsString(requestDto);

        // when - then
        this.mvc.perform(put("/api/comment/1")
                        .content(platform)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal)
                )
                .andExpect(status().is(201))
                .andDo(print());
    }
    @Test
    @DisplayName("Schedule delete")
    void test4() throws Exception {
        //given
        this.mockUserSetup();
        // when - then
        this.mvc.perform(delete("/api/comment/1")
                        .principal(mockPrincipal)
                )
                .andExpect(status().is(201))
                .andDo(print());
    }

}
