package com.stock_management.service;

import com.stock_management.dto.UserDto;

import java.util.List;

public interface UserService {
    // Get
    UserDto findUserById (Long userId);

    UserDto findUserByUsernameAndPassword (String username, String password);

    List<UserDto> findAllUsers();

    // Post
    void saveUser (UserDto userDto);
}
