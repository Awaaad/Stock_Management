package com.stock_management.mapper;

import com.stock_management.dto.UserDto;
import com.stock_management.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto mapUserEntityToDto (User userEntity);
    @InheritInverseConfiguration
    User mapUserDtoToEntity (UserDto userDto);
}
