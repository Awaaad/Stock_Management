package com.stock_management.controller;

import com.stock_management.dto.invoice.InvoiceDto;
import com.stock_management.dto.invoice.PurchaseInvoiceListDto;
import com.stock_management.dto.invoice.SavePurchaseInvoiceStockDto;
import com.stock_management.service.InvoiceService;
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
import java.util.Date;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/purchase-invoice")
public class PurchaseInvoiceController {
    private final InvoiceService invoiceService;

    public PurchaseInvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // GET GOES HERE
//    @GetMapping("all")
//    public ResponseEntity<List<InvoiceDto>>getAllPurchaseInvoice(){
//        return new ResponseEntity<>(invoiceService.findAllPurchaseInvoice(), HttpStatus.OK);
//    }
//
    @GetMapping("filter")
    public ResponseEntity<PurchaseInvoiceListDto> getPurchaseInvoicesViaFilter(@RequestParam String searchBox, @RequestParam("invoiceDateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date invoiceDateFrom, @RequestParam("invoiceDateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date invoiceDateTo, @RequestParam String sortOrder, @RequestParam String sortBy, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return new ResponseEntity<>(invoiceService.findPurchaseInvoiceByFilters(searchBox, invoiceDateFrom, invoiceDateTo, sortOrder, sortBy, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("invoice-id/{invoiceId}")
    public ResponseEntity<InvoiceDto> getPurchaseInvoiceById(@PathVariable Long invoiceId){
        return new ResponseEntity<>(invoiceService.findPurchaseInvoiceById(invoiceId), HttpStatus.OK);
    }

//    // POST GOES HERE
    @PostMapping("save-purchase-invoice")
    public ResponseEntity<String> savePurchaseInvoice(@RequestBody SavePurchaseInvoiceStockDto savePurchaseInvoiceStockDto){
        invoiceService.savePurchaseInvoice(savePurchaseInvoiceStockDto);
        return new ResponseEntity<>("Purchase invoice saved successfully!", HttpStatus.OK);
    }

}
