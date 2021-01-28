package com.stock_management.service.implementation;

import com.querydsl.core.BooleanBuilder;
import com.stock_management.dto.CustomerReceiptDto;
import com.stock_management.dto.MonthlySalesDto;
import com.stock_management.dto.OrderDto;
import com.stock_management.dto.OrderLineDto;
import com.stock_management.dto.OrderListDto;
import com.stock_management.entity.Order;
import com.stock_management.entity.QOrder;
import com.stock_management.entity.QProduct;
import com.stock_management.mapper.CustomerMapper;
import com.stock_management.mapper.DoctorMapper;
import com.stock_management.mapper.OrderMapper;
import com.stock_management.mapper.OrderLineMapper;
import com.stock_management.mapper.ReceiptMapper;
import com.stock_management.repository.CustomerRepository;
import com.stock_management.repository.DoctorRepository;
import com.stock_management.repository.OrderLineRepository;
import com.stock_management.repository.OrderRepository;
import com.stock_management.repository.ProductRepository;
import com.stock_management.service.OrderService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    private final OrderLineMapper orderLineMapper;
    private final ProductRepository productRepository;
    private final OrderLineRepository orderLineRepository;
    private final ReceiptMapper receiptMapper;
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public OrderServiceImplementation(OrderRepository orderRepository, OrderMapper orderMapper, OrderLineMapper orderLineMapper, ProductRepository productRepository, OrderLineRepository orderLineRepository, ReceiptMapper receiptMapper, DoctorRepository doctorRepository, DoctorMapper doctorMapper, CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderLineMapper = orderLineMapper;
        this.productRepository = productRepository;
        this.orderLineRepository = orderLineRepository;
        this.receiptMapper = receiptMapper;
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
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
    public OrderListDto findListOfOrdersByFilters(String customerName, Long userId, LocalDateTime orderDateTimeFrom, LocalDateTime orderDateTimeTo, Boolean paid, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.by("ASC".equals(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        BooleanBuilder predicate = buildProductPredicate(customerName, userId, orderDateTimeFrom, orderDateTimeTo, paid);
        Page<Order> orders = orderRepository.findAll(predicate,pageRequest);
        List<OrderDto> orderDtos = orders.stream().map(orderMapper::mapOrderEntityToDto).collect(Collectors.toList());

        List<OrderDto> list = new ArrayList<>();
        for (Order o : orders) {
            list.add(orderMapper.mapOrderEntityToDto(o));
        }

        var orderListDto = new OrderListDto();
        orderListDto.setOrdersDto(orderDtos);
        orderListDto.setTotalElements(orders.getNumberOfElements());
        orderListDto.setTotalPages(orders.getTotalPages());
        return orderListDto;
    }

    private BooleanBuilder buildProductPredicate(String customerName, Long userId, LocalDateTime orderDateTimeFrom, LocalDateTime orderDateTimeTo, Boolean paid) {
        var qOrder = QOrder.order;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(!customerName.equals("")) {
            booleanBuilder.and(qOrder.customer.firstName.concat(" ").concat(qOrder.customer.lastName).toLowerCase().contains(customerName.toLowerCase())).
                    or(qOrder.customer.lastName.concat(" ").concat(qOrder.customer.firstName).toLowerCase().contains(customerName.toLowerCase()));
        }
        if(Objects.nonNull(userId) && userId != 0) {
            booleanBuilder.and(qOrder.userProfile.userId.eq(userId));
        }
//        if(Objects.nonNull(orderDateTimeFrom)) {
//            booleanBuilder.and(qOrder.orderDate.after(orderDateTimeFrom));
//        }
//        if(Objects.nonNull(orderDateTimeTo)) {
//            booleanBuilder.and(qOrder.orderDate.before(orderDateTimeTo));
//        }
//        if(Objects.nonNull(paid)) {
//            booleanBuilder.and(qOrder.paid.eq(paid));
//        }
        return booleanBuilder;
    }

    @Override
    public CustomerReceiptDto findCustomerReceiptDetails(Long orderId) {
        var qOrder = QOrder.order;
//        var qOrderProduct = QOrderProduct.orderProduct;
        var qProduct = QProduct.product;

//        if (Objects.nonNull(orderId)) {
//            var customerReceipt = new JPAQuery<OrderProduct>(entityManager).select(
//                    qOrder.orderId.as("orderId"),
//                    qOrder.userProfile.firstName.as("cashierName"),
////                    qOrder.orderDate.as("orderDate"),
//                    qOrder.totalPrice.as("totalPrice"),
//                    qOrderProduct.product.productName.as("productName"),
//                    qOrderProduct.boxesOrdered.as("boxesOrdered"),
//                    qOrderProduct.unitsOrdered.as("unitsOrdered"),
//                    qOrderProduct.totalPrice.as("price"),
////                    qProduct.pricePerBox.as("pricePerBox"),
////                    qProduct.pricePerUnit.as("pricePerUnit"),
//                    qProduct.unitsPerBox.as("unitsPerBox"))
//                    .from(qOrderProduct)
//                    .leftJoin(qOrderProduct.order, qOrder)
//                    .leftJoin(qOrderProduct.product, qProduct)
//                    .where(qOrder.orderId.eq(orderId))
//                    .fetchResults().getResults();
//
//            return receiptMapper.mapCustomerReceiptDtoEntityToDto(customerReceipt);
//        } else {
//            return null;
//        }
        return null;
    }

    @Override
    public Double findEODSalesAmount(LocalDate dateTime) {
        var order = orderRepository.findAll();
//        return order.stream().filter(order1 -> dateTime.equals(order1.getOrderDate().toLocalDate())).mapToDouble(Order::getTotalPrice).sum();
        return null;
    }

    @Override
    public Double findMonthSalesAmount(LocalDate month, LocalDate year) {
        var order = orderRepository.findAll();
//        return order.stream().filter(order1 ->
//                month.getMonth().equals(order1.getOrderDate().toLocalDate().getMonth()) &&
//                        (year.getYear() == (order1.getOrderDate().toLocalDate().getYear())))
//                .mapToDouble(Order::getTotalPrice).sum();
        return null;
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
//        return order.stream().filter(order1 -> year.getYear() == order1.getOrderDate().getYear() && order1.getOrderDate().getMonthValue() == month).mapToDouble(Order::getTotalPrice).sum();
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderDto orderDto) throws Exception {
        List<OrderLineDto> orderLineDtos = new ArrayList<>();
//        for (OrderLineDto orderLineDto : orderDto.getOrderProductsDto()) {
//            if (!orderLineDto.getBoxesOrdered().equals(0) || !orderLineDto.getUnitsOrdered().equals(0)) {
//                orderLineDtos.add(orderLineDto);
//            }
//        }
//        orderDto.setOrderProductsDto(orderLineDtos);

        if (orderDto.getPrescription().equals(true)) {
            if (orderDto.getIsNewCustomer().equals(true)) {
                var savedCustomer = customerRepository.save(customerMapper.mapCustomerDtoToEntity(orderDto.getCustomerDto()));
                orderDto.setCustomerDto(customerMapper.mapCustomerEntityToDto(savedCustomer));
            } else if (Objects.nonNull(orderDto.getCustomerDto().getCustomerId())) {
                var existingCustomer = customerRepository.findById(orderDto.getCustomerDto().getCustomerId()).orElse(null);
                if (Objects.nonNull(existingCustomer)) {
                    orderDto.setCustomerDto((customerMapper.mapCustomerEntityToDto(existingCustomer)));
                }
            } else {
                throw new Exception("customer.not.provided");
            }
            if (orderDto.getIsNewDoctor().equals(true)) {
                var savedDoctor = doctorRepository.save(doctorMapper.mapDoctorDtoToEntity(orderDto.getDoctorDto()));
                orderDto.setDoctorDto(doctorMapper.mapDoctorEntityToDto(savedDoctor));
            } else if (Objects.nonNull(orderDto.getDoctorDto().getDoctorId())) {
               var existingDoctor = doctorRepository.findById(orderDto.getDoctorDto().getDoctorId()).orElse(null);
               if (Objects.nonNull(existingDoctor)) {
                   orderDto.setDoctorDto(doctorMapper.mapDoctorEntityToDto(existingDoctor));
               }
            } else {
                throw new Exception("doctor.not.provided");
            }
        } else {
            if (orderDto.getIsNewCustomer().equals(true)) {
                var savedCustomer = customerRepository.save(customerMapper.mapCustomerDtoToEntity(orderDto.getCustomerDto()));
                orderDto.setCustomerDto(customerMapper.mapCustomerEntityToDto(savedCustomer));
            } else if (!Objects.nonNull(orderDto.getCustomerDto().getCustomerId())) {
                var spontaneousCustomer = customerRepository.findCustomerByLastName("anonymous");
                orderDto.setCustomerDto(customerMapper.mapCustomerEntityToDto(spontaneousCustomer));
            }
            orderDto.setDoctorDto(null);
        }

        var order = orderMapper.mapOrderDtoToEntity(orderDto);
        var savedOrder = orderRepository.save(order);
//        orderLineRepository.saveAll(orderDto.getOrderProductsDto().stream().map(orderProductDto ->
//        {
//            try {
//                return mapOrderProduct(orderProductDto, savedOrder);
//            } catch (Exception e) {
//                System.out.println(e.toString());
//            }
//            return null;
//        }).collect(Collectors.toList()));
    }

//    private OrderLine mapOrderProduct(OrderLineDto orderLineDto, Order order) throws Exception {
//        var orderProduct = orderLineMapper.mapOrderProductDtoToEntity(orderLineDto);
//        orderProduct.setOrder(order);
//        var product = productRepository.findById(orderProductDto.getProductDto().getProductId());
//        if (product.isPresent()) {
//            var productEntity = product.get();
//            orderProduct.setProduct(productEntity);
//            var currentUnits = productEntity.getUnitsTotal();
//            if (order.getAmountPaid() > order.getTotalPrice()) {
//                throw new Exception("amount.paid.greater.than.total.price");
//            }
//            if (currentUnits - (orderProductDto.getUnitsOrdered()) < 0) {
//                throw new Exception("total.units.less.than.zero");
//            } else {
//                productEntity.setUnitsTotal(currentUnits - (orderProductDto.getUnitsOrdered()));
//                productEntity.setBox((double) (productEntity.getUnitsTotal()/productEntity.getUnitsPerBox()));
//            }
//        }
//        return orderProduct;
//    }

    @Override
    @Transactional
    public void editOrder(OrderDto orderDto) throws Exception {
        var order = findOrderById(orderDto.getOrderId());
        if (order != null) {
//            order.setPaid(true);
//            order.setAmountPaid(orderDto.getAmountPaid());
            orderRepository.save(orderMapper.mapOrderDtoToEntity(order));
        } else {
            throw new Exception("order.not.found");
        }
    }
}
