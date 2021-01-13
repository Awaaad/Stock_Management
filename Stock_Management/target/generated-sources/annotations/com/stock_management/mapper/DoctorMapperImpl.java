package com.stock_management.mapper;

import com.stock_management.dto.DoctorDto;
import com.stock_management.dto.RoleDto;
import com.stock_management.dto.UserDto;
import com.stock_management.entity.Doctor;
import com.stock_management.entity.Role;
import com.stock_management.entity.UserProfile;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-13T23:07:24+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.6 (AdoptOpenJDK)"
)
@Component
public class DoctorMapperImpl implements DoctorMapper {

    @Override
    public DoctorDto mapDoctorEntityToDto(Doctor doctor) {
        if ( doctor == null ) {
            return null;
        }

        DoctorDto doctorDto = new DoctorDto();

        doctorDto.setDoctorId( doctor.getDoctorId() );
        doctorDto.setFirstName( doctor.getFirstName() );
        doctorDto.setLastName( doctor.getLastName() );
        doctorDto.setAddress( doctor.getAddress() );
        doctorDto.setTelephoneNumber( doctor.getTelephoneNumber() );
        doctorDto.setCreatedBy( userProfileToUserDto( doctor.getCreatedBy() ) );
        doctorDto.setCreatedDate( doctor.getCreatedDate() );
        doctorDto.setLastModifiedBy( userProfileToUserDto( doctor.getLastModifiedBy() ) );
        doctorDto.setLastModifiedDate( doctor.getLastModifiedDate() );

        return doctorDto;
    }

    @Override
    public Doctor mapDoctorDtoToEntity(DoctorDto doctorDto) {
        if ( doctorDto == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setDoctorId( doctorDto.getDoctorId() );
        doctor.setFirstName( doctorDto.getFirstName() );
        doctor.setLastName( doctorDto.getLastName() );
        doctor.setAddress( doctorDto.getAddress() );
        doctor.setTelephoneNumber( doctorDto.getTelephoneNumber() );
        doctor.setCreatedBy( userDtoToUserProfile( doctorDto.getCreatedBy() ) );
        doctor.setCreatedDate( doctorDto.getCreatedDate() );
        doctor.setLastModifiedBy( userDtoToUserProfile( doctorDto.getLastModifiedBy() ) );
        doctor.setLastModifiedDate( doctorDto.getLastModifiedDate() );

        return doctor;
    }

    protected RoleDto roleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setRoleId( role.getRoleId() );
        roleDto.setRole( role.getRole() );

        return roleDto;
    }

    protected Set<RoleDto> roleSetToRoleDtoSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDto> set1 = new HashSet<RoleDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleDto( role ) );
        }

        return set1;
    }

    protected UserDto userProfileToUserDto(UserProfile userProfile) {
        if ( userProfile == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserId( userProfile.getUserId() );
        userDto.setUsername( userProfile.getUsername() );
        userDto.setFirstName( userProfile.getFirstName() );
        userDto.setLastName( userProfile.getLastName() );
        userDto.setAge( userProfile.getAge() );
        userDto.setEmail( userProfile.getEmail() );
        userDto.setPhone( userProfile.getPhone() );
        userDto.setPassword( userProfile.getPassword() );
        userDto.setRoles( roleSetToRoleDtoSet( userProfile.getRoles() ) );

        return userDto;
    }

    protected Role roleDtoToRole(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setRoleId( roleDto.getRoleId() );
        role.setRole( roleDto.getRole() );

        return role;
    }

    protected Set<Role> roleDtoSetToRoleSet(Set<RoleDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new HashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleDto roleDto : set ) {
            set1.add( roleDtoToRole( roleDto ) );
        }

        return set1;
    }

    protected UserProfile userDtoToUserProfile(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserProfile userProfile = new UserProfile();

        userProfile.setUserId( userDto.getUserId() );
        userProfile.setUsername( userDto.getUsername() );
        userProfile.setPassword( userDto.getPassword() );
        userProfile.setFirstName( userDto.getFirstName() );
        userProfile.setLastName( userDto.getLastName() );
        userProfile.setAge( userDto.getAge() );
        userProfile.setEmail( userDto.getEmail() );
        userProfile.setPhone( userDto.getPhone() );
        userProfile.setRoles( roleDtoSetToRoleSet( userDto.getRoles() ) );

        return userProfile;
    }
}
