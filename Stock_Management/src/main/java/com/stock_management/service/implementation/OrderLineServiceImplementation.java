package com.stock_management.service.implementation;

import com.stock_management.dto.order.OrderLineDto;
import com.stock_management.entity.OrderLine;
import com.stock_management.mapper.OrderLineMapper;
import com.stock_management.repository.OrderLineRepository;
import com.stock_management.service.OrderLineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderLineServiceImplementation implements OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public OrderLineServiceImplementation(OrderLineRepository orderLineRepository, OrderLineMapper orderLineMapper) {
        this.orderLineRepository = orderLineRepository;
        this.orderLineMapper = orderLineMapper;
    }

    @Override
    public List<OrderLineDto> findOrderProductsByOrderId(Long orderId) {
        List<OrderLine> orderLines = orderLineRepository.findByorder_orderId(orderId);
        return orderLines.stream().map(orderLineMapper::mapOrderLineEntityToDto).collect(Collectors.toList());
    }
}
