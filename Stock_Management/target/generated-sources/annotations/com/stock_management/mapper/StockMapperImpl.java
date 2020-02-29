package com.stock_management.mapper;

import com.stock_management.dto.StockDto;
import com.stock_management.dto.UserDto;
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
        stockDto.setUser( userToUserDto( stockEntity.getUser() ) );

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
        stock.setUser( userDtoToUser( stockDto.getUser() ) );

        return stock;
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
}
