package com.stock_management.mapper;

import com.stock_management.dto.CustomerDto;
import com.stock_management.entity.Customer;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer mapCustomerEntityToDto(CustomerDto customerDto);
    @InheritInverseConfiguration
    CustomerDto mapCustomerDtoToEntity(Customer customer);
}
