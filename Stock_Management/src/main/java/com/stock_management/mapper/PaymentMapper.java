package com.stock_management.mapper;

import com.stock_management.dto.payment.PaymentDto;
import com.stock_management.entity.Payment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(target = "receiptDto", source = "receipt")
    @Mapping(target = "invoiceDto", source = "invoice")
    PaymentDto mapPaymentEntityToDto(Payment payment);
    @InheritInverseConfiguration
    Payment mapPaymentDtoToEntity(PaymentDto paymentDto);
}
