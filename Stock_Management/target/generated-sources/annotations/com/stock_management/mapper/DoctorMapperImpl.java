package com.stock_management.mapper;

import com.stock_management.dto.DoctorDto;
import com.stock_management.entity.Doctor;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-03T23:00:33+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.6 (AdoptOpenJDK)"
)
@Component
public class DoctorMapperImpl implements DoctorMapper {

    @Override
    public DoctorDto mapDoctorEntityToDto(Doctor doctor) {
        if ( doctor == null ) {
            return null;
        }

        DoctorDto doctorDto = new DoctorDto();

        doctorDto.setDoctorId( doctor.getDoctorId() );
        doctorDto.setFirstName( doctor.getFirstName() );
        doctorDto.setLastName( doctor.getLastName() );
        doctorDto.setAddress( doctor.getAddress() );
        doctorDto.setTelephoneNumber( doctor.getTelephoneNumber() );

        return doctorDto;
    }

    @Override
    public Doctor mapDoctorDtoToEntity(DoctorDto doctorDto) {
        if ( doctorDto == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setDoctorId( doctorDto.getDoctorId() );
        doctor.setFirstName( doctorDto.getFirstName() );
        doctor.setLastName( doctorDto.getLastName() );
        doctor.setAddress( doctorDto.getAddress() );
        doctor.setTelephoneNumber( doctorDto.getTelephoneNumber() );

        return doctor;
    }
}
