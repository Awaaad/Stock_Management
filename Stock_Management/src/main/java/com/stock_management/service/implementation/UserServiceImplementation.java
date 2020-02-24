package com.stock_management.service.implementation;

import com.stock_management.entity.User;
import com.stock_management.repository.UserRepository;
import com.stock_management.service.UserService;
import lombok.var;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImplementation implements UserService {
    public final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        var oneUser = user.orElse(null);
        return oneUser;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> user = userRepository.findAll();
        return user;
    }
}
