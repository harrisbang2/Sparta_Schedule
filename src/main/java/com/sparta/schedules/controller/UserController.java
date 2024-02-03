package com.sparta.schedules.controller;

import com.sparta.schedules.DTO.ScheduleRequestDto;
import com.sparta.schedules.DTO.ScheduleResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class UserController {
    @GetMapping("/user/search-page")
    public String search(Model model) {
        model.addAttribute("username", "username");
        return "search";
    }

}
