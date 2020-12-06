package com.stock_management.service.implementation;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.stock_management.dto.CustomerReceiptDto;
import com.stock_management.dto.OrderDto;
import com.stock_management.dto.OrderListDto;
import com.stock_management.dto.OrderProductDto;
import com.stock_management.entity.Order;
import com.stock_management.entity.OrderProduct;
import com.stock_management.entity.QOrder;
import com.stock_management.entity.QOrderProduct;
import com.stock_management.entity.QProduct;
import com.stock_management.mapper.OrderMapper;
import com.stock_management.mapper.OrderProductMapper;
import com.stock_management.mapper.ReceiptMapper;
import com.stock_management.repository.OrderProductRepository;
import com.stock_management.repository.OrderRepository;
import com.stock_management.repository.ProductRepository;
import com.stock_management.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplementation implements OrderService {
    @PersistenceContext
    private EntityManager entityManager;

    public final OrderRepository orderRepository;
    public final OrderMapper orderMapper;
    private final OrderProductMapper orderProductMapper;
    public final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;
    private final ReceiptMapper receiptMapper;

    public OrderServiceImplementation(OrderRepository orderRepository, OrderMapper orderMapper, OrderProductMapper orderProductMapper, ProductRepository productRepository, OrderProductRepository orderProductRepository, ReceiptMapper receiptMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderProductMapper = orderProductMapper;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
        this.receiptMapper = receiptMapper;
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

    // filter DSL
    @Override
    @Transactional
    public OrderListDto findListOfOrdersByFilters(String customerName, String cashierName, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.by("ASC".equals(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        BooleanBuilder predicate = buildProductPredicate(customerName, cashierName);
        Page<Order> orders = orderRepository.findAll(predicate,pageRequest);
        List<OrderDto> orderDtos = orders.stream().map(orderMapper::mapOrderEntityToDto).collect(Collectors.toList());

        List<OrderDto> list = new ArrayList<>();
        for (Order o : orders) {
            list.add(orderMapper.mapOrderEntityToDto(o));
        }

        var orderListDto = new OrderListDto();
        orderListDto.setOrderDtos(orderDtos);
        orderListDto.setTotalElements(orders.getNumberOfElements());
        orderListDto.setTotalPages(orders.getTotalPages());
        return orderListDto;
    }

    @Override
    public CustomerReceiptDto findCustomerReceiptDetails(Long orderId) {
        var qOrder = QOrder.order;
        var qOrderProduct = QOrderProduct.orderProduct;
        var qProduct = QProduct.product;

        if (Objects.nonNull(orderId)) {
            var customerReceipt = new JPAQuery<OrderProduct>(entityManager).select(
                    qOrder.orderId.as("orderId"),
                    qOrder.cashierName.as("cashierName"),
                    qOrder.customerName.as("CustomerName"),
                    qOrder.orderDate.as("orderDate"),
                    qOrder.totalPrice.as("totalPrice"),
                    qOrderProduct.product.productName.as("productName"),
                    qOrderProduct.boxesOrdered.as("boxesOrdered"),
                    qOrderProduct.unitsOrdered.as("unitsOrdered"),
                    qOrderProduct.totalPrice.as("price"),
                    qProduct.pricePerBox.as("pricePerBox"),
                    qProduct.pricePerUnit.as("pricePerUnit"),
                    qProduct.unitsPerBox.as("unitsPerBox"))
                    .from(qOrderProduct)
                    .leftJoin(qOrderProduct.order, qOrder)
                    .leftJoin(qOrderProduct.product, qProduct)
                    .where(qOrder.orderId.eq(orderId))
                    .fetchResults().getResults();

            return receiptMapper.mapCustomerReceiptDtoEntityToDto(customerReceipt);
        } else {
            return null;
        }
    }

    private BooleanBuilder buildProductPredicate(String customerName, String cashierName) {
        var qOrder = QOrder.order;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(!customerName.equals("")) {
            booleanBuilder.and(qOrder.customerName.contains(customerName));
        }
        if(!cashierName.equals("All")) {
            booleanBuilder.and(qOrder.cashierName.eq(cashierName));
        }
        return booleanBuilder;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public void saveOrder(OrderDto orderDto) {

        var order = orderMapper.mapOrderDtoToEntity(orderDto);
        var savedOrder = orderRepository.save(order);
        orderProductRepository.saveAll(orderDto.getOrderProductDtos().stream().map(orderProductDto ->
                mapOrderProduct(orderProductDto, savedOrder)).collect(Collectors.toList()));

    }

    private OrderProduct mapOrderProduct(OrderProductDto orderProductDto, Order order) {
        var orderProduct = orderProductMapper.mapOrderProductDtoToEntity(orderProductDto);
        orderProduct.setOrder(order);
        var product = productRepository.findById(orderProductDto.getProductDto().getProductId());
        if (product.isPresent()) {
            var productEntity = product.get();
            orderProduct.setProduct(productEntity);
            var currentUnits = productEntity.getUnitsTotal(); // 1000
            productEntity.setUnitsTotal(currentUnits - (orderProductDto.getUnitsOrdered()));
            productEntity.setBox(productEntity.getUnitsTotal()/productEntity.getUnitsPerBox());
        }
        return orderProduct;
    }


    // POST
//    @Override
//    public void saveOrder(OrderDto orderDto) {
//        ProductDto productDto = new ProductDto();
//        Order order = new Order();
//        var saveOrderDetails = orderMapper.mapOrderDtoToEntity(orderDto);
//        var productEntities = saveOrderDetails.getProducts().stream().map(product -> {
//            var productEntity = productRepository.findById(product.getProductId());
//            if (productEntity.isPresent()){
//                productEntity.get().setBox(product.getBox());
//                productEntity.get().setUnitsTotal(product.getUnitsTotal());
//                return productEntity.get();
//            }
//            return null;
//        }).filter(Objects::nonNull).collect(Collectors.toList());
//        productRepository.saveAll(productEntities);
//        orderRepository.save(saveOrderDetails);
//    }

    @Override
    public void editOrder(OrderDto orderDto) {
        var order = findOrderById(orderDto.getOrderId());
        if (order != null) {
            order.setPaid(true);
            orderRepository.save(orderMapper.mapOrderDtoToEntity(order));
        } else {
            System.out.println("Order Not Found!");
        }
    }
}
