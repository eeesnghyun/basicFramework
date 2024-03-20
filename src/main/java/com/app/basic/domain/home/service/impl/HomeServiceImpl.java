package com.app.basic.domain.home.service.impl;


import com.app.basic.domain.home.dao.HomeDao;
import com.app.basic.domain.home.dto.MenuDto;
import com.app.basic.domain.home.service.HomeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final HomeDao homeDao;

    @Cacheable(value = "homeBasic", cacheManager = "homeCacheManager")
    @Override
    public List<MenuDto> getMenuList() {
        return homeDao.getMenuList();
    }
}
