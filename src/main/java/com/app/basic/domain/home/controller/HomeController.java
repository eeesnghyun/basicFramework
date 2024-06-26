package com.app.basic.domain.home.controller;

import com.app.basic.common.entity.MsgEntity;
import com.app.basic.common.entity.StatusEnum;
import com.app.basic.domain.home.dto.MenuDto;
import com.app.basic.domain.home.service.HomeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/getMenuInfo", method = {RequestMethod.GET})
    public @ResponseBody MsgEntity getMenuInfo(@RequestParam int menuSeq) {
        log.info(":: 메뉴 조회 ::");

        MenuDto menuInfo = homeService.getMenuInfo(menuSeq);

        return MsgEntity.builder()
            .message(StatusEnum.OK)
            .result(menuInfo).build();
    }

    @RequestMapping(value = "/saveMenuInfo", method = {RequestMethod.POST})
    public @ResponseBody MsgEntity saveMenuInfo(@RequestBody MenuDto menuDto) throws Exception {
        homeService.saveMenuInfo(menuDto);

        return MsgEntity.builder()
            .message(StatusEnum.OK)
            .result(menuDto).build();
    }
}
