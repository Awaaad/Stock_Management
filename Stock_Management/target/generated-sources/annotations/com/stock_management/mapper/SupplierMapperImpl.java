package com.stock_management.mapper;

import com.stock_management.dto.SupplierDto;
import com.stock_management.entity.Supplier;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-23T18:18:05+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.6 (AdoptOpenJDK)"
)
@Component
public class SupplierMapperImpl implements SupplierMapper {

    @Override
    public SupplierDto mapSupplierEntityToDto(Supplier supplierEntity) {
        if ( supplierEntity == null ) {
            return null;
        }

        SupplierDto supplierDto = new SupplierDto();

        supplierDto.setSupplierId( supplierEntity.getSupplierId() );
        supplierDto.setSupplierName( supplierEntity.getSupplierName() );
        supplierDto.setEmail( supplierEntity.getEmail() );
        supplierDto.setTelephoneNumber( supplierEntity.getTelephoneNumber() );
        supplierDto.setAddress( supplierEntity.getAddress() );

        return supplierDto;
    }

    @Override
    public Supplier mapSupplierDtoToEntity(SupplierDto supplierDto) {
        if ( supplierDto == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setSupplierId( supplierDto.getSupplierId() );
        supplier.setSupplierName( supplierDto.getSupplierName() );
        supplier.setEmail( supplierDto.getEmail() );
        supplier.setTelephoneNumber( supplierDto.getTelephoneNumber() );
        supplier.setAddress( supplierDto.getAddress() );

        return supplier;
    }
}
