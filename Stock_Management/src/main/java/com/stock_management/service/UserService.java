package com.stock_management.service;

import com.stock_management.dto.UserDto;
import com.stock_management.entity.User;

import java.util.List;

public interface UserService {
    // Get
    UserDto findUserById (Long userId);

    List<UserDto> findAllUsers();

    // Post
    void saveUser (UserDto userDto);
}
