package com.stock_management.service.implementation;

import com.stock_management.dto.UserDto;
import com.stock_management.entity.UserProfile;
import com.stock_management.mapper.RoleMapper;
import com.stock_management.mapper.UserMapper;
import com.stock_management.repository.RoleRepository;
import com.stock_management.repository.UserRepository;
import com.stock_management.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final BCryptPasswordEncoder passwordEncoder ;

    public UserServiceImplementation(UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository, RoleMapper roleMapper, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.passwordEncoder = passwordEncoder;
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
    public UserDto findUserByUsername(String username) {
        Optional<UserProfile> user = Optional.ofNullable(userRepository.findByUsername(username));
        var oneUser = user.orElse(null);
        oneUser.setPassword("********");
        return userMapper.mapUserEntityToDto(oneUser);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<UserProfile> userProfiles = userRepository.findAll();
        userProfiles.forEach(userProfile -> userProfile.setPassword("********"));
        return userProfiles.stream().map(userMapper::mapUserEntityToDto).collect(Collectors.toList());
    }

    @Override
    public void saveUser(UserDto userDto) {
        String password = userDto.getPassword();
        String encrypPassword = passwordEncoder.encode(password);
        userDto.setPassword(encrypPassword);
        userDto.setRoles(userDto.getRoles().stream().map(roleDto ->
                roleRepository.findRoleByRole(roleDto.getRole())).map(roleMapper::mapRoleEntityToDto)
                .collect(Collectors.toSet()));
        var saveUserInformation = userMapper.mapUserDtoToEntity(userDto);
        userRepository.save(saveUserInformation);
    }

    @Override
    public void saveUser(UserProfile userProfile) {
        String password = userProfile.getPassword();
        String encrypPassword = passwordEncoder.encode(password);
        userProfile.setPassword(encrypPassword);
        userProfile.setRoles(userProfile.getRoles().stream().map(role ->
                roleRepository.findRoleByRole(role.getRole()))
                .collect(Collectors.toSet()));
        userRepository.save(userProfile);
    }

    @Override
    public void saveUsers(List<UserProfile> userProfiles) {
        userProfiles.stream().map(this::mapUser).collect(Collectors.toList());
        userRepository.saveAll(userProfiles);
    }

    private UserProfile mapUser(UserProfile userProfile) {
        String password = userProfile.getPassword();
        String encrypPassword = passwordEncoder.encode(password);
        userProfile.setPassword(encrypPassword);
        userProfile.setRoles(userProfile.getRoles().stream().map(role ->
                roleRepository.findRoleByRole(role.getRole()))
                .collect(Collectors.toSet()));
        return userProfile;
    }
}
