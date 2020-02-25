package com.stock_management.service.implementation;

import com.stock_management.dto.UserDto;
import com.stock_management.entity.User;
import com.stock_management.mapper.UserMapper;
import com.stock_management.repository.UserRepository;
import com.stock_management.service.UserService;
import lombok.var;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {
    public final UserRepository userRepository;
    public final UserMapper userMapper;

    public UserServiceImplementation(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        var oneUser = user.orElse(null);
        return oneUser;
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::mapUserEntityToDto).collect(Collectors.toList());
    }
}
