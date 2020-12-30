package com.stock_management.service.implementation;

import com.querydsl.core.BooleanBuilder;
import com.stock_management.dto.CustomerDto;
import com.stock_management.dto.CustomerListDto;
import com.stock_management.entity.Customer;
import com.stock_management.entity.QCustomer;
import com.stock_management.mapper.CustomerMapper;
import com.stock_management.repository.CustomerRepository;
import com.stock_management.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImplementation implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImplementation(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerListDto findListOfCustomerByFilters(String name, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.by("ASC".equals(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        BooleanBuilder predicate = buildProductPredicate(name);
        Page<Customer> customers = customerRepository.findAll(predicate,pageRequest);
        List<CustomerDto> customerDtos = customers.stream().map(customerMapper::mapCustomerEntityToDto).collect(Collectors.toList());
        var customerDtoList = new CustomerListDto();
        customerDtoList.setCustomerDtos(customerDtos);
        customerDtoList.setTotalElements(customers.getNumberOfElements());
        customerDtoList.setTotalPages(customers.getTotalPages());
        return customerDtoList;
    }

    private BooleanBuilder buildProductPredicate(String name) {
        var qCustomer = QCustomer.customer;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(!name.equals("")) {
            booleanBuilder.and(qCustomer.firstName.concat(" ").concat(qCustomer.lastName).toLowerCase().contains(name.toLowerCase()))
            .or(qCustomer.lastName.concat(" ").concat(qCustomer.firstName).toLowerCase().contains(name.toLowerCase()));
        }
        return booleanBuilder;
    }

    @Override
    public void saveCustomer(List<CustomerDto> customerDtoList) {
        customerRepository.saveAll(customerDtoList.stream().map(customerMapper::mapCustomerDtoToEntity).collect(Collectors.toList()));
    }

    @Override
    public void editCustomer(CustomerDto customerDto) {
        var optionalCustomer = customerRepository.findById(customerDto.getCustomerId());
        var customer = optionalCustomer.orElse(null);
        if (Objects.nonNull(customer)) {
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            customer.setAddress(customerDto.getAddress());
            customer.setTelephoneNumber(customerDto.getTelephoneNumber());
            customerRepository.save(customer);
        } else {
            System.out.println("Customer not found!");
        }
    }
}
