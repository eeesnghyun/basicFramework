package com.app.basic.domain.home.service;

import com.app.basic.domain.home.dto.MenuDto;
import java.util.List;

public interface HomeService {

    public List<MenuDto> getMenuList();

    public MenuDto getMenuInfo(int menuSeq);

}
