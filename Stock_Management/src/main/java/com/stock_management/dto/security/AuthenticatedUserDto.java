package com.stock_management.dto.security;

import lombok.Data;

@Data
public class AuthenticatedUserDto {
    private UserDto userDto;
    private String token;
}
