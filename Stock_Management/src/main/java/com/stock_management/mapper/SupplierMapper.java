package com.stock_management.mapper;
import com.stock_management.dto.SupplierDto;
import com.stock_management.entity.Supplier;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierDto mapSupplierEntityToDto (Supplier supplierEntity);
    @InheritInverseConfiguration
    Supplier mapSupplierDtoToEntity (SupplierDto supplierDto);
}
