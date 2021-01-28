package com.stock_management.mapper;

import com.stock_management.dto.payment.PaymentDto;
import com.stock_management.entity.Payment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentDto mapPaymentEntityToDto(Payment payment);
    @InheritInverseConfiguration
    Payment mapPaymentDtoToEntity(PaymentDto paymentDto);
}
