package com.sparta.schedules.controllertest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.schedules.config.WebSecurityConfig;
import com.sparta.schedules.controller.UserController;
import com.sparta.schedules.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = {UserController.class},
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = WebSecurityConfig.class
                )
        }
)

public class HomeControllerTest {
    public MockMvc mvc;
    public Principal mockPricipal;
    @MockBean
    UserService userService;
    @Autowired
    public WebApplicationContext context;
    @Autowired
    public ObjectMapper objectMapper;
//    @BeforeEach
//    public void setup() {
//        mvc = MockMvcBuilders.webAppContextSetup(context)
//                .apply(springSecurity(new MockSpringSecurityFilter()))
//                .build();
//    }
    @Test
    @DisplayName("로그인 Page")
    void test1() throws Exception {
        // when - then
        this.mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("index"))
                .andDo(print());
    }



}
