package com.stock_management.mapper;

import com.stock_management.dto.OrderDto;
import com.stock_management.dto.OrderProductDto;
import com.stock_management.dto.RoleDto;
import com.stock_management.dto.UserDto;
import com.stock_management.entity.Order;
import com.stock_management.entity.OrderProduct;
import com.stock_management.entity.Role;
import com.stock_management.entity.UserProfile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-27T15:29:13+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.6 (AdoptOpenJDK)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Override
    public OrderDto mapOrderEntityToDto(Order orderEntity) {
        if ( orderEntity == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setUserProfileDto( userProfileToUserDto( orderEntity.getUserProfile() ) );
        orderDto.setOrderProductDtos( orderProductListToOrderProductDtoList( orderEntity.getOrderProducts() ) );
        orderDto.setOrderId( orderEntity.getOrderId() );
        orderDto.setCustomerName( orderEntity.getCustomerName() );
        orderDto.setOrderDate( orderEntity.getOrderDate() );
        orderDto.setTotalPrice( orderEntity.getTotalPrice() );
        orderDto.setAmountPaid( orderEntity.getAmountPaid() );
        orderDto.setPaid( orderEntity.getPaid() );
        orderDto.setPaymentMode( orderEntity.getPaymentMode() );
        orderDto.setPrescription( orderEntity.getPrescription() );
        orderDto.setDoctorName( orderEntity.getDoctorName() );

        return orderDto;
    }

    @Override
    public Order mapOrderDtoToEntity(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Order order = new Order();

        order.setOrderProducts( orderProductDtoListToOrderProductList( orderDto.getOrderProductDtos() ) );
        order.setUserProfile( userDtoToUserProfile( orderDto.getUserProfileDto() ) );
        order.setOrderId( orderDto.getOrderId() );
        order.setCustomerName( orderDto.getCustomerName() );
        order.setOrderDate( orderDto.getOrderDate() );
        order.setTotalPrice( orderDto.getTotalPrice() );
        order.setAmountPaid( orderDto.getAmountPaid() );
        order.setPaid( orderDto.getPaid() );
        order.setPaymentMode( orderDto.getPaymentMode() );
        order.setPrescription( orderDto.getPrescription() );
        order.setDoctorName( orderDto.getDoctorName() );

        return order;
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

    protected List<OrderProductDto> orderProductListToOrderProductDtoList(List<OrderProduct> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderProductDto> list1 = new ArrayList<OrderProductDto>( list.size() );
        for ( OrderProduct orderProduct : list ) {
            list1.add( orderProductMapper.mapOrderProductEntityToDto( orderProduct ) );
        }

        return list1;
    }

    protected List<OrderProduct> orderProductDtoListToOrderProductList(List<OrderProductDto> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderProduct> list1 = new ArrayList<OrderProduct>( list.size() );
        for ( OrderProductDto orderProductDto : list ) {
            list1.add( orderProductMapper.mapOrderProductDtoToEntity( orderProductDto ) );
        }

        return list1;
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
