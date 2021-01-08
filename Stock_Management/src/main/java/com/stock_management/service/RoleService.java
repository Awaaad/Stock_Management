package com.stock_management.service;

import com.stock_management.dto.RoleDto;
import com.stock_management.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<RoleDto> findAllRoles();

    void saveRole(RoleDto roleDto);

    void saveRoles(List<Role> roles);
}
