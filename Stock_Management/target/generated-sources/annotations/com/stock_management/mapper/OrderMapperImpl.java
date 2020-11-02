package com.stock_management.mapper;

import com.stock_management.dto.OrderDto;
import com.stock_management.entity.Order;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-02T15:59:38+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.6 (AdoptOpenJDK)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDto mapOrderEntityToDto(Order orderEntity) {
        if ( orderEntity == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setOrderId( orderEntity.getOrderId() );
        orderDto.setCashierName( orderEntity.getCashierName() );
        orderDto.setCustomerName( orderEntity.getCustomerName() );
        orderDto.setOrderDate( orderEntity.getOrderDate() );
        orderDto.setTotalPrice( orderEntity.getTotalPrice() );
        orderDto.setPaid( orderEntity.getPaid() );

        return orderDto;
    }

    @Override
    public Order mapOrderDtoToEntity(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Order order = new Order();

        order.setOrderId( orderDto.getOrderId() );
        order.setCashierName( orderDto.getCashierName() );
        order.setCustomerName( orderDto.getCustomerName() );
        order.setOrderDate( orderDto.getOrderDate() );
        order.setTotalPrice( orderDto.getTotalPrice() );
        order.setPaid( orderDto.getPaid() );

        return order;
    }
}
