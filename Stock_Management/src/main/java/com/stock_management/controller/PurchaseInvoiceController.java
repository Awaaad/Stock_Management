package com.stock_management.controller;

import com.stock_management.service.InvoiceService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//    @GetMapping("filter")
//    public ResponseEntity<PurchaseInvoiceListDto> getPurchaseInvoicesViaFilter(@RequestParam String searchBox, @RequestParam("invoiceDateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime invoiceDateFrom, @RequestParam("invoiceDateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime invoiceDateTo, @RequestParam String sortOrder, @RequestParam String sortBy, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
//        return new ResponseEntity<>(invoiceService.findPurchaseInvoiceByFilters(searchBox, invoiceDateFrom, invoiceDateTo, sortOrder, sortBy, pageNumber, pageSize), HttpStatus.OK);
//    }
//
//    @GetMapping("purchase-invoice-id/{purchaseInvoiceId}")
//    public ResponseEntity<InvoiceDto> getPurchaseInvoiceById(@PathVariable Long purchaseInvoiceId){
//        return new ResponseEntity<>(invoiceService.findPurchaseInvoiceById(purchaseInvoiceId), HttpStatus.OK);
//    }
//
//    // POST GOES HERE
//    @PostMapping("save-purchase-invoice")
//    public ResponseEntity<String> savePurchaseInvoice(@RequestBody InvoiceDto invoiceDto){
//        invoiceService.savePurchaseInvoice(invoiceDto);
//        return new ResponseEntity<>("Purchase invoice saved successfully!", HttpStatus.OK);
//    }

}
