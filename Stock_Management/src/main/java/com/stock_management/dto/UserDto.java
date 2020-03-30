package com.stock_management.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private Integer phone;
    private String role;
}
