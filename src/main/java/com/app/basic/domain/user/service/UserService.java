package com.app.basic.domain.user.service;

import com.app.basic.domain.user.dto.UserDto;

public interface UserService {

    public void saveUser(UserDto userDto) throws Exception;

}
