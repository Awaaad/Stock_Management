package com.stock_management.dto.shared;

import com.stock_management.dto.security.UserDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AuditDto {
    private UserDto createdBy;
    private LocalDateTime createdDate;
    private UserDto lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}
