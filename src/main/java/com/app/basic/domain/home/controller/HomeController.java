package com.app.basic.domain.home.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login/index";
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join() {
        return "join/index";
    }

}
