package com.stock_management.service.implementation;

import com.querydsl.core.BooleanBuilder;
import com.stock_management.dto.customer.CustomerReceiptDto;
import com.stock_management.dto.order.MonthlySalesDto;
import com.stock_management.dto.order.OrderDto;
import com.stock_management.dto.order.OrderListDto;
import com.stock_management.dto.order.SaleStockUpdateDto;
import com.stock_management.dto.order.SaleTransactionDto;
import com.stock_management.dto.payment.PaymentDto;
import com.stock_management.entity.Invoice;
import com.stock_management.entity.Order;
import com.stock_management.entity.OrderLine;
import com.stock_management.entity.Payment;
import com.stock_management.entity.QOrder;
import com.stock_management.entity.QProduct;
import com.stock_management.entity.Receipt;
import com.stock_management.entity.Stock;
import com.stock_management.entity.UserProfile;
import com.stock_management.mapper.CustomerMapper;
import com.stock_management.mapper.DoctorMapper;
import com.stock_management.mapper.OrderMapper;
import com.stock_management.repository.CustomerRepository;
import com.stock_management.repository.DoctorRepository;
import com.stock_management.repository.InvoiceRepository;
import com.stock_management.repository.OrderLineRepository;
import com.stock_management.repository.OrderRepository;
import com.stock_management.repository.PaymentRepository;
import com.stock_management.repository.ReceiptRepository;
import com.stock_management.repository.StockRepository;
import com.stock_management.repository.UserRepository;
import com.stock_management.service.OrderService;
import com.stock_management.type.TransactionType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@EnableAutoConfiguration
@Service
public class OrderServiceImplementation implements OrderService {
    @PersistenceContext
    private final EntityManager entityManager;

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineRepository orderLineRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final UserRepository userRepository;
    private final InvoiceRepository invoiceRepository;
    private final StockRepository stockRepository;
    private final PaymentRepository paymentRepository;
    private final ReceiptRepository receiptRepository;

    public OrderServiceImplementation(OrderRepository orderRepository, EntityManager entityManager, OrderMapper orderMapper, OrderLineRepository orderLineRepository, DoctorRepository doctorRepository, DoctorMapper doctorMapper, CustomerRepository customerRepository, CustomerMapper customerMapper, UserRepository userRepository, InvoiceRepository invoiceRepository, StockRepository stockRepository, PaymentRepository paymentRepository, ReceiptRepository receiptRepository) {
        this.orderRepository = orderRepository;
        this.entityManager = entityManager;
        this.orderMapper = orderMapper;
        this.orderLineRepository = orderLineRepository;
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.userRepository = userRepository;
        this.invoiceRepository = invoiceRepository;
        this.stockRepository = stockRepository;
        this.paymentRepository = paymentRepository;
        this.receiptRepository = receiptRepository;
    }

