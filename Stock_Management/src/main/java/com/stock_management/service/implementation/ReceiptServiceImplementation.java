package com.stock_management.service.implementation;

import com.stock_management.dto.order.SaleStockUpdateDto;
import com.stock_management.dto.receipt.CustomReceiptDto;
import com.stock_management.entity.Receipt;
import com.stock_management.mapper.CustomerMapper;
import com.stock_management.mapper.DoctorMapper;
import com.stock_management.mapper.PaymentMapper;
import com.stock_management.mapper.ProductMapper;
import com.stock_management.mapper.ReceiptMapper;
import com.stock_management.mapper.UserMapper;
import com.stock_management.repository.PaymentRepository;
import com.stock_management.repository.ReceiptRepository;
import com.stock_management.service.ReceiptService;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceiptServiceImplementation implements ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final PaymentRepository paymentRepository;
    private final ReceiptMapper receiptMapper;
    private final PaymentMapper paymentMapper;
    private final CustomerMapper customerMapper;
    private final DoctorMapper doctorMapper;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;

    public ReceiptServiceImplementation(ReceiptRepository receiptRepository, PaymentRepository paymentRepository, ReceiptMapper receiptMapper, PaymentMapper paymentMapper, CustomerMapper customerMapper, DoctorMapper doctorMapper, UserMapper userMapper, ProductMapper productMapper) {
        this.receiptRepository = receiptRepository;
        this.paymentRepository = paymentRepository;
        this.receiptMapper = receiptMapper;
        this.paymentMapper = paymentMapper;
        this.customerMapper = customerMapper;
        this.doctorMapper = doctorMapper;
        this.userMapper = userMapper;
        this.productMapper = productMapper;
    }

    @Override
    public CustomReceiptDto findReceiptByInvoiceId(Long invoiceId) {
        Optional<Receipt> optionalReceipt = Optional.ofNullable(receiptRepository.findByInvoice_InvoiceId(invoiceId));
        var receipt = optionalReceipt.orElse(null);

        CustomReceiptDto customReceiptDto = new CustomReceiptDto();

        customReceiptDto.setReceiptId(receipt.getReceiptId());
        customReceiptDto.setTotalPrice(receipt.getTotalPrice());
        customReceiptDto.setDiscount(receipt.getDiscount().setScale(2, RoundingMode.HALF_UP));
        customReceiptDto.setPaymentsDto(paymentRepository.findByInvoice_InvoiceId(invoiceId).stream().map(paymentMapper::mapPaymentEntityToDto).collect(Collectors.toList()));
        customReceiptDto.setCustomerDto(customerMapper.mapCustomerEntityToDto(receipt.getCustomer()));
        if (Objects.nonNull(receipt.getDoctor())) {
            customReceiptDto.setDoctorDto(doctorMapper.mapDoctorEntityToDto(receipt.getDoctor()));
        }
        customReceiptDto.setCreatedBy(userMapper.mapUserEntityToDto(receipt.getCreatedBy()));
        customReceiptDto.setCreatedDate(receipt.getCreatedDate());
        var saleStockUpdate = receipt.getInvoice().getOrder().getOrderLines().stream().map(orderLine -> {
            SaleStockUpdateDto saleStockUpdateDto = new SaleStockUpdateDto();
            saleStockUpdateDto.setStockId(orderLine.getStock().getStockId());
            saleStockUpdateDto.setProductDto(productMapper.mapProductEntityToDto(orderLine.getStock().getProduct()));
            saleStockUpdateDto.setUnitsPerBox(orderLine.getStock().getUnitsPerBox());
            saleStockUpdateDto.setWholeSalePrice(orderLine.getStock().getWholeSalePrice());
            saleStockUpdateDto.setPricePerBox(orderLine.getStock().getPricePerBox());
            saleStockUpdateDto.setPricePerUnit(orderLine.getStock().getPricePerUnit());
            saleStockUpdateDto.setExpiryDate(orderLine.getStock().getExpiryDate());
            saleStockUpdateDto.setBoxesOrdered(orderLine.getBoxesOrdered());
            saleStockUpdateDto.setUnitsOrdered(orderLine.getUnitsOrdered());
            saleStockUpdateDto.setTotal(orderLine.getTotalPrice());
            return saleStockUpdateDto;
        }).collect(Collectors.toList());
        customReceiptDto.setSaleStockUpdatesDto(saleStockUpdate);
        return customReceiptDto;

    }
}
