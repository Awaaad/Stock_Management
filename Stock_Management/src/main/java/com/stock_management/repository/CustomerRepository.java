package com.stock_management.repository;

import com.stock_management.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CustomerRepository extends JpaRepository<Customer, Long>, QuerydslPredicateExecutor<Customer> {
    Customer findCustomerByLastName(String lastName);
    Boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
