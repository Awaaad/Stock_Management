package com.stock_management.mapper;

import com.stock_management.dto.ProductDto;
import com.stock_management.dto.SupplierDto;
import com.stock_management.entity.Product;
import com.stock_management.entity.Supplier;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-02T18:03:01+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.6 (AdoptOpenJDK)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto mapProductEntityToDto(Product productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setProductId( productEntity.getProductId() );
        productDto.setProductName( productEntity.getProductName() );
        productDto.setDescription( productEntity.getDescription() );
        productDto.setDosage( productEntity.getDosage() );
        productDto.setCategory( productEntity.getCategory() );
        productDto.setBox( productEntity.getBox() );
        productDto.setUnitsPerBox( productEntity.getUnitsPerBox() );
        productDto.setUnitsTotal( productEntity.getUnitsTotal() );
        productDto.setPricePerBox( productEntity.getPricePerBox() );
        productDto.setPricePerUnit( productEntity.getPricePerUnit() );
        productDto.setRequirePrescription( productEntity.getRequirePrescription() );
        productDto.setSupplier( supplierToSupplierDto( productEntity.getSupplier() ) );

        return productDto;
    }

    @Override
    public Product mapProductDtoToEntity(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setProductId( productDto.getProductId() );
        product.setProductName( productDto.getProductName() );
        product.setDescription( productDto.getDescription() );
        product.setDosage( productDto.getDosage() );
        product.setCategory( productDto.getCategory() );
        product.setBox( productDto.getBox() );
        product.setUnitsPerBox( productDto.getUnitsPerBox() );
        product.setUnitsTotal( productDto.getUnitsTotal() );
        product.setPricePerBox( productDto.getPricePerBox() );
        product.setPricePerUnit( productDto.getPricePerUnit() );
        product.setRequirePrescription( productDto.getRequirePrescription() );
        product.setSupplier( supplierDtoToSupplier( productDto.getSupplier() ) );

        return product;
    }

    protected SupplierDto supplierToSupplierDto(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        SupplierDto supplierDto = new SupplierDto();

        supplierDto.setSupplierId( supplier.getSupplierId() );
        supplierDto.setSupplierName( supplier.getSupplierName() );
        supplierDto.setEmail( supplier.getEmail() );
        supplierDto.setTelephoneNumber( supplier.getTelephoneNumber() );
        supplierDto.setAddress( supplier.getAddress() );

        return supplierDto;
    }

    protected Supplier supplierDtoToSupplier(SupplierDto supplierDto) {
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
