package com.stock_management.service;

import com.stock_management.dto.*;
import com.stock_management.entity.Supplier;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SupplierService {
    // GET
    List<SupplierDto> findAllSuppliers();

    SupplierDto findSupplierById(Long supplierId);

    SupplierListDto findListOfSuppliersByFilters(String supplierName, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize);

    SupplierDto findSupplierByName(String supplierName);

    // POST
    void saveSupplier(SupplierListDto supplierListDto);

    void upload(MultipartFile file) throws IOException;

    boolean hasExcelFormat(MultipartFile file);

    // PUT
    void editSupplier(SupplierDto supplierDto) throws Exception;

    List<OrderDto> findSupplierByOrderId(Long supplierId);
}
