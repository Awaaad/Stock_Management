package com.stock_management.service.implementation;

import com.querydsl.core.BooleanBuilder;
import com.stock_management.dto.invoice.InvoiceDto;
import com.stock_management.dto.invoice.PurchaseInvoiceListDto;
import com.stock_management.dto.invoice.SavePurchaseInvoiceStockDto;
import com.stock_management.dto.payment.PaymentDto;
import com.stock_management.entity.Invoice;
import com.stock_management.entity.Payment;
import com.stock_management.entity.PurchaseInvoiceLine;
import com.stock_management.entity.QInvoice;
import com.stock_management.entity.Receipt;
import com.stock_management.mapper.CustomerMapper;
import com.stock_management.mapper.DoctorMapper;
import com.stock_management.mapper.InvoiceMapper;
import com.stock_management.mapper.OrderLineMapper;
import com.stock_management.mapper.OrderMapper;
import com.stock_management.mapper.PaymentMapper;
import com.stock_management.mapper.PurchaseInvoiceLineMapper;
import com.stock_management.mapper.SupplierMapper;
import com.stock_management.mapper.UserMapper;
import com.stock_management.repository.InvoiceRepository;
import com.stock_management.repository.PaymentRepository;
import com.stock_management.repository.PurchaseInvoiceLineRepository;
import com.stock_management.repository.ReceiptRepository;
import com.stock_management.repository.SupplierRepository;
import com.stock_management.repository.UserRepository;
import com.stock_management.service.InvoiceService;
import com.stock_management.service.PaymentService;
import com.stock_management.service.StockService;
import com.stock_management.type.PaymentType;
import com.stock_management.type.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImplementation implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final StockService stockService;
    private final UserRepository userRepository;
    private final PurchaseInvoiceLineRepository purchaseInvoiceLineRepository;
    private final SupplierRepository supplierRepository;
    private final PaymentRepository paymentRepository;

    private final PurchaseInvoiceLineMapper purchaseInvoiceLineMapper;
    private final OrderLineMapper orderLineMapper;
    private final CustomerMapper customerMapper;
    private final DoctorMapper doctorMapper;
    private final SupplierMapper supplierMapper;
    private final OrderMapper orderMapper;
    private final UserMapper userMapper;
    private final InvoiceMapper invoiceMapper;
    private final PaymentMapper paymentMapper;
    private final ReceiptRepository receiptRepository;
    private final PaymentService paymentService;

    public InvoiceServiceImplementation(InvoiceRepository invoiceRepository, StockService stockService, UserRepository userRepository, PurchaseInvoiceLineRepository purchaseInvoiceLineRepository, SupplierRepository supplierRepository, InvoiceMapper invoiceMapper, PaymentRepository paymentRepository, PurchaseInvoiceLineMapper purchaseInvoiceLineMapper, OrderLineMapper orderLineMapper, CustomerMapper customerMapper, DoctorMapper doctorMapper, SupplierMapper supplierMapper, OrderMapper orderMapper, UserMapper userMapper, PaymentMapper paymentMapper, ReceiptRepository receiptRepository, PaymentService paymentService) {
        this.invoiceRepository = invoiceRepository;
        this.stockService = stockService;
        this.userRepository = userRepository;
        this.purchaseInvoiceLineRepository = purchaseInvoiceLineRepository;
        this.supplierRepository = supplierRepository;
        this.invoiceMapper = invoiceMapper;
        this.paymentRepository = paymentRepository;
        this.purchaseInvoiceLineMapper = purchaseInvoiceLineMapper;
        this.orderLineMapper = orderLineMapper;
        this.customerMapper = customerMapper;
        this.doctorMapper = doctorMapper;
        this.supplierMapper = supplierMapper;
        this.orderMapper = orderMapper;
        this.userMapper = userMapper;
        this.paymentMapper = paymentMapper;
        this.receiptRepository = receiptRepository;
        this.paymentService = paymentService;
    }

    @Override
    public void savePurchaseInvoice(SavePurchaseInvoiceStockDto savePurchaseInvoiceStockDto) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(savePurchaseInvoiceStockDto.getInvoiceNumber());
        invoice.setTransactionType(TransactionType.PURCHASE);
        invoice.setTotalPrice(savePurchaseInvoiceStockDto.getTotalPrice());
        invoice.setSupplier(supplierRepository.findById(savePurchaseInvoiceStockDto.getSupplierId()).orElse(null));
        invoice.setCreatedBy(userRepository.findById(savePurchaseInvoiceStockDto.getUserId()).orElse(null));
        invoice.setCreatedDate(LocalDateTime.now());
        var savedInvoice = invoiceRepository.save(invoice);

        var savedStocks = stockService.saveStock(savePurchaseInvoiceStockDto.getStocksDto());
        var setPurchaseInvoiceLines = savedStocks.stream().map(stock -> {
            PurchaseInvoiceLine purchaseInvoiceLine = new PurchaseInvoiceLine();
            purchaseInvoiceLine.setInvoice(savedInvoice);
            purchaseInvoiceLine.setStock(stock);
            purchaseInvoiceLine.setBoxesReceived(stock.getQuantity());
            purchaseInvoiceLine.setWholeSalePrice(stock.getWholeSalePrice());
            purchaseInvoiceLine.setPricePerBox(stock.getPricePerBox());
            return purchaseInvoiceLine;
        }).collect(Collectors.toList());

        purchaseInvoiceLineRepository.saveAll(setPurchaseInvoiceLines);
    }

    @Override
    public InvoiceDto findPurchaseInvoiceById(Long invoiceId) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);
        var invoice = optionalInvoice.orElse(null);
        return invoiceMapper.mapInvoiceEntityToDto(invoice);
    }

    @Override
    public PurchaseInvoiceListDto findPurchaseInvoiceByFilters(TransactionType transactionType, PaymentType paymentType, String searchBox, Long userId, Long customerId, LocalDateTime invoiceDateFrom, LocalDateTime invoiceDateTo, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize) {

        Sort sort = Sort.by("ASC".equals(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        BigDecimal amountDueSum = BigDecimal.ZERO;
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        BooleanBuilder predicate = buildProductPredicate(transactionType, searchBox, userId, customerId, invoiceDateFrom, invoiceDateTo);
        Page<Invoice> invoices = invoiceRepository.findAll(predicate, pageRequest);
        List<InvoiceDto> invoicesDto = invoices.stream().map(this::mapInvoiceEntityToDto).collect(Collectors.toList());
        if (Objects.nonNull(paymentType)) {
            invoicesDto = invoicesDto.stream().filter(invoiceDto -> invoiceDto.getPaymentsDto().stream().anyMatch(paymentDto -> paymentDto.getPaymentMode().equals(paymentType))).collect(Collectors.toList());
            for (InvoiceDto invoiceDto : invoicesDto) {
                for (PaymentDto paymentDto : invoiceDto.getPaymentsDto()) {
                    if (paymentDto.getPaymentMode().equals(PaymentType.CREDIT)) {
                        amountDueSum = amountDueSum.add(paymentDto.getAmountPaid());
                    }
                }
            }
        } else {
            invoicesDto = invoices.stream().map(this::mapInvoiceEntityToDto).collect(Collectors.toList());
        }

        var purchaseInvoiceListDto = new PurchaseInvoiceListDto();
        purchaseInvoiceListDto.setPurchaseInvoicesDto(invoicesDto);
        purchaseInvoiceListDto.setTotalAmountDue(amountDueSum);
        purchaseInvoiceListDto.setTotalElements(invoices.getNumberOfElements());
        purchaseInvoiceListDto.setTotalPages(invoices.getTotalPages());
        return purchaseInvoiceListDto;
    }

    @Override
    public void bulkPayment(List<InvoiceDto> invoicesDto) throws Exception {
        var invoices = invoicesDto.stream().map(invoiceDto ->{
            invoiceDto.getPaymentsDto().forEach(paymentDto -> {
                var payment = paymentRepository.findById(paymentDto.getPaymentId()).orElse(null);
                var user = userRepository.findById(paymentDto.getLastModifiedBy().getUserId()).orElse(null);
                if (Objects.nonNull(payment)) {
                    payment.setPreviousPaymentMode(payment.getPaymentMode());
                    payment.setPaymentMode(paymentDto.getPaymentMode());
                    payment.setLastModifiedBy(user);
                    payment.setLastModifiedDate(LocalDateTime.now());
                    paymentRepository.save(payment);
                } else {
                    try {
                        throw new Exception("payment.not.found");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            Optional<Receipt> optionalReceipt = Optional.ofNullable(receiptRepository.findByInvoice_InvoiceId(invoiceDto.getInvoiceId()));
            var receipt = optionalReceipt.orElse(null);
            if (Objects.nonNull(receipt.getDiscount())) {
                receipt.setDiscount(receipt.getDiscount().add(invoiceDto.getDiscountOnTotalCredit()));
            } else {
                receipt.setDiscount(invoiceDto.getDiscountOnTotalCredit());
            }
            receiptRepository.save(receipt);

            return invoiceRepository.findById(invoiceDto.getInvoiceId())
                   .map(invoice -> {
                       if (Objects.nonNull(invoice.getDiscount())) {
                           invoice.setDiscount(invoice.getDiscount().add(invoiceDto.getDiscountOnTotalCredit()));
                       } else {
                           invoice.setDiscount(invoiceDto.getDiscountOnTotalCredit());
                       }
                       return invoice;
                   }).orElse(null);
                }
        ).collect(Collectors.toList());
        invoiceRepository.saveAll(invoices);
    }

    private InvoiceDto mapInvoiceEntityToDto(Invoice invoice) {
        InvoiceDto invoiceDto = new InvoiceDto();
        PaymentDto paymentDto = new PaymentDto();
        invoiceDto.setInvoiceId(invoice.getInvoiceId());
        invoiceDto.setIsChecked(true);
        invoiceDto.setInvoiceNumber(invoice.getInvoiceNumber());
        invoiceDto.setTransactionType(invoice.getTransactionType());
        invoiceDto.setInvoiceId(invoice.getInvoiceId());
        invoiceDto.setPurchaseInvoiceLinesDto(invoice.getPurchaseInvoiceLines().stream().map(purchaseInvoiceLineMapper::mapPurchaseInvoiceLineEntityToDto).collect(Collectors.toList()));
        invoiceDto.setOrderLinesDto(invoice.getOrderLines().stream().map(orderLineMapper::mapOrderLineEntityToDto).collect(Collectors.toList()));
        invoiceDto.setTotalPrice(invoice.getTotalPrice().setScale(2, RoundingMode.HALF_UP));
        if (Objects.nonNull(invoice.getDiscount())) {
            invoiceDto.setDiscount(invoice.getDiscount().setScale(5, RoundingMode.HALF_UP));
        }
        if (Objects.nonNull(invoice.getPrescription())) {
            invoiceDto.setPrescription(invoice.getPrescription());
        }
        if (Objects.nonNull(invoice.getOrder())) {
            invoiceDto.setOrderDto(orderMapper.mapOrderEntityToDto(invoice.getOrder()));
        }
        if (Objects.nonNull(invoice.getCustomer())) {
            invoiceDto.setCustomerDto(customerMapper.mapCustomerEntityToDto(invoice.getCustomer()));
        }
        if (Objects.nonNull(invoice.getDoctor())) {
            invoiceDto.setDoctorDto(doctorMapper.mapDoctorEntityToDto(invoice.getDoctor()));
        }
        if (Objects.nonNull(invoice.getSupplier())) {
            invoiceDto.setSupplierDto(supplierMapper.mapSupplierEntityToDto(invoice.getSupplier()));
        }
        invoiceDto.setCreatedBy(userMapper.mapUserEntityToDto(invoice.getCreatedBy()));
        invoiceDto.setCreatedDate(invoice.getCreatedDate());

        invoiceDto.setPaymentsDto(paymentRepository.findByInvoice_InvoiceId(invoice.getInvoiceId()).stream().map(paymentMapper::mapPaymentEntityToDto).collect(Collectors.toList()));

        if (invoice.getTransactionType().equals(TransactionType.SALE)) {
            var payments = paymentRepository.findByInvoice_InvoiceId(invoice.getInvoiceId());

            BigDecimal amountPaidSum = BigDecimal.ZERO;
            BigDecimal amountDueSum = BigDecimal.ZERO;
            for (Payment payment : payments) {
                if (!payment.getPaymentMode().equals(PaymentType.CREDIT)) {
                    amountPaidSum = amountPaidSum.add(payment.getAmountPaid());
                } else if (payment.getPaymentMode().equals(PaymentType.CREDIT)) {
                    amountDueSum = amountDueSum.add(payment.getAmountPaid());
                }
            }
            var totalAmountPaid = amountPaidSum.setScale(2, RoundingMode.HALF_UP);
            var totalAmountDue = amountDueSum.setScale(2, RoundingMode.HALF_UP);
//            var totalAmountPaid = payments.stream().mapToDouble(Payment::getAmountPaid).sum();
            var totalPriceAfterDiscount = invoice.getTotalPrice().subtract(invoice.getDiscount()).setScale(2, RoundingMode.HALF_UP);
            invoiceDto.setAmountPaid(totalAmountPaid);
            invoiceDto.setAmountDue(totalAmountDue);
            invoiceDto.setPaid(totalAmountPaid.equals(totalPriceAfterDiscount));
        } else {
            invoiceDto.setPaid(false);
        }
        return invoiceDto;
    }

    private BooleanBuilder buildProductPredicate(TransactionType transactionType, String searchBox, Long userId, Long customerId, LocalDateTime invoiceDateFrom, LocalDateTime invoiceDateTo) {
        var qInvoice = QInvoice.invoice;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (Objects.nonNull(transactionType) && transactionType.equals(TransactionType.PURCHASE)) {
            booleanBuilder.and(qInvoice.transactionType.eq(TransactionType.PURCHASE));
            if (!searchBox.equals("")) {
                booleanBuilder.and(qInvoice.supplier.supplierName.toLowerCase().contains(searchBox.toLowerCase()))
                        .or(qInvoice.invoiceNumber.toLowerCase().contains(searchBox.toLowerCase()));
            }
        } else if (Objects.nonNull(transactionType) && transactionType.equals(TransactionType.SALE)) {
            booleanBuilder.and(qInvoice.transactionType.eq(TransactionType.SALE));
            if (!searchBox.equals("")) {
                booleanBuilder.and(qInvoice.customer.firstName.concat(" ").concat(qInvoice.customer.lastName).toLowerCase().contains(searchBox.toLowerCase())).
                        or(qInvoice.customer.lastName.concat(" ").concat(qInvoice.customer.firstName).toLowerCase().contains(searchBox.toLowerCase()));
            }
            if (Objects.nonNull(customerId) && customerId != 0) {
                booleanBuilder.and((qInvoice.customer.customerId.eq(customerId)));
            }
        }
        if (Objects.nonNull(userId) && userId != 0) {
            booleanBuilder.and(qInvoice.createdBy.userId.eq(userId));
        }
        if (Objects.nonNull(invoiceDateFrom)) {
            booleanBuilder.and(qInvoice.createdDate.after(invoiceDateFrom));
        }
        if (Objects.nonNull(invoiceDateTo)) {
            booleanBuilder.and(qInvoice.createdDate.before(invoiceDateTo));
        }
        return booleanBuilder;
    }

}
