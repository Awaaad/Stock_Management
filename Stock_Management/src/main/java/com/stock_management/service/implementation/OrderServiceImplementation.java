package com.stock_management.service.implementation;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.stock_management.dto.CustomerReceiptDto;
import com.stock_management.dto.MonthlySalesDto;
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
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@EnableAutoConfiguration
@Service
public class OrderServiceImplementation implements OrderService {
    @PersistenceContext
    private EntityManager entityManager;

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderProductMapper orderProductMapper;
    private final ProductRepository productRepository;
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
    public OrderListDto findListOfOrdersByFilters(String customerName, Long userId, LocalDateTime orderDateTime, Boolean paid, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.by("ASC".equals(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        BooleanBuilder predicate = buildProductPredicate(customerName, userId, orderDateTime, paid);
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

    private BooleanBuilder buildProductPredicate(String customerName, Long userId, LocalDateTime orderDateTime, Boolean paid) {
        var qOrder = QOrder.order;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(!customerName.equals("")) {
            booleanBuilder.and(qOrder.customerName.toLowerCase().contains(customerName.toLowerCase()));
        }
        if(Objects.nonNull(userId) && userId != 0) {
            booleanBuilder.and(qOrder.userProfile.userId.eq(userId));
        }
        if(Objects.nonNull(orderDateTime)) {
            booleanBuilder.and(qOrder.orderDate.eq(LocalDateTime.from(orderDateTime.toLocalDate())));
        }
        if(Objects.nonNull(paid)) {
            booleanBuilder.and(qOrder.paid.eq(paid));
        }
        return booleanBuilder;
    }

    @Override
    public CustomerReceiptDto findCustomerReceiptDetails(Long orderId) {
        var qOrder = QOrder.order;
        var qOrderProduct = QOrderProduct.orderProduct;
        var qProduct = QProduct.product;

        if (Objects.nonNull(orderId)) {
            var customerReceipt = new JPAQuery<OrderProduct>(entityManager).select(
                    qOrder.orderId.as("orderId"),
                    qOrder.userProfile.firstName.as("cashierName"),
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

    @Override
    public Double findEODSalesAmount(LocalDate dateTime) {
        var order = orderRepository.findAll();
        return order.stream().filter(order1 -> dateTime.equals(order1.getOrderDate().toLocalDate())).mapToDouble(Order::getTotalPrice).sum();
    }

    @Override
    public Double findMonthSalesAmount(LocalDate month, LocalDate year) {
        var order = orderRepository.findAll();
        return order.stream().filter(order1 ->
                month.getMonth().equals(order1.getOrderDate().toLocalDate().getMonth()) &&
                        (year.getYear() == (order1.getOrderDate().toLocalDate().getYear())))
                .mapToDouble(Order::getTotalPrice).sum();
    }

    @Override
    public MonthlySalesDto findSalesForEachMonth(LocalDate year) {
        MonthlySalesDto monthlySalesDto = new MonthlySalesDto();
        var order = orderRepository.findAll();
        monthlySalesDto.setJan(computeSumForOrder(order, year, 1));
        monthlySalesDto.setFeb(computeSumForOrder(order, year, 2));
        monthlySalesDto.setMar(computeSumForOrder(order, year, 3));
        monthlySalesDto.setApr(computeSumForOrder(order, year, 4));
        monthlySalesDto.setMay(computeSumForOrder(order, year, 5));
        monthlySalesDto.setJun(computeSumForOrder(order, year, 6));
        monthlySalesDto.setJul(computeSumForOrder(order, year, 7));
        monthlySalesDto.setAug(computeSumForOrder(order, year, 8));
        monthlySalesDto.setSep(computeSumForOrder(order, year, 9));
        monthlySalesDto.setOct(computeSumForOrder(order, year, 10));
        monthlySalesDto.setNov(computeSumForOrder(order, year, 11));
        monthlySalesDto.setDec(computeSumForOrder(order, year, 12));
        return monthlySalesDto;
    }

    private Double computeSumForOrder(List<Order> order, LocalDate year, int month) {
        return order.stream().filter(order1 -> year.getYear() == order1.getOrderDate().getYear() && order1.getOrderDate().getMonthValue() == month).mapToDouble(Order::getTotalPrice).sum();
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public void saveOrder(OrderDto orderDto) {
        List<OrderProductDto> orderProductDtos = new ArrayList<>();
        for (OrderProductDto orderProductDto : orderDto.getOrderProductDtos()) {
            if (!orderProductDto.getBoxesOrdered().equals(0) || !orderProductDto.getUnitsOrdered().equals(0)) {
                orderProductDtos.add(orderProductDto);
            }
        }
        orderDto.setOrderProductDtos(orderProductDtos);
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

    @Override
    public void editOrder(OrderDto orderDto) {
        var order = findOrderById(orderDto.getOrderId());
        if (order != null) {
            order.setPaid(true);
            order.setAmountPaid(orderDto.getAmountPaid());
            orderRepository.save(orderMapper.mapOrderDtoToEntity(order));
        } else {
            System.out.println("Order Not Found!");
        }
    }
}
