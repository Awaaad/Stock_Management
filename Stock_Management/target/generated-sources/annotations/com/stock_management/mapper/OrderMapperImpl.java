package com.stock_management.mapper;

import com.stock_management.dto.CustomerDto;
import com.stock_management.dto.DoctorDto;
import com.stock_management.dto.OrderDto;
import com.stock_management.dto.OrderProductDto;
import com.stock_management.dto.RoleDto;
import com.stock_management.dto.UserDto;
import com.stock_management.entity.Customer;
import com.stock_management.entity.Doctor;
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
    date = "2021-01-08T14:32:36+0400",
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

        orderDto.setCustomerDto( customerToCustomerDto( orderEntity.getCustomer() ) );
        orderDto.setDoctorDto( doctorToDoctorDto( orderEntity.getDoctor() ) );
        orderDto.setOrderProductDtos( orderProductListToOrderProductDtoList( orderEntity.getOrderProducts() ) );
        orderDto.setUserProfileDto( userProfileToUserDto( orderEntity.getUserProfile() ) );
        orderDto.setOrderId( orderEntity.getOrderId() );
        orderDto.setOrderDate( orderEntity.getOrderDate() );
        orderDto.setTotalPrice( orderEntity.getTotalPrice() );
        orderDto.setAmountPaid( orderEntity.getAmountPaid() );
        orderDto.setDiscount( orderEntity.getDiscount() );
        orderDto.setPaid( orderEntity.getPaid() );
        orderDto.setPaymentMode( orderEntity.getPaymentMode() );
        orderDto.setPrescription( orderEntity.getPrescription() );

        return orderDto;
    }

    @Override
    public Order mapOrderDtoToEntity(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Order order = new Order();

        order.setDoctor( doctorDtoToDoctor( orderDto.getDoctorDto() ) );
        order.setOrderProducts( orderProductDtoListToOrderProductList( orderDto.getOrderProductDtos() ) );
        order.setUserProfile( userDtoToUserProfile( orderDto.getUserProfileDto() ) );
        order.setCustomer( customerDtoToCustomer( orderDto.getCustomerDto() ) );
        order.setOrderId( orderDto.getOrderId() );
        order.setOrderDate( orderDto.getOrderDate() );
        order.setTotalPrice( orderDto.getTotalPrice() );
        order.setAmountPaid( orderDto.getAmountPaid() );
        order.setDiscount( orderDto.getDiscount() );
        order.setPaid( orderDto.getPaid() );
        order.setPaymentMode( orderDto.getPaymentMode() );
        order.setPrescription( orderDto.getPrescription() );

        return order;
    }

    protected CustomerDto customerToCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setCustomerId( customer.getCustomerId() );
        customerDto.setFirstName( customer.getFirstName() );
        customerDto.setLastName( customer.getLastName() );
        customerDto.setAddress( customer.getAddress() );
        customerDto.setTelephoneNumber( customer.getTelephoneNumber() );

        return customerDto;
    }

    protected DoctorDto doctorToDoctorDto(Doctor doctor) {
        if ( doctor == null ) {
            return null;
        }

        DoctorDto doctorDto = new DoctorDto();

        doctorDto.setDoctorId( doctor.getDoctorId() );
        doctorDto.setFirstName( doctor.getFirstName() );
        doctorDto.setLastName( doctor.getLastName() );
        doctorDto.setAddress( doctor.getAddress() );
        doctorDto.setTelephoneNumber( doctor.getTelephoneNumber() );

        return doctorDto;
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

    protected Doctor doctorDtoToDoctor(DoctorDto doctorDto) {
        if ( doctorDto == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setDoctorId( doctorDto.getDoctorId() );
        doctor.setFirstName( doctorDto.getFirstName() );
        doctor.setLastName( doctorDto.getLastName() );
        doctor.setAddress( doctorDto.getAddress() );
        doctor.setTelephoneNumber( doctorDto.getTelephoneNumber() );

        return doctor;
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

    protected Customer customerDtoToCustomer(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setCustomerId( customerDto.getCustomerId() );
        customer.setFirstName( customerDto.getFirstName() );
        customer.setLastName( customerDto.getLastName() );
        customer.setAddress( customerDto.getAddress() );
        customer.setTelephoneNumber( customerDto.getTelephoneNumber() );

        return customer;
    }
}
