package com.stock_management.controller;

import com.stock_management.dto.ProductDto;
import com.stock_management.dto.SupplierDto;
import com.stock_management.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // GET
    @GetMapping("all")
    public ResponseEntity<List<SupplierDto>> getAllSuppliers(){
        return new ResponseEntity<>(supplierService.findAllSuppliers(), HttpStatus.OK);
    }
}
