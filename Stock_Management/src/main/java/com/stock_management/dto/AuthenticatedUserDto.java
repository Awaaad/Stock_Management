package com.stock_management.dto;

import lombok.Data;

@Data
public class AuthenticatedUserDto {
    private UserDto userDto;
    private String token;
}
