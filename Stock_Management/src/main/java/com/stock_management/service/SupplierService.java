package com.stock_management.service;

import com.stock_management.dto.*;
import com.stock_management.entity.Supplier;

import java.util.List;

public interface SupplierService {
    // GET
    List<SupplierDto> findAllSuppliers();

    SupplierDto findSupplierById(Long supplierId);

    SupplierListDto findListOfSuppliersByFilters(String supplierName, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize);

    // POST
    void saveSupplier(SupplierListDto supplierListDto);

    // PUT
    void editSupplier(SupplierDto supplierDto);

    List<OrderDto> findSupplierByOrderId(Long supplierId);
}
