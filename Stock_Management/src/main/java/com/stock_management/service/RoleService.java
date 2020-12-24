package com.stock_management.service;

import com.stock_management.dto.RoleDto;

import java.util.List;

public interface RoleService {
    void saveRole(RoleDto roleDto);

    List<RoleDto> findAllRoles();
}
