package com.app.basic.domain.home.service.impl;


import com.app.basic.domain.home.dao.HomeDao;
import com.app.basic.domain.home.dto.MenuDto;
import com.app.basic.domain.home.service.HomeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CacheConfig(cacheNames = "menu")
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final HomeDao homeDao;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(key = "'all'", cacheManager = "cacheManager")
    public List<MenuDto> getMenuList() {
        return homeDao.getMenuList();
    }

    @Override
    @Cacheable(value = "message", key = "#menuSeq", cacheManager = "cacheManager")
    public MenuDto getMenuInfo(int menuSeq) {
        return homeDao.getMenuInfo(menuSeq);
    }

    @Override
    public void saveMenuInfo(MenuDto menuDto) throws Exception {
        homeDao.saveMenuInfo(menuDto);
    }

}
