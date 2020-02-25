package com.stock_management.mapper;

import com.stock_management.dto.ProductDto;
import com.stock_management.dto.StockDto;
import com.stock_management.dto.UserDto;
import com.stock_management.entity.Product;
import com.stock_management.entity.Stock;
import com.stock_management.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-02-24T22:11:54+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.5 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto mapUserEntityToDto(User userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserId( userEntity.getUserId() );
        userDto.setFirstName( userEntity.getFirstName() );
        userDto.setLastName( userEntity.getLastName() );
        userDto.setAge( userEntity.getAge() );
        userDto.setEmail( userEntity.getEmail() );
        userDto.setPhone( userEntity.getPhone() );
        userDto.setStocks( stockListToStockDtoList( userEntity.getStocks() ) );

        return userDto;
    }

    @Override
    public User mapUserDtoToEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( userDto.getUserId() );
        user.setFirstName( userDto.getFirstName() );
        user.setLastName( userDto.getLastName() );
        user.setAge( userDto.getAge() );
        user.setEmail( userDto.getEmail() );
        user.setPhone( userDto.getPhone() );
        user.setStocks( stockDtoListToStockList( userDto.getStocks() ) );

        return user;
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

    protected StockDto stockToStockDto(Stock stock) {
        if ( stock == null ) {
            return null;
        }

        StockDto stockDto = new StockDto();

        stockDto.setStockId( stock.getStockId() );
        stockDto.setStockName( stock.getStockName() );
        stockDto.setDateAdded( stock.getDateAdded() );
        stockDto.setProducts( productListToProductDtoList( stock.getProducts() ) );

        return stockDto;
    }

    protected List<StockDto> stockListToStockDtoList(List<Stock> list) {
        if ( list == null ) {
            return null;
        }

        List<StockDto> list1 = new ArrayList<StockDto>( list.size() );
        for ( Stock stock : list ) {
            list1.add( stockToStockDto( stock ) );
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

    protected Stock stockDtoToStock(StockDto stockDto) {
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

    protected List<Stock> stockDtoListToStockList(List<StockDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Stock> list1 = new ArrayList<Stock>( list.size() );
        for ( StockDto stockDto : list ) {
            list1.add( stockDtoToStock( stockDto ) );
        }

        return list1;
    }
}
