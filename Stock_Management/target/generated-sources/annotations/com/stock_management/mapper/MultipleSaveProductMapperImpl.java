package com.stock_management.mapper;

import com.stock_management.dto.ProductListDto;
import com.stock_management.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-13T12:32:14+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.5 (Oracle Corporation)"
)
@Component
public class MultipleSaveProductMapperImpl implements MultipleSaveProductMapper {

    @Override
    public Product mapSaveMultipleProductsDtoToEntity(ProductListDto productListDto) {
        if ( productListDto == null ) {
            return null;
        }

        Product product = new Product();

        return product;
    }
}
