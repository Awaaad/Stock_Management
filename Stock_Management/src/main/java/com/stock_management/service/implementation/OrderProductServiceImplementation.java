package com.stock_management.service.implementation;

import com.stock_management.dto.OrderDto;
import com.stock_management.dto.OrderProductDto;
import com.stock_management.dto.ProductDto;
import com.stock_management.mapper.OrderMapper;
import com.stock_management.mapper.OrderProductMapper;
import com.stock_management.repository.OrderProductRepository;
import com.stock_management.repository.OrderRepository;
import com.stock_management.repository.ProductRepository;
import com.stock_management.service.OrderProductService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class OrderProductServiceImplementation implements OrderProductService {
    public final OrderProductRepository orderProductRepository;
    public final OrderRepository orderRepository;
    public final ProductRepository productRepository;
    public final OrderProductMapper orderProductMapper;
    public final OrderMapper orderMapper;

    public OrderProductServiceImplementation(OrderProductRepository orderProductRepository, OrderRepository orderRepository, ProductRepository productRepository, OrderProductMapper orderProductMapper, OrderMapper orderMapper) {
        this.orderProductRepository = orderProductRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderProductMapper = orderProductMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public void saveOrderProduct(OrderProductDto orderProductDto) {
    }
}
