package com.stock_management.dto;

import lombok.Data;
import java.util.Set;

@Data
public class UserDto {
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private Integer phone;
    private String password;
    private Set<RoleDto> roles;
}
