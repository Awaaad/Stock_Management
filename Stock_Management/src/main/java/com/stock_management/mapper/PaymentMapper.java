package com.stock_management.mapper;

import com.stock_management.dto.PaymentDto;
import com.stock_management.entity.Payment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentDto mapPaymentEntityToDto(Payment payment);
    @InheritInverseConfiguration
    Payment mapPaymentDtoToEntity(PaymentDto paymentDto);
}
