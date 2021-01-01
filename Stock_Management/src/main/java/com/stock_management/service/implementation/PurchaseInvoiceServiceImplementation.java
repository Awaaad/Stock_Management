package com.stock_management.service.implementation;

import com.querydsl.core.BooleanBuilder;
import com.stock_management.dto.PurchaseInvoiceDto;
import com.stock_management.dto.PurchaseInvoiceListDto;
import com.stock_management.dto.PurchaseInvoiceProductDto;
import com.stock_management.entity.Product;
import com.stock_management.entity.PurchaseInvoice;
import com.stock_management.entity.PurchaseInvoiceProduct;
import com.stock_management.entity.QPurchaseInvoice;
import com.stock_management.mapper.ProductMapper;
import com.stock_management.mapper.PurchaseInvoiceMapper;
import com.stock_management.mapper.PurchaseInvoiceProductMapper;
import com.stock_management.repository.ProductRepository;
import com.stock_management.repository.PurchaseInvoiceProductRepository;
import com.stock_management.repository.PurchaseInvoiceRepository;
import com.stock_management.service.PurchaseInvoiceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseInvoiceServiceImplementation implements PurchaseInvoiceService {
    private final PurchaseInvoiceRepository purchaseInvoiceRepository;
    private final PurchaseInvoiceMapper purchaseInvoiceMapper;
    private final PurchaseInvoiceProductRepository purchaseInvoiceProductRepository;
    private final PurchaseInvoiceProductMapper purchaseInvoiceProductMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public PurchaseInvoiceServiceImplementation(PurchaseInvoiceRepository purchaseInvoiceRepository, PurchaseInvoiceMapper purchaseInvoiceMapper, PurchaseInvoiceProductRepository purchaseInvoiceProductRepository, PurchaseInvoiceProductMapper purchaseInvoiceProductMapper, ProductRepository productRepository, ProductMapper productMapper) {
        this.purchaseInvoiceRepository = purchaseInvoiceRepository;
        this.purchaseInvoiceMapper = purchaseInvoiceMapper;
        this.purchaseInvoiceProductRepository = purchaseInvoiceProductRepository;
        this.purchaseInvoiceProductMapper = purchaseInvoiceProductMapper;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public void savePurchaseInvoice(PurchaseInvoiceDto purchaseInvoiceDto) {
        var purchaseInvoiceToEntity = purchaseInvoiceMapper.mapPurchaseInvoiceDtoToEntity(purchaseInvoiceDto);
        var savedPurchaseInvoice = purchaseInvoiceRepository.save(purchaseInvoiceToEntity);
        purchaseInvoiceProductRepository.saveAll(purchaseInvoiceDto.getPurchaseInvoiceProductDtos().stream().map(purchaseInvoiceProductDto ->
                mapPurchaseInvoiceProduct(purchaseInvoiceProductDto, savedPurchaseInvoice)).collect(Collectors.toList()));
    }

    private PurchaseInvoiceProduct mapPurchaseInvoiceProduct(PurchaseInvoiceProductDto purchaseInvoiceProductDto, PurchaseInvoice purchaseInvoice) {
        var purchaseInvoiceProduct = purchaseInvoiceProductMapper.mapPurchaseInvoiceProductDtoToEntity(purchaseInvoiceProductDto);

        purchaseInvoiceProduct.setPurchaseInvoice(purchaseInvoice);
        var product = productRepository.findById(purchaseInvoiceProductDto.getProductDto().getProductId());
        if (product.isPresent()) {
            var productEntity = product.get();
            if (purchaseInvoiceProductDto.getProductDto().getExpiryDate().isAfter(productEntity.getExpiryDate())) {
                productEntity.setExpiryDate(purchaseInvoiceProductDto.getProductDto().getExpiryDate().plusDays(1));
            } else {
                productEntity.setExpiryDate(productEntity.getExpiryDate().plusDays(1));
            }
            purchaseInvoiceProduct.setProduct(productEntity);
            var currentUnits = productEntity.getUnitsTotal();
            var newUnits = purchaseInvoiceProductDto.getBoxesReceived() * productEntity.getUnitsPerBox();
            productEntity.setUnitsTotal(currentUnits + newUnits);
            var boxesInStore = purchaseInvoiceProductDto.getBoxesReceived() + productEntity.getBox();
            productEntity.setBox(boxesInStore);
            if (!productEntity.getPricePerBox().equals(purchaseInvoiceProductDto.getPricePerBox()) && !purchaseInvoiceProductDto.getPricePerBox().equals(0D) ) {
                productEntity.setOldPricePerBox(productEntity.getPricePerBox());
                productEntity.setPricePerBox(purchaseInvoiceProductDto.getPricePerBox());
            }
        }
        return purchaseInvoiceProduct;
    }

    private PurchaseInvoiceDto mapPurchaseInvoiceEntityToDto(PurchaseInvoice purchaseInvoice) {
        var purchaseInvoiceDto = purchaseInvoiceMapper.mapPurchaseInvoiceEntityToDto(purchaseInvoice);

        var purchaseInvoiceProducts = purchaseInvoice.getPurchaseInvoiceProducts();
        var purchaseInvoiceProductDtos = purchaseInvoiceProducts.stream().map(this::mapPurchaseInvoiceProductEntityToDto).collect(Collectors.toList());

        purchaseInvoiceDto.setPurchaseInvoiceProductDtos(purchaseInvoiceProductDtos);
        return purchaseInvoiceDto;
    }

    private PurchaseInvoiceProductDto mapPurchaseInvoiceProductEntityToDto(PurchaseInvoiceProduct purchaseInvoiceProduct) {
        java.util.Optional<Product> product = productRepository.findById(purchaseInvoiceProduct.getProduct().getProductId());
        var oneProduct = product.orElse(null);
        var productDto = productMapper.mapProductEntityToDto(oneProduct);
        return purchaseInvoiceProductMapper.mapPurchaseInvoiceProductEntityToDto(purchaseInvoiceProduct);
    }

    @Override
    public PurchaseInvoiceDto findPurchaseInvoiceById(Long purchaseInvoiceId) {
        Optional<PurchaseInvoice> purchaseInvoice = purchaseInvoiceRepository.findById(purchaseInvoiceId);
        var onePurchaseInvoice = purchaseInvoice.orElse(null);

        return mapPurchaseInvoiceEntityToDto(onePurchaseInvoice);
//        onePurchaseInvoice.getPurchaseInvoiceProducts().stream().map(this::mapPurchaseInvoiceProductEntityToDto).collect(Collectors.toList());
//        return purchaseInvoiceMapper.mapPurchaseInvoiceEntityToDto(onePurchaseInvoice);
    }

    @Override
    public List<PurchaseInvoiceDto> findAllPurchaseInvoice() {
        List<PurchaseInvoice> purchaseInvoices = purchaseInvoiceRepository.findAll();
        return purchaseInvoices.stream().map(this::mapPurchaseInvoiceEntityToDto).collect(Collectors.toList());
    }

    @Override
    public PurchaseInvoiceListDto findPurchaseInvoiceByFilters(String searchBox, LocalDateTime invoiceDateFrom, LocalDateTime invoiceDateTo, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.by("ASC".equals(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        BooleanBuilder predicate = buildProductPredicate(searchBox, invoiceDateFrom, invoiceDateTo);
        Page<PurchaseInvoice> purchaseInvoices = purchaseInvoiceRepository.findAll(predicate,pageRequest);
        List<PurchaseInvoiceDto> purchaseInvoiceDtos = purchaseInvoices.stream().map(this::mapPurchaseInvoiceEntityToDto).collect(Collectors.toList());

        var purchaseInvoiceListDto = new PurchaseInvoiceListDto();
        purchaseInvoiceListDto.setPurchaseInvoiceDtos(purchaseInvoiceDtos);
        purchaseInvoiceListDto.setTotalElements(purchaseInvoices.getNumberOfElements());
        purchaseInvoiceListDto.setTotalPages(purchaseInvoices.getTotalPages());
        return purchaseInvoiceListDto;
    }

    private BooleanBuilder buildProductPredicate(String searchBox, LocalDateTime invoiceDateFrom, LocalDateTime invoiceDateTo) {
        var qPurchaseInvoice = QPurchaseInvoice.purchaseInvoice;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(!searchBox.equals("")) {
            booleanBuilder.and(qPurchaseInvoice.supplier.supplierName.toLowerCase().contains(searchBox.toLowerCase()))
            .or(qPurchaseInvoice.invoiceNumber.toLowerCase().contains(searchBox.toLowerCase()));
        }
        if(Objects.nonNull(invoiceDateFrom)) {
            booleanBuilder.and(qPurchaseInvoice.invoiceDate.after(invoiceDateFrom));
        }
        if(Objects.nonNull(invoiceDateTo)) {
            booleanBuilder.and(qPurchaseInvoice.invoiceDate.before(invoiceDateTo));
        }
        return booleanBuilder;
    }

}
