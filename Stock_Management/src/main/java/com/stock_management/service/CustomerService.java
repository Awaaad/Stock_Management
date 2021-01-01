package com.stock_management.service;

import com.stock_management.dto.CustomerDto;
import com.stock_management.dto.CustomerListDto;

import java.util.List;

public interface CustomerService {
    CustomerListDto findListOfCustomerByFilters(String name, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize);

    void saveCustomer(List<CustomerDto> customerDtoList);

    void editCustomer(CustomerDto customerDto) throws Exception;
}
