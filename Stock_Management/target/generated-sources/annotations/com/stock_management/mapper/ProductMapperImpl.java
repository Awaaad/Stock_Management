package com.stock_management.mapper;

import com.stock_management.dto.ProductDto;
import com.stock_management.dto.StockDto;
import com.stock_management.dto.UserDto;
import com.stock_management.entity.Product;
import com.stock_management.entity.Stock;
import com.stock_management.entity.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-02-29T14:32:40+0400",
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
        productDto.setStock( stockToStockDto( productEntity.getStock() ) );

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
        product.setStock( stockDtoToStock( productDtoDto.getStock() ) );

        return product;
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserId( user.getUserId() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        userDto.setAge( user.getAge() );
        userDto.setEmail( user.getEmail() );
        userDto.setPhone( user.getPhone() );

        return userDto;
    }

    protected StockDto stockToStockDto(Stock stock) {
        if ( stock == null ) {
            return null;
        }

        StockDto stockDto = new StockDto();

        stockDto.setStockId( stock.getStockId() );
        stockDto.setStockName( stock.getStockName() );
        stockDto.setDateAdded( stock.getDateAdded() );
        stockDto.setUser( userToUserDto( stock.getUser() ) );

        return stockDto;
    }

    protected User userDtoToUser(UserDto userDto) {
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

        return user;
    }

    protected Stock stockDtoToStock(StockDto stockDto) {
        if ( stockDto == null ) {
            return null;
        }

        Stock stock = new Stock();

        stock.setStockId( stockDto.getStockId() );
        stock.setStockName( stockDto.getStockName() );
        stock.setDateAdded( stockDto.getDateAdded() );
        stock.setUser( userDtoToUser( stockDto.getUser() ) );

        return stock;
    }
}
