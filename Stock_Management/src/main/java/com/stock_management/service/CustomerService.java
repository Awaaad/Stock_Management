package com.stock_management.service;

import com.stock_management.dto.customer.CustomerDto;
import com.stock_management.dto.customer.CustomerListDto;

import java.util.List;

public interface CustomerService {
    CustomerListDto findListOfCustomerByFilters(String name, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize);

    void saveCustomer(List<CustomerDto> customerDtoList);

    void editCustomer(CustomerDto customerDto) throws Exception;
}
