package com.stock_management.mapper;

import com.stock_management.dto.CustomerDto;
import com.stock_management.dto.RoleDto;
import com.stock_management.dto.UserDto;
import com.stock_management.entity.Customer;
import com.stock_management.entity.Role;
import com.stock_management.entity.UserProfile;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-13T22:31:22+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.6 (AdoptOpenJDK)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDto mapCustomerEntityToDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setCustomerId( customer.getCustomerId() );
        customerDto.setFirstName( customer.getFirstName() );
        customerDto.setLastName( customer.getLastName() );
        customerDto.setAddress( customer.getAddress() );
        customerDto.setTelephoneNumber( customer.getTelephoneNumber() );
        customerDto.setCreatedBy( userProfileToUserDto( customer.getCreatedBy() ) );
        customerDto.setCreatedDate( customer.getCreatedDate() );
        customerDto.setLastModifiedBy( userProfileToUserDto( customer.getLastModifiedBy() ) );
        customerDto.setLastModifiedDate( customer.getLastModifiedDate() );

        return customerDto;
    }

    @Override
    public Customer mapCustomerDtoToEntity(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setCustomerId( customerDto.getCustomerId() );
        customer.setFirstName( customerDto.getFirstName() );
        customer.setLastName( customerDto.getLastName() );
        customer.setAddress( customerDto.getAddress() );
        customer.setTelephoneNumber( customerDto.getTelephoneNumber() );
        customer.setCreatedBy( userDtoToUserProfile( customerDto.getCreatedBy() ) );
        customer.setCreatedDate( customerDto.getCreatedDate() );
        customer.setLastModifiedBy( userDtoToUserProfile( customerDto.getLastModifiedBy() ) );
        customer.setLastModifiedDate( customerDto.getLastModifiedDate() );

        return customer;
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
