package com.stock_management.controller;

import com.stock_management.dto.*;
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

    @GetMapping("id")
    public ResponseEntity<SupplierDto>getSupplierById(@RequestParam Long supplierId){
        return new ResponseEntity<>(supplierService.findSupplierById(supplierId), HttpStatus.OK);
    }

    @GetMapping("supplier-name")
    public ResponseEntity<SupplierDto>getSupplierByName(@RequestParam String supplierName){
        return new ResponseEntity<>(supplierService.findSupplierByName(supplierName), HttpStatus.OK);
    }

    @GetMapping("orderId/{orderId}")
    public ResponseEntity<List<OrderDto>>getSupplierByOrderId(@PathVariable Long orderId){
        return new ResponseEntity<>(supplierService.findSupplierByOrderId(orderId), HttpStatus.ACCEPTED);
    }
    // POST GOES HERE
    @PostMapping("save-suppliers")
    public ResponseEntity<String> saveSuppliers(@RequestBody SupplierListDto supplierListDto){
        supplierService.saveSupplier(supplierListDto);
        return new ResponseEntity<String>("Suppliers saved successfully!", HttpStatus.OK);
    }

    // PUT GOES HERE
    @PutMapping("edit-supplier")
    public ResponseEntity<String> editSupplier(@RequestBody SupplierDto supplierDto){
        supplierService.editSupplier(supplierDto);
        return new ResponseEntity<String>("Supplier edited successfully!", HttpStatus.OK);
    }
}
