package com.stock_management.service;

import com.stock_management.dto.ProductDto;
import com.stock_management.dto.SupplierDto;
import com.stock_management.dto.SupplierListDto;

import java.util.List;

public interface SupplierService {
    // GET
    List<SupplierDto> findAllSuppliers();

    SupplierListDto findListOfSuppliersByFilters(String supplierName, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize);

    // POST
    void saveSupplier(SupplierListDto supplierListDto);
}
