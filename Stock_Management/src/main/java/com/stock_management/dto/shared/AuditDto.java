package com.stock_management.dto.shared;

import com.stock_management.dto.security.UserDto;
import lombok.Data;

import java.util.Date;

@Data
public class AuditDto {
    private UserDto createdBy;
    private Date createdDate;
    private UserDto lastModifiedBy;
    private Date lastModifiedDate;
}
