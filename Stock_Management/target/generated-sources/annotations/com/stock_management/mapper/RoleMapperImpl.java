package com.stock_management.mapper;

import com.stock_management.dto.RoleDto;
import com.stock_management.entity.Role;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-24T22:58:30+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.6 (AdoptOpenJDK)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDto mapRoleEntityToDto(Role roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setRoleId( roleEntity.getRoleId() );
        roleDto.setRole( roleEntity.getRole() );

        return roleDto;
    }

    @Override
    public Role mapRoleDtoToEntity(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setRoleId( roleDto.getRoleId() );
        role.setRole( roleDto.getRole() );

        return role;
    }
}
