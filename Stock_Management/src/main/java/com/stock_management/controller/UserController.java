package com.stock_management.controller;

import com.stock_management.dto.UserDto;
import com.stock_management.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // GET GOES HERE
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

    // POST GOES HERE
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@RequestBody UserDto userDto){
        userService.saveUser(userDto);
        return new ResponseEntity<>("User saved successfully!", HttpStatus.OK);
    }
}
