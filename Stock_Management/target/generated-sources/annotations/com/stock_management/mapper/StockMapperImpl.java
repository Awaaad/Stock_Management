package com.stock_management.mapper;

import com.stock_management.dto.ProductDto;
import com.stock_management.dto.StockDto;
import com.stock_management.entity.Product;
import com.stock_management.entity.Stock;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-02-24T22:11:55+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.5 (Oracle Corporation)"
)
@Component
public class StockMapperImpl implements StockMapper {

    @Override
    public StockDto mapStockEntityToDto(Stock stockEntity) {
        if ( stockEntity == null ) {
            return null;
        }

        StockDto stockDto = new StockDto();

        stockDto.setStockId( stockEntity.getStockId() );
        stockDto.setStockName( stockEntity.getStockName() );
        stockDto.setDateAdded( stockEntity.getDateAdded() );
        stockDto.setProducts( productListToProductDtoList( stockEntity.getProducts() ) );

        return stockDto;
    }

    @Override
    public Stock mapStockDtoToEntity(StockDto stockDto) {
        if ( stockDto == null ) {
            return null;
        }

        Stock stock = new Stock();

        stock.setStockId( stockDto.getStockId() );
        stock.setStockName( stockDto.getStockName() );
        stock.setDateAdded( stockDto.getDateAdded() );
        stock.setProducts( productDtoListToProductList( stockDto.getProducts() ) );

        return stock;
    }

    protected ProductDto productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setProductId( product.getProductId() );
        productDto.setProductName( product.getProductName() );
        productDto.setDescription( product.getDescription() );
        productDto.setCategory( product.getCategory() );
        productDto.setMakeDate( product.getMakeDate() );
        productDto.setExpiryDate( product.getExpiryDate() );
        productDto.setSupplier( product.getSupplier() );
        productDto.setPrice( product.getPrice() );
        productDto.setQuantity( product.getQuantity() );

        return productDto;
    }

    protected List<ProductDto> productListToProductDtoList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDto> list1 = new ArrayList<ProductDto>( list.size() );
        for ( Product product : list ) {
            list1.add( productToProductDto( product ) );
        }

        return list1;
    }

    protected Product productDtoToProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setProductId( productDto.getProductId() );
        product.setProductName( productDto.getProductName() );
        product.setDescription( productDto.getDescription() );
        product.setCategory( productDto.getCategory() );
        product.setMakeDate( productDto.getMakeDate() );
        product.setExpiryDate( productDto.getExpiryDate() );
        product.setSupplier( productDto.getSupplier() );
        product.setPrice( productDto.getPrice() );
        product.setQuantity( productDto.getQuantity() );

        return product;
    }

    protected List<Product> productDtoListToProductList(List<ProductDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Product> list1 = new ArrayList<Product>( list.size() );
        for ( ProductDto productDto : list ) {
            list1.add( productDtoToProduct( productDto ) );
        }

        return list1;
    }
}
