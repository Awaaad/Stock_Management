package com.stock_management.mapper;

import com.stock_management.dto.ProductDto;
import com.stock_management.entity.Product;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-02-24T22:11:55+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.5 (Oracle Corporation)"
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
        productDto.setCategory( productEntity.getCategory() );
        productDto.setMakeDate( productEntity.getMakeDate() );
        productDto.setExpiryDate( productEntity.getExpiryDate() );
        productDto.setSupplier( productEntity.getSupplier() );
        productDto.setPrice( productEntity.getPrice() );
        productDto.setQuantity( productEntity.getQuantity() );

        return productDto;
    }

    @Override
    public Product mapProductDtoToEntity(ProductDto productDtoDto) {
        if ( productDtoDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setProductId( productDtoDto.getProductId() );
        product.setProductName( productDtoDto.getProductName() );
        product.setDescription( productDtoDto.getDescription() );
        product.setCategory( productDtoDto.getCategory() );
        product.setMakeDate( productDtoDto.getMakeDate() );
        product.setExpiryDate( productDtoDto.getExpiryDate() );
        product.setSupplier( productDtoDto.getSupplier() );
        product.setPrice( productDtoDto.getPrice() );
        product.setQuantity( productDtoDto.getQuantity() );

        return product;
    }
}
