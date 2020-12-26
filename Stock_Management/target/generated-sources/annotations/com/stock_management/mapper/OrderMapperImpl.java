package com.stock_management.mapper;

import com.stock_management.dto.OrderDto;
import com.stock_management.dto.OrderProductDto;
import com.stock_management.entity.Order;
import com.stock_management.entity.OrderProduct;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-26T23:29:40+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.6 (AdoptOpenJDK)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Override
    public OrderDto mapOrderEntityToDto(Order orderEntity) {
        if ( orderEntity == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setOrderProductDtos( orderProductListToOrderProductDtoList( orderEntity.getOrderProducts() ) );
        orderDto.setOrderId( orderEntity.getOrderId() );
        orderDto.setCashierName( orderEntity.getCashierName() );
        orderDto.setCustomerName( orderEntity.getCustomerName() );
        orderDto.setOrderDate( orderEntity.getOrderDate() );
        orderDto.setTotalPrice( orderEntity.getTotalPrice() );
        orderDto.setAmountPaid( orderEntity.getAmountPaid() );
        orderDto.setPaid( orderEntity.getPaid() );
        orderDto.setPaymentMode( orderEntity.getPaymentMode() );
        orderDto.setPrescription( orderEntity.getPrescription() );
        orderDto.setDoctorName( orderEntity.getDoctorName() );

        return orderDto;
    }

    @Override
    public Order mapOrderDtoToEntity(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Order order = new Order();

        order.setOrderProducts( orderProductDtoListToOrderProductList( orderDto.getOrderProductDtos() ) );
        order.setOrderId( orderDto.getOrderId() );
        order.setCashierName( orderDto.getCashierName() );
        order.setCustomerName( orderDto.getCustomerName() );
        order.setOrderDate( orderDto.getOrderDate() );
        order.setTotalPrice( orderDto.getTotalPrice() );
        order.setAmountPaid( orderDto.getAmountPaid() );
        order.setPaid( orderDto.getPaid() );
        order.setPaymentMode( orderDto.getPaymentMode() );
        order.setPrescription( orderDto.getPrescription() );
        order.setDoctorName( orderDto.getDoctorName() );

        return order;
    }

    protected List<OrderProductDto> orderProductListToOrderProductDtoList(List<OrderProduct> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderProductDto> list1 = new ArrayList<OrderProductDto>( list.size() );
        for ( OrderProduct orderProduct : list ) {
            list1.add( orderProductMapper.mapOrderProductEntityToDto( orderProduct ) );
        }

        return list1;
    }

    protected List<OrderProduct> orderProductDtoListToOrderProductList(List<OrderProductDto> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderProduct> list1 = new ArrayList<OrderProduct>( list.size() );
        for ( OrderProductDto orderProductDto : list ) {
            list1.add( orderProductMapper.mapOrderProductDtoToEntity( orderProductDto ) );
        }

        return list1;
    }
}
