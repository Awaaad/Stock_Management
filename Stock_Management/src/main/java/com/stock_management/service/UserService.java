package com.stock_management.service;

import com.stock_management.entity.User;

import java.util.List;

public interface UserService {
    User findUserById (Long userId);

    List<User> findAllUsers();
}
