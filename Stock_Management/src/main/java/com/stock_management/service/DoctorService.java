package com.stock_management.service;

import com.stock_management.dto.doctor.DoctorDto;
import com.stock_management.dto.doctor.DoctorListDto;

import java.util.List;

public interface DoctorService {
    DoctorListDto findListOfDoctorByFilters(String name, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize);

    void saveDoctor(List<DoctorDto> doctorDtoList);

    void editDoctor(DoctorDto doctorDto) throws Exception;
}
