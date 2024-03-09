package com.sparta.schedules.controller;

import com.sparta.schedules.dto.LoginRequestDto;
import com.sparta.schedules.dto.SignupRequestDto;
import com.sparta.schedules.dto.UserRequestDto;
import com.sparta.schedules.security.UserDetailsImpl;
import com.sparta.schedules.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/user/signup")
    public String signup(SignupRequestDto requestDto) {
        userService.signup(requestDto);
        return "redirect:/api/user/login-page";
    }
    // 로그임
    @PostMapping("/user/login")
    public String login(LoginRequestDto requestDto, HttpServletResponse res){
        try {
            userService.login(requestDto,res);
        } catch (Exception e) {
            return "/api/user/login-page";
        }
        return "redirect:/";
    }
    // 로그아웃
    @GetMapping("/logout")
    public String fetchSignoutSite(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        userService.userLogout(response);
        return "redirect:/";
    }

    // 비번 변경
    @PatchMapping("/updatepassword")
    public ResponseEntity<Boolean> upDatePassword(
        UserRequestDto userRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.update(userRequestDto,userDetails.getUser()))
                ;
    }
}