package com.stock_management.service.implementation;

import com.querydsl.core.BooleanBuilder;
import com.stock_management.dto.DoctorDto;
import com.stock_management.dto.DoctorListDto;
import com.stock_management.entity.Doctor;
import com.stock_management.entity.QDoctor;
import com.stock_management.mapper.DoctorMapper;
import com.stock_management.repository.DoctorRepository;
import com.stock_management.repository.UserRepository;
import com.stock_management.service.DoctorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImplementation implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final UserRepository userRepository;

    public DoctorServiceImplementation(DoctorRepository doctorRepository, DoctorMapper doctorMapper, UserRepository userRepository) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
        this.userRepository = userRepository;
    }

    @Override
    public DoctorListDto findListOfDoctorByFilters(String name, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.by("ASC".equals(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        BooleanBuilder predicate = buildProductPredicate(name);
        Page<Doctor> doctors = doctorRepository.findAll(predicate,pageRequest);
        List<DoctorDto> doctorDtos = doctors.stream().map(doctorMapper::mapDoctorEntityToDto).collect(Collectors.toList());
        var doctorDtoList = new DoctorListDto();
        doctorDtoList.setDoctorDtos(doctorDtos);
        doctorDtoList.setTotalElements(doctors.getNumberOfElements());
        doctorDtoList.setTotalPages(doctors.getTotalPages());
        return doctorDtoList;
    }

    private BooleanBuilder buildProductPredicate(String name) {
        var qDoctor = QDoctor.doctor;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(!name.equals("")) {
            booleanBuilder.and(qDoctor.lastName.concat(" ").concat(qDoctor.firstName).toLowerCase().contains(name.toLowerCase()))
            .or(qDoctor.firstName.concat(" ").concat(qDoctor.lastName).toLowerCase().contains(name.toLowerCase()));
        }
        return booleanBuilder;
    }

    @Override
    @Transactional
    public void saveDoctor(List<DoctorDto> doctorDtoList) {
        doctorRepository.saveAll(doctorDtoList.stream().map(doctorMapper::mapDoctorDtoToEntity).collect(Collectors.toList()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editDoctor(DoctorDto doctorDto) throws Exception {
        var optionalDoctor = doctorRepository.findById(doctorDto.getDoctorId());
        var doctor = optionalDoctor.orElse(null);

        var optionalUser = userRepository.findById(doctorDto.getLastModifiedBy().getUserId());
        var user = optionalUser.orElse(null);
        if (Objects.nonNull(doctor)) {
            doctor.setFirstName(doctorDto.getFirstName());
            doctor.setLastName(doctorDto.getLastName());
            doctor.setAddress(doctorDto.getAddress());
            doctor.setTelephoneNumber(doctorDto.getTelephoneNumber());
            doctor.setLastModifiedBy(user);
            doctorRepository.save(doctor);
        } else {
            throw new Exception("doctor.not.found");
        }

    }
}
