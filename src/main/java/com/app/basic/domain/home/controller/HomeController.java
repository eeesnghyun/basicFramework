package com.app.basic.domain.home.controller;

import com.app.basic.common.entity.MsgEntity;
import com.app.basic.common.entity.StatusEnum;
import com.app.basic.domain.home.dto.MenuDto;
import com.app.basic.domain.home.service.HomeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @RequestMapping(value = "/getMenuList", method = {RequestMethod.POST})
    public @ResponseBody MsgEntity getMenuList() {
        log.info(":: 메뉴 조회 ::");

        List<MenuDto> menuList = homeService.getMenuList();

        return MsgEntity.builder()
                    .message(StatusEnum.OK)
                    .result(menuList).build();
    }
}
