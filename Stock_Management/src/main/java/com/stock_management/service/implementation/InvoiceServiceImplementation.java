package com.stock_management.service.implementation;

import com.stock_management.mapper.ProductMapper;
import com.stock_management.mapper.PurchaseInvoiceLineMapper;
import com.stock_management.mapper.InvoiceMapper;
import com.stock_management.repository.ProductRepository;
import com.stock_management.repository.PurchaseInvoiceLineRepository;
import com.stock_management.repository.InvoiceRepository;
import com.stock_management.service.InvoiceService;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImplementation implements InvoiceService {
//    private final InvoiceRepository purchaseInvoiceRepository;
//    private final InvoiceMapper invoiceMapper;
//    private final PurchaseInvoiceLineRepository purchaseInvoiceLineRepository;
//    private final PurchaseInvoiceLineMapper purchaseInvoiceLineMapper;
//    private final ProductRepository productRepository;
//    private final ProductMapper productMapper;
//
//    public InvoiceServiceImplementation(InvoiceRepository purchaseInvoiceRepository, InvoiceMapper invoiceMapper, PurchaseInvoiceLineRepository purchaseInvoiceLineRepository, PurchaseInvoiceLineMapper purchaseInvoiceLineMapper, ProductRepository productRepository, ProductMapper productMapper) {
//        this.purchaseInvoiceRepository = purchaseInvoiceRepository;
//        this.invoiceMapper = invoiceMapper;
//        this.purchaseInvoiceLineRepository = purchaseInvoiceLineRepository;
//        this.purchaseInvoiceLineMapper = purchaseInvoiceLineMapper;
//        this.productRepository = productRepository;
//        this.productMapper = productMapper;
//    }

//    @Override
//    @Transactional
//    public void savePurchaseInvoice(InvoiceDto invoiceDto) {
//        var purchaseInvoiceToEntity = invoiceMapper.mapPurchaseInvoiceDtoToEntity(invoiceDto);
//        var savedPurchaseInvoice = purchaseInvoiceRepository.save(purchaseInvoiceToEntity);
//        purchaseInvoiceLineRepository.saveAll(invoiceDto.getPurchaseInvoiceProductsDto().stream().map(purchaseInvoiceProductDto ->
//                mapPurchaseInvoiceProduct(purchaseInvoiceProductDto, savedPurchaseInvoice)).collect(Collectors.toList()));
//    }
//
//    private PurchaseInvoiceLine mapPurchaseInvoiceProduct(PurchaseInvoiceLineDto purchaseInvoiceLineDto, PurchaseInvoice purchaseInvoice) {
//        var purchaseInvoiceProduct = purchaseInvoiceLineMapper.mapPurchaseInvoiceProductDtoToEntity(purchaseInvoiceLineDto);
//
//        purchaseInvoiceProduct.setPurchaseInvoice(purchaseInvoice);
//        var product = productRepository.findById(purchaseInvoiceProductDto.getProductDto().getProductId());
//        if (product.isPresent()) {
//            var productEntity = product.get();
//            if (purchaseInvoiceProductDto.getProductDto().getExpiryDate().isAfter(productEntity.getExpiryDate())) {
//                productEntity.setExpiryDate(purchaseInvoiceProductDto.getProductDto().getExpiryDate().plusDays(1));
//            } else {
//                productEntity.setExpiryDate(productEntity.getExpiryDate().plusDays(1));
//            }
//            if (!purchaseInvoiceProductDto.getWholeSalePrice().equals(productEntity.getWholeSalePrice())) {
//                productEntity.setWholeSalePrice(purchaseInvoiceProductDto.getWholeSalePrice());
//            }
//            purchaseInvoiceProduct.setProduct(productEntity);
//            var currentUnits = productEntity.getUnitsTotal();
//            var newUnits = purchaseInvoiceProductDto.getBoxesReceived() * productEntity.getUnitsPerBox();
//            productEntity.setUnitsTotal(currentUnits + newUnits);
//            var boxesInStore = purchaseInvoiceProductDto.getBoxesReceived() + productEntity.getBox();
//            productEntity.setBox(boxesInStore);
//            if (!productEntity.getPricePerBox().equals(purchaseInvoiceProductDto.getPricePerBox()) && !purchaseInvoiceProductDto.getPricePerBox().equals(0D) ) {
//                productEntity.setOldPricePerBox(productEntity.getPricePerBox());
//                productEntity.setPricePerBox(purchaseInvoiceProductDto.getPricePerBox());
//            }
//        }
//        return purchaseInvoiceProduct;
//    }

//    private InvoiceDto mapPurchaseInvoiceEntityToDto(PurchaseInvoice purchaseInvoice) {
//        var purchaseInvoiceDto = invoiceMapper.mapPurchaseInvoiceEntityToDto(purchaseInvoice);
//
//        var purchaseInvoiceProducts = purchaseInvoice.getPurchaseInvoiceLines();
//        var purchaseInvoiceProductDtos = purchaseInvoiceProducts.stream().map(this::mapPurchaseInvoiceProductEntityToDto).collect(Collectors.toList());
//
//        purchaseInvoiceDto.setPurchaseInvoiceProductsDto(purchaseInvoiceProductDtos);
//        return purchaseInvoiceDto;
//    }

//    private PurchaseInvoiceLineDto mapPurchaseInvoiceProductEntityToDto(PurchaseInvoiceLine purchaseInvoiceLine) {
//        java.util.Optional<Product> product = productRepository.findById(purchaseInvoiceProduct.getProduct().getProductId());
//        var oneProduct = product.orElse(null);
//        var productDto = productMapper.mapProductEntityToDto(oneProduct);
//        return purchaseInvoiceLineMapper.mapPurchaseInvoiceProductEntityToDto(purchaseInvoiceLine);
//    }

//    @Override
//    public InvoiceDto findPurchaseInvoiceById(Long purchaseInvoiceId) {
//        Optional<PurchaseInvoice> purchaseInvoice = purchaseInvoiceRepository.findById(purchaseInvoiceId);
//        var onePurchaseInvoice = purchaseInvoice.orElse(null);
//
//        return mapPurchaseInvoiceEntityToDto(onePurchaseInvoice);
//    }

//    @Override
//    public List<InvoiceDto> findAllPurchaseInvoice() {
//        List<PurchaseInvoice> purchaseInvoices = purchaseInvoiceRepository.findAll();
//        return purchaseInvoices.stream().map(this::mapPurchaseInvoiceEntityToDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public PurchaseInvoiceListDto findPurchaseInvoiceByFilters(String searchBox, LocalDateTime invoiceDateFrom, LocalDateTime invoiceDateTo, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize) {
//        Sort sort = Sort.by("ASC".equals(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
//        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
//        BooleanBuilder predicate = buildProductPredicate(searchBox, invoiceDateFrom, invoiceDateTo);
//        Page<PurchaseInvoice> purchaseInvoices = purchaseInvoiceRepository.findAll(predicate,pageRequest);
//        List<InvoiceDto> invoiceDtos = purchaseInvoices.stream().map(this::mapPurchaseInvoiceEntityToDto).collect(Collectors.toList());
//
//        var purchaseInvoiceListDto = new PurchaseInvoiceListDto();
//        purchaseInvoiceListDto.setPurchaseInvoicesDto(invoiceDtos);
//        purchaseInvoiceListDto.setTotalElements(purchaseInvoices.getNumberOfElements());
//        purchaseInvoiceListDto.setTotalPages(purchaseInvoices.getTotalPages());
//        return purchaseInvoiceListDto;
//    }

//    private BooleanBuilder buildProductPredicate(String searchBox, LocalDateTime invoiceDateFrom, LocalDateTime invoiceDateTo) {
//        var qPurchaseInvoice = QPurchaseInvoice.purchaseInvoice;
//        BooleanBuilder booleanBuilder = new BooleanBuilder();
//        if(!searchBox.equals("")) {
//            booleanBuilder.and(qPurchaseInvoice.supplier.supplierName.toLowerCase().contains(searchBox.toLowerCase()))
//            .or(qPurchaseInvoice.invoiceNumber.toLowerCase().contains(searchBox.toLowerCase()));
//        }
//        if(Objects.nonNull(invoiceDateFrom)) {
//            booleanBuilder.and(qPurchaseInvoice.invoiceDate.after(invoiceDateFrom));
//        }
//        if(Objects.nonNull(invoiceDateTo)) {
//            booleanBuilder.and(qPurchaseInvoice.invoiceDate.before(invoiceDateTo));
//        }
//        return booleanBuilder;
//    }

}
