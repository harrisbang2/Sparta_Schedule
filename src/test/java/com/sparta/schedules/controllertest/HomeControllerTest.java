package com.sparta.schedules.controllertest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.schedules.config.WebSecurityConfig;
import com.sparta.schedules.controller.UserController;
import com.sparta.schedules.entity.User;
import com.sparta.schedules.entity.UserRoleEnum;
import com.sparta.schedules.security.UserDetailsImpl;
import com.sparta.schedules.service.UserService;
import org.apache.catalina.security.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.MockMvcConfigurer;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.logging.Filter;

import static org.mockito.Mockito.when;
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
    public Principal mockPrincipal;
    @MockBean
    UserService userService;
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
//
    @Test
    @DisplayName("index Page/html 가져오기")
    void test1() throws Exception {
        // when - then
        this.mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(print());
    }
    @Test
    @DisplayName("comment Page/html 가져오기")
    void test2() throws Exception {
        // when - then
        this.mvc.perform(get("/api/user/comment-page"))
                .andExpect(status().isOk())
                .andExpect(view().name("comment"))
                .andDo(print());
    }
    @Test
    @DisplayName("search Page/html 가져오기")
    void test3() throws Exception {
        // when - then
        this.mvc.perform(get("/api/user/search-page"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"))
                .andDo(print());
    }


}
