package com.stock_management.dto.payment;

import com.stock_management.entity.Invoice;
import com.stock_management.type.PaymentType;
import lombok.Data;

import java.util.Date;

@Data
public class PaymentDto {
    private Long paymentId;
    private Invoice invoice;
    private Double amountPaid;
    private Double discount;
    private PaymentType paymentMode;
    private Date createdDate;
}
