package com.stock_management.mapper;

import com.stock_management.dto.CustomerDto;
import com.stock_management.entity.Customer;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto mapCustomerEntityToDto(Customer customer);
    @InheritInverseConfiguration
    Customer mapCustomerDtoToEntity(CustomerDto customerDto);
}
