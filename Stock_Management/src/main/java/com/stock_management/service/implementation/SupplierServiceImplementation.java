package com.stock_management.service.implementation;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.stock_management.dto.CountProductsDto;
import com.stock_management.dto.OrderDto;
import com.stock_management.dto.SupplierDto;
import com.stock_management.dto.SupplierListDto;
import com.stock_management.entity.*;
import com.stock_management.mapper.OrderMapper;
import com.stock_management.repository.SupplierRepository;
import com.stock_management.service.SupplierService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.stock_management.mapper.SupplierMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImplementation implements SupplierService{
    @PersistenceContext
    private EntityManager entityManager;

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final OrderMapper orderMapper;

    public SupplierServiceImplementation(SupplierRepository supplierRepository, SupplierMapper supplierMapper, OrderMapper orderMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
        this.orderMapper = orderMapper;
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
            booleanBuilder.and(qSupplier.supplierName.toLowerCase().contains(supplierName.toLowerCase()));
        }
        return booleanBuilder;
    }


    @Override
    public SupplierDto findSupplierByName(String supplierName) {
        Optional<Supplier> supplier = Optional.ofNullable(supplierRepository.findSupplierBySupplierName(supplierName));
        var oneSupplier = supplier.orElse(null);
        return supplierMapper.mapSupplierEntityToDto(oneSupplier);
    }

    // POST
    @Override
    @Transactional
    public void saveSupplier(SupplierListDto supplierListDto) {
        var saveMultipleSuppliers = supplierListDto.getSupplierDtos().stream().map(supplierMapper::mapSupplierDtoToEntity).collect(Collectors.toList());
        supplierRepository.saveAll(saveMultipleSuppliers);
    }

    @Override
    @Transactional
    public void editSupplier(SupplierDto supplierDto) throws Exception {
        var supplier = findSupplierById(supplierDto.getSupplierId());
        if (Objects.nonNull(supplier)) {
            supplier.setSupplierName(supplierDto.getSupplierName());
            supplier.setEmail(supplierDto.getEmail());
            supplier.setAddress(supplierDto.getAddress());
            supplier.setTelephoneNumber(supplierDto.getTelephoneNumber());
            supplierRepository.save(supplierMapper.mapSupplierDtoToEntity(supplier));
        } else {
            throw new Exception("supplier.not.found");
        }
    }

    @Override
    public List<OrderDto> findSupplierByOrderId(Long supplierId) {
        CountProductsDto countProductsDto = new CountProductsDto();
        var qSupplier = QSupplier.supplier;
        var qProduct = QProduct.product;
        var qOrderProduct = QOrderProduct.orderProduct;
        var qOrder = QOrder.order;
        JPAQuery<Supplier> query = new JPAQuery<>(entityManager);

        if (supplierId != null) {
            var orderList = query.select(qOrder).distinct().from(qOrder).
                    innerJoin(qOrderProduct).on(qOrder.orderId.eq(qOrderProduct.order.orderId)).
                    innerJoin(qProduct).on(qOrderProduct.product.productId.eq(qProduct.productId)).
                    innerJoin(qSupplier).on(qProduct.supplier.supplierId.eq(qSupplier.supplierId)).where(qSupplier.supplierId.eq(supplierId)).fetch();
//            countProductsDto.setNumberOfProducts((long) supplierList.size());
            return orderList.stream().map(orderMapper::mapOrderEntityToDto).collect(Collectors.toList());
        } else {
            return null;
        }
    }
}
