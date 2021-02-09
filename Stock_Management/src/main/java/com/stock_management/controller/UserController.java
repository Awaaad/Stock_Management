package com.stock_management.controller;

import com.stock_management.dto.security.AuthenticatedUserDto;
import com.stock_management.dto.security.LoginParamDto;
import com.stock_management.dto.security.UserDto;
import com.stock_management.security.JwtUtil;
import com.stock_management.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager){
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("all")
    public  ResponseEntity<List<UserDto>>getAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("userId/{userId}")
    public ResponseEntity<UserDto>getUserById(@PathVariable Long userId){
        return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.OK);
    }

    @GetMapping("login")
    public ResponseEntity<UserDto>getUserByUsername(@RequestParam String username, @RequestParam String password){
        return new ResponseEntity<>(userService.findUserByUsernameAndPassword(username, password), HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("save-user")
    public ResponseEntity<String> saveUser(@RequestBody UserDto userDto){
        userService.saveUser(userDto);
        return new ResponseEntity<>("User saved successfully!", HttpStatus.OK);
    }

    @PostMapping("authenticate")
    public AuthenticatedUserDto generateToken(@RequestBody LoginParamDto loginParamDto) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginParamDto.getUsername(), loginParamDto.getPassword())
            );
        }
        catch (Exception e) {
            throw new Exception("invalid username and password");
        }


        AuthenticatedUserDto authenticatedUserDto = new AuthenticatedUserDto();
        authenticatedUserDto.setUserDto(userService.findUserByUsername(loginParamDto.getUsername()));
        authenticatedUserDto.setToken(jwtUtil.generateToken(loginParamDto.getUsername()));
        return authenticatedUserDto;
    }
}
