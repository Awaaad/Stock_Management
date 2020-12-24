package com.stock_management.mapper;

import com.stock_management.dto.RoleDto;
import com.stock_management.entity.Role;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto mapRoleEntityToDto (Role roleEntity);
    @InheritInverseConfiguration
    Role mapRoleDtoToEntity (RoleDto roleDto);
}
