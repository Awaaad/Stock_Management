package com.stock_management.service;

import com.stock_management.dto.SupplierDto;

import java.util.List;

public interface SupplierService {
    // GET
    List<SupplierDto> findAllSuppliers();
}
