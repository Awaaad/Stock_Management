package com.stock_management.dto;

import lombok.Data;

import java.util.List;

@Data
public class DoctorListDto extends PaginationDto {
    List<DoctorDto> doctorDtos;
}
