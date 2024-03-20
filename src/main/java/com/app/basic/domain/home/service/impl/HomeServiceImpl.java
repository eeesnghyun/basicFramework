package com.app.basic.domain.home.service.impl;


import com.app.basic.domain.home.dao.HomeDao;
import com.app.basic.domain.home.dto.MenuDto;
import com.app.basic.domain.home.service.HomeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "menu")
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final HomeDao homeDao;

    @Cacheable(key = "'all'", cacheManager = "cacheManager")
    @Override
    public List<MenuDto> getMenuList() {
        return homeDao.getMenuList();
    }

}
