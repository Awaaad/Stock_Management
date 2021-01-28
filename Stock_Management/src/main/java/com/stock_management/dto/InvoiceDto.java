package com.stock_management.dto;

import com.stock_management.entity.Customer;
import com.stock_management.entity.Doctor;
import com.stock_management.entity.OrderLine;
import com.stock_management.entity.PurchaseInvoiceLine;
import com.stock_management.entity.Supplier;
import com.stock_management.entity.UserProfile;
import com.stock_management.type.TransactionType;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class InvoiceDto {
    private Long invoiceId;
    private String invoiceNumber;
    private TransactionType transactionType;
    private List<PurchaseInvoiceLineDto> purchaseInvoiceLinesDto;
    private List<OrderLineDto> orderLinesDto;
    private Double totalPrice;
    private Boolean prescription;
    private CustomerDto customerDto;
    private DoctorDto doctorDto;
    private UserDto createdBy;
    private SupplierDto supplierDto;
    private ReceiptDto receiptDto;
    private Date createdDate;
}
