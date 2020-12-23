package com.stock_management.mapper;

import com.stock_management.dto.UserDto;
import com.stock_management.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-23T18:18:05+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.6 (AdoptOpenJDK)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto mapUserEntityToDto(User userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserId( userEntity.getUserId() );
        userDto.setFirstName( userEntity.getFirstName() );
        userDto.setLastName( userEntity.getLastName() );
        userDto.setAge( userEntity.getAge() );
        userDto.setEmail( userEntity.getEmail() );
        userDto.setPhone( userEntity.getPhone() );

        return userDto;
    }

    @Override
    public User mapUserDtoToEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( userDto.getUserId() );
        user.setFirstName( userDto.getFirstName() );
        user.setLastName( userDto.getLastName() );
        user.setAge( userDto.getAge() );
        user.setEmail( userDto.getEmail() );
        user.setPhone( userDto.getPhone() );

        return user;
    }
}
