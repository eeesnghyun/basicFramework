package com.app.basic.domain.home.dao;

import com.app.basic.domain.home.dto.MenuDto;
import java.util.List;

public interface HomeDao {

    public List<MenuDto> getMenuList();

    public MenuDto getMenuInfo(int menuSeq);

    public void saveMenuInfo(MenuDto menuDto) throws Exception;

}
