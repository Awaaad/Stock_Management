package com.stock_management.mapper;

import com.stock_management.dto.OrderDto;
import com.stock_management.dto.OrderProductDto;
import com.stock_management.entity.Order;
import com.stock_management.entity.OrderProduct;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-02T15:59:38+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.6 (AdoptOpenJDK)"
)
@Component
public class OrderProductMapperImpl implements OrderProductMapper {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public OrderProductDto mapOrderProductEntityToDto(OrderProduct orderProductEntity) {
        if ( orderProductEntity == null ) {
            return null;
        }

        OrderProductDto orderProductDto = new OrderProductDto();

        orderProductDto.setOrderDto( orderToOrderDto( orderProductEntity.getOrder() ) );
        orderProductDto.setProductDto( productMapper.mapProductEntityToDto( orderProductEntity.getProduct() ) );
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

        orderProduct.setOrder( orderDtoToOrder( orderProductDto.getOrderDto() ) );
        orderProduct.setProduct( productMapper.mapProductDtoToEntity( orderProductDto.getProductDto() ) );
        orderProduct.setOrderProductId( orderProductDto.getOrderProductId() );
        orderProduct.setProductName( orderProductDto.getProductName() );
        orderProduct.setBoxesOrdered( orderProductDto.getBoxesOrdered() );
        orderProduct.setUnitsOrdered( orderProductDto.getUnitsOrdered() );
        orderProduct.setTotalPrice( orderProductDto.getTotalPrice() );

        return orderProduct;
    }

    protected OrderDto orderToOrderDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setOrderId( order.getOrderId() );
        orderDto.setCashierName( order.getCashierName() );
        orderDto.setCustomerName( order.getCustomerName() );
        orderDto.setOrderDate( order.getOrderDate() );
        orderDto.setTotalPrice( order.getTotalPrice() );
        orderDto.setPaid( order.getPaid() );

        return orderDto;
    }

    protected Order orderDtoToOrder(OrderDto orderDto) {
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
