package com.stock_management.controller;

import com.stock_management.dto.invoice.InvoiceDto;
import com.stock_management.dto.invoice.PurchaseInvoiceListDto;
import com.stock_management.dto.invoice.SavePurchaseInvoiceStockDto;
import com.stock_management.service.InvoiceService;
import com.stock_management.type.PaymentType;
import com.stock_management.type.TransactionType;
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
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("filter")
    public ResponseEntity<PurchaseInvoiceListDto> getPurchaseInvoicesViaFilter(@RequestParam TransactionType transactionType, @RequestParam PaymentType paymentType, @RequestParam String searchBox, @RequestParam Long userId, @RequestParam Long customerId, @RequestParam("invoiceDateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime invoiceDateFrom, @RequestParam("invoiceDateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime invoiceDateTo, @RequestParam String sortOrder, @RequestParam String sortBy, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return new ResponseEntity<>(invoiceService.findPurchaseInvoiceByFilters(transactionType, paymentType, searchBox, userId, customerId, invoiceDateFrom, invoiceDateTo, sortOrder, sortBy, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("invoice-id/{invoiceId}")
    public ResponseEntity<InvoiceDto> getPurchaseInvoiceById(@PathVariable Long invoiceId){
        return new ResponseEntity<>(invoiceService.findPurchaseInvoiceById(invoiceId), HttpStatus.OK);
    }

    @PostMapping("save-purchase-invoice")
    public ResponseEntity<String> savePurchaseInvoice(@RequestBody SavePurchaseInvoiceStockDto savePurchaseInvoiceStockDto){
        invoiceService.savePurchaseInvoice(savePurchaseInvoiceStockDto);
        return new ResponseEntity<>("Purchase invoice saved successfully!", HttpStatus.OK);
    }

    @PostMapping("save-bulk-payment")
    public ResponseEntity<String> saveBulkPayment(@RequestBody List<InvoiceDto> invoicesDto) throws Exception{
        invoiceService.bulkPayment(invoicesDto);
        return new ResponseEntity<>("Payment made successfully!", HttpStatus.OK);
    }
}
