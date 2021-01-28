package com.stock_management.dto.receipt;

import com.stock_management.entity.Customer;
import com.stock_management.entity.Doctor;
import com.stock_management.entity.UserProfile;
import lombok.Data;
import java.util.Date;

@Data
public class ReceiptDto {
    private Long receiptId;
    private Double pricePerBox;
    private Double pricePerUnit;
    private Integer boxesOrdered;
    private Integer unitsOrdered;
    private Double totalPrice;
    private Double discount;
    private Customer customer;
    private Doctor doctor;
    private UserProfile createdBy;
    private Date createdDate;
}
