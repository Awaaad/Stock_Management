package com.stock_management.controller;

import com.stock_management.dto.CustomerDto;
import com.stock_management.dto.CustomerListDto;
import com.stock_management.dto.SupplierDto;
import com.stock_management.dto.SupplierListDto;
import com.stock_management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("filter")
    public ResponseEntity<CustomerListDto> getCustomerViaFilter(@RequestParam String name, String sortOrder, @RequestParam String sortBy, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return new ResponseEntity<>(customerService.findListOfCustomerByFilters(name, sortOrder, sortBy, pageNumber, pageSize), HttpStatus.OK);
    }

    @PostMapping("save-customers")
    public ResponseEntity<String> saveCustomers(@RequestBody List<CustomerDto> customerDtoList){
        customerService.saveCustomer(customerDtoList);
        return new ResponseEntity<String>("Customer saved successfully!", HttpStatus.OK);
    }

    @PutMapping("edit-customer")
    public ResponseEntity<String> editCustomer(@RequestBody CustomerDto customerDto){
        customerService.editCustomer(customerDto);
        return new ResponseEntity<String>("Customer edited successfully!", HttpStatus.OK);
    }
}
