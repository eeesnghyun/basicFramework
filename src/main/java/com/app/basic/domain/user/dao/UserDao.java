package com.app.basic.domain.user.dao;

import com.app.basic.domain.user.dto.UserDto;

public interface UserDao {

    public UserDto loadUserAuth(String userName);

    public void saveUser(UserDto userDto) throws Exception;



}