    // GET
    @Override
    public List<OrderDto> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::mapOrderEntityToDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto findOrderById(Long OrderId) {
        Optional<Order> order = orderRepository.findById(OrderId);
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
            booleanBuilder.and(qOrder.createdBy.userId.eq(userId));
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
    public void saveSaleTransaction(SaleTransactionDto saleTransactionDto) throws Exception {

        var user = userRepository.findById(saleTransactionDto.getUserId()).orElse(null);
        var filteredStocks = saleTransactionDto.getSaleStockUpdatesDto().stream().filter(stockDto -> (Objects.nonNull(stockDto.getBoxesOrdered()) && stockDto.getBoxesOrdered() > 0) || Objects.nonNull(stockDto.getUnitsOrdered()) && stockDto.getUnitsOrdered() > 0).collect(Collectors.toList());

        BigDecimal totalPrice = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        for (SaleStockUpdateDto saleStockUpdateDto : filteredStocks) {
            totalPrice = totalPrice.add(saleStockUpdateDto.getTotal());
        }

        var savedOrder = saveOrderFromST(saleTransactionDto, totalPrice, user);

        var savedInvoice = saveInvoiceFromtST(savedOrder, saleTransactionDto.getSoldAt());
        var savedReceipt = saveReceipt(savedInvoice, savedOrder, user);

        savePayments(saleTransactionDto.getPaymentsDto(), savedInvoice, savedReceipt);
        saveOrderLines(filteredStocks, savedOrder);
    }

    private Order saveOrderFromST(SaleTransactionDto saleTransactionDto, BigDecimal totalPrice, UserProfile user) throws Exception {
        Order order = new Order();
        if (saleTransactionDto.getIsPrescription().equals(true)) {
            order.setPrescription(true);
            if (saleTransactionDto.getIsNewCustomer().equals(true)) {
                var savedCustomer = customerRepository.save(customerMapper.mapCustomerDtoToEntity(saleTransactionDto.getCustomerDto()));
                order.setCustomer(savedCustomer);
            } else if (Objects.nonNull(saleTransactionDto.getCustomerDto().getCustomerId())) {
                var existingCustomer = customerRepository.findById(saleTransactionDto.getCustomerDto().getCustomerId()).orElse(null);
                if (Objects.nonNull(existingCustomer)) {
                    order.setCustomer(existingCustomer);
                }
            } else {
                throw new Exception("customer.not.provided");
            }
            if (saleTransactionDto.getIsNewDoctor().equals(true)) {
                var savedDoctor = doctorRepository.save(doctorMapper.mapDoctorDtoToEntity(saleTransactionDto.getDoctorDto()));
                order.setDoctor(savedDoctor);
            } else if (Objects.nonNull(saleTransactionDto.getDoctorDto().getDoctorId())) {
                var existingDoctor = doctorRepository.findById(saleTransactionDto.getDoctorDto().getDoctorId()).orElse(null);
                if (Objects.nonNull(existingDoctor)) {
                    order.setDoctor(existingDoctor);
                }
            } else {
                throw new Exception("doctor.not.provided");
            }
        } else {
            order.setPrescription(false);
            if (saleTransactionDto.getIsNewCustomer().equals(true)) {
                var savedCustomer = customerRepository.save(customerMapper.mapCustomerDtoToEntity(saleTransactionDto.getCustomerDto()));
                order.setCustomer(savedCustomer);
            } else if (!Objects.nonNull(saleTransactionDto.getCustomerDto().getCustomerId())) {
                var spontaneousCustomer = customerRepository.findCustomerByLastName("anonymous");
                order.setCustomer(spontaneousCustomer);
            } else {
                order.setCustomer(customerMapper.mapCustomerDtoToEntity(saleTransactionDto.getCustomerDto()));
            }
            saleTransactionDto.setDoctorDto(null);
        }

        order.setCreatedBy(user);
        order.setTotalPrice(totalPrice);
        order.setCreatedDate(LocalDateTime.now());

        return orderRepository.save(order);
    }

    private Invoice saveInvoiceFromtST(Order order, BigDecimal soldAt) {
        Invoice invoice = new Invoice();
        invoice.setOrder(order);
        invoice.setCustomer(order.getCustomer());
        invoice.setCreatedBy(order.getCreatedBy());
        invoice.setCreatedDate(LocalDateTime.now());
        invoice.setInvoiceNumber("TEST123");
        invoice.setTransactionType(TransactionType.SALE);
        invoice.setTotalPrice(order.getTotalPrice());
        invoice.setPrescription(order.getPrescription());
        invoice.setDiscount((((order.getTotalPrice().subtract(soldAt)).divide(order.getTotalPrice(), 5, RoundingMode.HALF_UP)).multiply(new BigDecimal("100"))));

        return invoiceRepository.save(invoice);
    }

    private Stock setStockForST(SaleStockUpdateDto saleStockUpdateDto) throws Exception {
        var stock = stockRepository.findById(saleStockUpdateDto.getStockId()).orElse(null);
        if (Objects.nonNull(stock)) {
            var totalUnitsOrdered = ((saleStockUpdateDto.getBoxesOrdered() * stock.getUnitsPerBox()) + saleStockUpdateDto.getUnitsOrdered());
            var currentUnits = stock.getUnitsTotal();
            if ((currentUnits - totalUnitsOrdered) < 0) {
                throw new Exception("total.units.less.than.zero");
            } else {
                stock.setUnitsTotal(currentUnits - (totalUnitsOrdered));
                stock.setQuantity(new BigDecimal (stock.getUnitsTotal()/stock.getUnitsPerBox()));
            }
        }
        return stock;
    }

    private void savePayments(List<PaymentDto> paymentsDto, Invoice invoice, Receipt receipt) {
        var payments = paymentsDto.stream().map(paymentDto -> {
            Payment payment = new Payment();
            payment.setInvoice(invoice);
            payment.setReceipt(receipt);
            payment.setAmountPaid(paymentDto.getAmountPaid());
            payment.setPaymentMode(paymentDto.getPaymentMode());
            payment.setPreviousPaymentMode(paymentDto.getPaymentMode());
            payment.setCreatedBy(userRepository.findById(paymentDto.getCreatedBy().getUserId()).orElse(null));
            payment.setCreatedDate(LocalDateTime.now());
            payment.setLastModifiedBy(userRepository.findById(paymentDto.getCreatedBy().getUserId()).orElse(null));
            payment.setLastModifiedDate(LocalDateTime.now());
            return payment;
        }).collect(Collectors.toList());

        paymentRepository.saveAll(payments);
    }

    private void saveOrderLines(List<SaleStockUpdateDto> saleStockUpdatesDto, Order order) {
        var orderLines = saleStockUpdatesDto.stream().map(stock -> {
            OrderLine orderLine = new OrderLine();
            orderLine.setOrder(order);
            try {
                orderLine.setStock(setStockForST(stock));
            } catch (Exception e) {
                e.printStackTrace();
            }
            orderLine.setBoxesOrdered(stock.getBoxesOrdered());
            orderLine.setUnitsOrdered(stock.getUnitsOrdered());
            orderLine.setTotalPrice(stock.getTotal());
            return orderLine;
        }).collect(Collectors.toList());

        orderLineRepository.saveAll(orderLines);
    }

    private Receipt saveReceipt(Invoice invoice, Order order, UserProfile user) {
        Receipt receipt = new Receipt();
        receipt.setInvoice(invoice);
        receipt.setTotalPrice(invoice.getTotalPrice());
        receipt.setDiscount(invoice.getDiscount());
        receipt.setCreatedBy(user);
        receipt.setCreatedDate(LocalDateTime.now());
        receipt.setCustomer(order.getCustomer());
        receipt.setDoctor(order.getDoctor());
        return receiptRepository.save(receipt);
    }

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
