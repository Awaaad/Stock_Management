package com.stock_management.controller;

import com.stock_management.dto.OrderDto;
import com.stock_management.dto.OrderListDto;
import com.stock_management.dto.ProductDto;
import com.stock_management.dto.PurchaseInvoiceDto;
import com.stock_management.dto.PurchaseInvoiceListDto;
import com.stock_management.service.PurchaseInvoiceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/purchase-invoice")
public class PurchaseInvoiceController {
    private final PurchaseInvoiceService purchaseInvoiceService;

    public PurchaseInvoiceController(PurchaseInvoiceService purchaseInvoiceService) {
        this.purchaseInvoiceService = purchaseInvoiceService;
    }

    // GET GOES HERE
    @GetMapping("all")
    public ResponseEntity<List<PurchaseInvoiceDto>>getAllPurchaseInvoice(){
        return new ResponseEntity<>(purchaseInvoiceService.findAllPurchaseInvoice(), HttpStatus.OK);
    }

    @GetMapping("filter")
    public ResponseEntity<PurchaseInvoiceListDto> getPurchaseInvoicesViaFilter(@RequestParam String searchBox, @RequestParam("invoiceDateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime invoiceDateFrom, @RequestParam("invoiceDateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime invoiceDateTo, @RequestParam String sortOrder, @RequestParam String sortBy, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return new ResponseEntity<>(purchaseInvoiceService.findPurchaseInvoiceByFilters(searchBox, invoiceDateFrom, invoiceDateTo, sortOrder, sortBy, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("purchase-invoice-id/{purchaseInvoiceId}")
    public ResponseEntity<PurchaseInvoiceDto> getPurchaseInvoiceById(@PathVariable Long purchaseInvoiceId){
        return new ResponseEntity<>(purchaseInvoiceService.findPurchaseInvoiceById(purchaseInvoiceId), HttpStatus.OK);
    }

    // POST GOES HERE
    @PostMapping("save-purchase-invoice")
    public ResponseEntity<String> savePurchaseInvoice(@RequestBody PurchaseInvoiceDto purchaseInvoiceDto){
        purchaseInvoiceService.savePurchaseInvoice(purchaseInvoiceDto);
        return new ResponseEntity<>("Purchase invoice saved successfully!", HttpStatus.OK);
    }

}
