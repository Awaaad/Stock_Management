package com.stock_management.service.implementation;

import com.stock_management.dto.OrderDto;
import com.stock_management.entity.Order;
import com.stock_management.mapper.OrderMapper;
import com.stock_management.repository.OrderRepository;
import com.stock_management.service.OrderService;
import lombok.var;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplementation implements OrderService {
    public final OrderRepository orderRepository;
    public final OrderMapper orderMapper;

    public OrderServiceImplementation (OrderRepository orderRepository, OrderMapper orderMapper){
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    // GET
    @Override
    public List<OrderDto> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::mapOrderEntityToDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto findOrderById(Long OrderId) {
        java.util.Optional<Order> order = orderRepository.findById(OrderId);
        var oneOrder = order.orElse(null);
        return orderMapper.mapOrderEntityToDto(oneOrder);
    }

    // POST
    @Override
    public void saveOrder(OrderDto orderDto) {
        var saveOrderDetails = orderMapper.mapOrderDtoToEntity(orderDto);
        orderRepository.save(saveOrderDetails);
    }
}
