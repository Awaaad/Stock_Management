package com.stock_management.dto.doctor;

import com.stock_management.dto.shared.PaginationDto;
import lombok.Data;

import java.util.List;

@Data
public class DoctorListDto extends PaginationDto {
    List<DoctorDto> doctorsDto;
}
