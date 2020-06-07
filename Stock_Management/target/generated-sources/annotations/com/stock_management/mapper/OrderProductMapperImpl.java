package com.stock_management.mapper;

import com.stock_management.dto.OrderProductDto;
import com.stock_management.entity.OrderProduct;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-07T21:18:09+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.6 (AdoptOpenJDK)"
)
@Component
public class OrderProductMapperImpl implements OrderProductMapper {

    @Override
    public OrderProductDto mapOrderProductEntityToDto(OrderProduct orderProductEntity) {
        if ( orderProductEntity == null ) {
            return null;
        }

        OrderProductDto orderProductDto = new OrderProductDto();

        orderProductDto.setOrderProductId( orderProductEntity.getOrderProductId() );
        orderProductDto.setProductName( orderProductEntity.getProductName() );
        orderProductDto.setBoxesOrdered( orderProductEntity.getBoxesOrdered() );
        orderProductDto.setUnitsOrdered( orderProductEntity.getUnitsOrdered() );
        orderProductDto.setTotalPrice( orderProductEntity.getTotalPrice() );

        return orderProductDto;
    }

    @Override
    public OrderProduct mapOrderProductDtoToEntity(OrderProductDto orderProductDto) {
        if ( orderProductDto == null ) {
            return null;
        }

        OrderProduct orderProduct = new OrderProduct();

        orderProduct.setOrderProductId( orderProductDto.getOrderProductId() );
        orderProduct.setProductName( orderProductDto.getProductName() );
        orderProduct.setBoxesOrdered( orderProductDto.getBoxesOrdered() );
        orderProduct.setUnitsOrdered( orderProductDto.getUnitsOrdered() );
        orderProduct.setTotalPrice( orderProductDto.getTotalPrice() );

        return orderProduct;
    }
}
