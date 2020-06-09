package com.stock_management.service.implementation;

import com.querydsl.core.BooleanBuilder;
import com.stock_management.dto.SupplierDto;
import com.stock_management.dto.SupplierListDto;
import com.stock_management.entity.QSupplier;
import com.stock_management.entity.Supplier;
import com.stock_management.repository.SupplierRepository;
import com.stock_management.service.SupplierService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Override
    public SupplierDto findSupplierById(Long supplierId) {
        java.util.Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        var oneSupplier = supplier.orElse(null);
        return supplierMapper.mapSupplierEntityToDto(oneSupplier);
    }

    @Override
    public SupplierListDto findListOfSuppliersByFilters(String supplierName, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.by("ASC".equals(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        BooleanBuilder predicate = buildProductPredicate(supplierName);
        Page<Supplier> suppliers = supplierRepository.findAll(predicate,pageRequest);
        List<SupplierDto> supplierDtos = suppliers.stream().map(supplierMapper::mapSupplierEntityToDto).collect(Collectors.toList());
        var supplierListDto = new SupplierListDto();
        supplierListDto.setSupplierDtos(supplierDtos);
        supplierListDto.setTotalElements(suppliers.getNumberOfElements());
        supplierListDto.setTotalPages(suppliers.getTotalPages());
        return supplierListDto;
    }

    private BooleanBuilder buildProductPredicate(String supplierName) {
        var qSupplier = QSupplier.supplier;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(!supplierName.equals("")) {
            booleanBuilder.and(qSupplier.supplierName.contains(supplierName));
        }
        return booleanBuilder;
    }

    // GET
//    @Override
//    public List<SupplierDto> findAllSuppliers() {
//        List<Supplier> suppliers = supplierRepository.findAll();
//        return suppliers.stream().map()
//    }

    // POST
    @Override
    public void saveSupplier(SupplierListDto supplierListDto) {
        var saveMultipleSuppliers = supplierListDto.getSupplierDtos().stream().map(supplierMapper::mapSupplierDtoToEntity).collect(Collectors.toList());
        supplierRepository.saveAll(saveMultipleSuppliers);
    }

    @Override
    public void editSupplier(SupplierDto supplierDto) {
        var supplier = findSupplierById(supplierDto.getSupplierId());
        if (supplier != null) {
            supplier.setSupplierName(supplierDto.getSupplierName());
            supplier.setEmail(supplierDto.getEmail());
            supplier.setAddress(supplierDto.getAddress());
            supplier.setTelephoneNumber(supplierDto.getTelephoneNumber());
            supplierRepository.save(supplierMapper.mapSupplierDtoToEntity(supplier));
        } else {
            System.out.println("Supplier Not Found!");
        }
    }
}
