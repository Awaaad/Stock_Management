package com.stock_management.dto.security;

import lombok.Data;

@Data
public class LoginParamDto {
    private String username;
    private String password;
}
