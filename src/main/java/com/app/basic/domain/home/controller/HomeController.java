package com.app.basic.domain.home.controller;

import com.app.basic.domain.home.dto.MenuDto;
import com.app.basic.domain.home.service.HomeService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

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

    @RequestMapping(value = "/getMenuList", method = {RequestMethod.POST})
    public @ResponseBody Map<String, Object> getMenuList() {
        Map<String, Object> resultMap = new HashMap<>();

        List<MenuDto> menuList = homeService.getMenuList();

        resultMap.put("result", menuList);

        return resultMap;
    }


}
