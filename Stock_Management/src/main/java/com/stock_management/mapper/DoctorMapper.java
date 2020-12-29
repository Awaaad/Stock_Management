package com.stock_management.mapper;

import com.stock_management.dto.DoctorDto;
import com.stock_management.entity.Doctor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    DoctorDto mapDoctorEntityToDto(Doctor doctor);
    @InheritInverseConfiguration
    Doctor mapDoctorDtoToEntity(DoctorDto doctorDto);
}
