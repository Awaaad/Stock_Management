package com.stock_management.service.implementation;

import com.stock_management.dto.SupplierDto;
import com.stock_management.entity.Supplier;
import com.stock_management.repository.SupplierRepository;
import com.stock_management.service.SupplierService;
import org.springframework.stereotype.Service;
import com.stock_management.mapper.SupplierMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImplementation implements SupplierService{
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierServiceImplementation(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    @Override
    public List<SupplierDto> findAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream().map(supplierMapper::mapSupplierEntityToDto).collect(Collectors.toList());
    }

    // GET
//    @Override
//    public List<SupplierDto> findAllSuppliers() {
//        List<Supplier> suppliers = supplierRepository.findAll();
//        return suppliers.stream().map()
//    }
}
