package com.stock_management.service.implementation;

import com.stock_management.dto.UserDto;
import com.stock_management.entity.UserProfile;
import com.stock_management.mapper.UserMapper;
import com.stock_management.repository.UserRepository;
import com.stock_management.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto findUserById(Long userId) {
        Optional<UserProfile> user = userRepository.findById(userId);
        var oneUser = user.orElse(null);
        return userMapper.mapUserEntityToDto(oneUser);
    }

    @Override
    public UserDto findUserByUsernameAndPassword(String username, String password) {
        Optional<UserProfile> user = Optional.ofNullable(userRepository.findByUsernameAndPassword(username, password));
        var oneUser = user.orElse(null);
        oneUser.setPassword("********");
        return userMapper.mapUserEntityToDto(oneUser);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<UserProfile> userProfiles = userRepository.findAll();
        return userProfiles.stream().map(userMapper::mapUserEntityToDto).collect(Collectors.toList());
    }

    @Override
    public void saveUser(UserDto userDto) {
//        String password = userDto.getPassword();
//        String encrypPassword = passwordEncoder.encode(password);
//        userDto.setPassword(encrypPassword);
        var saveUserInformation = userMapper.mapUserDtoToEntity(userDto);
        userRepository.save(saveUserInformation);
    }
}
