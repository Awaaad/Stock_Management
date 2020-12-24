package com.stock_management.mapper;

import com.stock_management.dto.UserDto;
import com.stock_management.entity.UserProfile;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto mapUserEntityToDto (UserProfile userProfileEntity);
    @InheritInverseConfiguration
    UserProfile mapUserDtoToEntity (UserDto userDto);
}
