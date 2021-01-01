package com.stock_management.controller;

import com.stock_management.dto.DoctorDto;
import com.stock_management.dto.DoctorListDto;
import com.stock_management.dto.SupplierDto;
import com.stock_management.dto.SupplierListDto;
import com.stock_management.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping("filter")
    public ResponseEntity<DoctorListDto> getDoctorsViaFilter(@RequestParam String name, String sortOrder, @RequestParam String sortBy, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return new ResponseEntity<>(doctorService.findListOfDoctorByFilters(name, sortOrder, sortBy, pageNumber, pageSize), HttpStatus.OK);
    }

    @PostMapping("save-doctors")
    public ResponseEntity<String> saveDoctors(@RequestBody List<DoctorDto> doctorListDto){
        doctorService.saveDoctor(doctorListDto);
        return new ResponseEntity<String>("Doctors saved successfully!", HttpStatus.OK);
    }

    @PutMapping("edit-doctor")
    public ResponseEntity<String> editDoctor(@RequestBody DoctorDto doctorDto) throws Exception {
        doctorService.editDoctor(doctorDto);
        return new ResponseEntity<String>("Doctor edited successfully!", HttpStatus.OK);
    }
}
