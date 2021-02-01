package com.stock_management.service.implementation;

import com.querydsl.core.BooleanBuilder;
import com.stock_management.dto.invoice.InvoiceDto;
import com.stock_management.dto.invoice.PurchaseInvoiceListDto;
import com.stock_management.dto.invoice.SavePurchaseInvoiceStockDto;
import com.stock_management.entity.Invoice;
import com.stock_management.entity.PurchaseInvoiceLine;
import com.stock_management.entity.QInvoice;
import com.stock_management.mapper.InvoiceMapper;
import com.stock_management.repository.InvoiceRepository;
import com.stock_management.repository.PurchaseInvoiceLineRepository;
import com.stock_management.repository.SupplierRepository;
import com.stock_management.repository.UserRepository;
import com.stock_management.service.InvoiceService;
import com.stock_management.service.StockService;
import com.stock_management.type.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImplementation implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final StockService stockService;
    private final UserRepository userRepository;
    private final PurchaseInvoiceLineRepository purchaseInvoiceLineRepository;
    private final SupplierRepository supplierRepository;
    private final InvoiceMapper invoiceMapper;

    public InvoiceServiceImplementation(InvoiceRepository invoiceRepository, StockService stockService, UserRepository userRepository, PurchaseInvoiceLineRepository purchaseInvoiceLineRepository, SupplierRepository supplierRepository, InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.stockService = stockService;
        this.userRepository = userRepository;
        this.purchaseInvoiceLineRepository = purchaseInvoiceLineRepository;
        this.supplierRepository = supplierRepository;
        this.invoiceMapper = invoiceMapper;
    }

    @Override
    public void savePurchaseInvoice(SavePurchaseInvoiceStockDto savePurchaseInvoiceStockDto) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(savePurchaseInvoiceStockDto.getInvoiceNumber());
        invoice.setTransactionType(TransactionType.PURCHASE);
        invoice.setTotalPrice(savePurchaseInvoiceStockDto.getTotalPrice());
        invoice.setSupplier(supplierRepository.findById(savePurchaseInvoiceStockDto.getSupplierId()).orElse(null));
        invoice.setCreatedBy(userRepository.findById(savePurchaseInvoiceStockDto.getUserId()).orElse(null));
        invoice.setCreatedDate(new Date());
        var savedInvoice = invoiceRepository.save(invoice);

        var savedStocks = stockService.saveStock(savePurchaseInvoiceStockDto.getStocksDto());
        var setPurchaseInvoiceLines = savedStocks.stream().map(stock -> {
            PurchaseInvoiceLine purchaseInvoiceLine = new PurchaseInvoiceLine();
            purchaseInvoiceLine.setInvoice(savedInvoice);
            purchaseInvoiceLine.setStock(stock);
            purchaseInvoiceLine.setBoxesReceived(stock.getQuantity());
            purchaseInvoiceLine.setWholeSalePrice(stock.getWholeSalePrice());
            purchaseInvoiceLine.setPricePerBox(stock.getPricePerBox());
            return purchaseInvoiceLine;
        }).collect(Collectors.toList());

        purchaseInvoiceLineRepository.saveAll(setPurchaseInvoiceLines);
    }

    @Override
    public InvoiceDto findPurchaseInvoiceById(Long invoiceId) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);
        var invoice = optionalInvoice.orElse(null);


        return invoiceMapper.mapInvoiceEntityToDto(invoice);
    }

    @Override
    public PurchaseInvoiceListDto findPurchaseInvoiceByFilters(String searchBox, Date invoiceDateFrom, Date invoiceDateTo, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.by("ASC".equals(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        BooleanBuilder predicate = buildProductPredicate(searchBox, invoiceDateFrom, invoiceDateTo);
        Page<Invoice> invoices = invoiceRepository.findAll(predicate,pageRequest);
        List<InvoiceDto> invoiceDtos = invoices.stream().map(invoiceMapper::mapInvoiceEntityToDto).collect(Collectors.toList());

        var purchaseInvoiceListDto = new PurchaseInvoiceListDto();
        purchaseInvoiceListDto.setPurchaseInvoicesDto(invoiceDtos);
        purchaseInvoiceListDto.setTotalElements(invoices.getNumberOfElements());
        purchaseInvoiceListDto.setTotalPages(invoices.getTotalPages());
        return purchaseInvoiceListDto;
    }

    private BooleanBuilder buildProductPredicate(String searchBox, Date invoiceDateFrom, Date invoiceDateTo) {
        var qInvoice = QInvoice.invoice;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(!searchBox.equals("")) {
            booleanBuilder.and(qInvoice.supplier.supplierName.toLowerCase().contains(searchBox.toLowerCase()))
            .or(qInvoice.invoiceNumber.toLowerCase().contains(searchBox.toLowerCase()));
        }
        if(Objects.nonNull(invoiceDateFrom)) {
            booleanBuilder.and(qInvoice.createdDate.after(invoiceDateFrom));
        }
        if(Objects.nonNull(invoiceDateTo)) {
            booleanBuilder.and(qInvoice.createdDate.before(invoiceDateTo));
        }
        return booleanBuilder;
    }

}
