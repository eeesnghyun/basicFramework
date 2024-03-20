package com.app.basic.domain.user.service.impl;

import com.app.basic.domain.user.dao.UserDao;
import com.app.basic.domain.user.dto.UserDto;
import com.app.basic.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) throws Exception {
        userDto.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
        userDao.saveUser(userDto);
    }
}
