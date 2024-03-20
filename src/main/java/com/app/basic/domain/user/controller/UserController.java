package com.app.basic.domain.user.controller;

import com.app.basic.domain.user.dto.UserDto;
import com.app.basic.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(UserDto userDto) throws Exception {
        userService.saveUser(userDto);

        return "/login/index";
    }

}