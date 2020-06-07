package com.stock_management.controller;

import com.stock_management.dto.ProductDto;
import com.stock_management.dto.ProductListDto;
import com.stock_management.dto.SupplierDto;
import com.stock_management.dto.SupplierListDto;
import com.stock_management.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // GET
    @GetMapping("all")
    public ResponseEntity<List<SupplierDto>> getAllSuppliers(){
        return new ResponseEntity<>(supplierService.findAllSuppliers(), HttpStatus.OK);
    }

    @GetMapping("filter")
    public ResponseEntity<SupplierListDto> getSuppliersViaFilter(@RequestParam String supplierName, String sortOrder, @RequestParam String sortBy, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return new ResponseEntity<>(supplierService.findListOfSuppliersByFilters(supplierName, sortOrder, sortBy, pageNumber, pageSize), HttpStatus.OK);
    }

    // POST GOES HERE
    @PostMapping("/saveSuppliers")
    public ResponseEntity saveSuppliers(@RequestBody SupplierListDto supplierListDto){
        supplierService.saveSupplier(supplierListDto);
        return new ResponseEntity<String>("Suppliers saved successfully!", HttpStatus.OK);
    }
}
