package com.stock_management.service.implementation;

import com.querydsl.core.BooleanBuilder;
import com.stock_management.dto.CountProductsDto;
import com.stock_management.dto.ProductDto;
import com.stock_management.dto.ProductListDto;
import com.stock_management.entity.Product;
import com.stock_management.entity.QProduct;
import com.stock_management.mapper.MultipleSaveProductMapper;
import com.stock_management.mapper.ProductMapper;
import com.stock_management.repository.ProductRepository;
import com.stock_management.service.ProductService;
import org.hibernate.query.QueryProducer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImplementation implements ProductService {
    public final ProductRepository productRepository;
    public final ProductMapper productMapper;
    public final MultipleSaveProductMapper multipleSaveProductMapper;

    public ProductServiceImplementation(ProductRepository productRepository, ProductMapper productMapper, MultipleSaveProductMapper multipleSaveProductMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.multipleSaveProductMapper = multipleSaveProductMapper;
    }

    // GET
    @Override
    public List<ProductDto> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::mapProductEntityToDto).collect(Collectors.toList());
    }

    @Override
    public ProductListDto findAllProductList(Pageable pageable) {
        Page<Product> products = productRepository.findAll((org.springframework.data.domain.Pageable) pageable);
        return getProductListDto(products);
    }

    @Override
    public CountProductsDto countAllProducts() {
        Long numberOfProducts = productRepository.count();
        CountProductsDto countProductsDto = new CountProductsDto();
        countProductsDto.setNumberOfProducts(numberOfProducts);
        return countProductsDto;
    }

    // find product by product_id in productDto
    @Override
    public ProductDto findProductById(Long productId) {
            java.util.Optional<Product> product = productRepository.findById(productId);
            var oneProduct = product.orElse(null);
            return productMapper.mapProductEntityToDto(oneProduct);
    }

//    @Override
//    public List<ProductDto> findProductsByStockId(Long stockId) {
//        List<Product> products = productRepository.findBystock_stockId(stockId);
//        return products.stream().map(productMapper::mapProductEntityToDto).collect(Collectors.toList());
//    }

//    @Override
//    public CountProductsDto countProductsByStockId(Long stockId) {
//        Long numberOfProducts = productRepository.countBystock_stockId(stockId);
//        CountProductsDto countProductsDto = new CountProductsDto();
//        countProductsDto.setNumberOfProducts(numberOfProducts);
//        return countProductsDto;
//    }

    // POST
    @Override
    public void saveProduct(ProductDto productDto) {
        var saveProductInformation = productMapper.mapProductDtoToEntity(productDto);
        productRepository.save(saveProductInformation);
    }



    // PUT
    public void editProduct(ProductDto productDto) {
        var product = findProductById(productDto.getProductId());
        if (product != null) {
            product.setProductName(productDto.getProductName());
            product.setDescription(productDto.getDescription());
            product.setCategory(productDto.getCategory());
            product.setBox(productDto.getBox());
            product.setDosage(productDto.getDosage());
            product.setUnitsPerBox(productDto.getUnitsPerBox());
            product.setUnitsTotal(productDto.getUnitsTotal());
            product.setPricePerBox(productDto.getPricePerBox());
            product.setPricePerUnit(productDto.getPricePerUnit());
            product.setRequirePrescription(productDto.getRequirePrescription());
            product.setSupplier(productDto.getSupplier());
            productRepository.save(productMapper.mapProductDtoToEntity(product));
        } else {
            System.out.println("Product Not Found!");
        }
    }

    @Override
    public void saveProducts(ProductListDto productListDto) {
        var saveMultipleProduct = productListDto.getProducts().stream().map(productMapper::mapProductDtoToEntity).collect(Collectors.toList());
        productRepository.saveAll(saveMultipleProduct);
    }

    @Override
    public ProductListDto findListOfProductsByFilters(String productName, String supplierName, String category, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.by("ASC".equals(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        BooleanBuilder predicate = buildProductPredicate(productName, supplierName, category);
        Page<Product> product = productRepository.findAll(predicate,pageRequest);
        List<ProductDto> productDtos = product.stream().map(productMapper::mapProductEntityToDto).collect(Collectors.toList());
        var productListDto = new ProductListDto();
        productListDto.setProducts(productDtos);
        productListDto.setTotalElements(product.getNumberOfElements());
        productListDto.setTotalPages(product.getTotalPages());
        return productListDto;
    }

    private BooleanBuilder buildProductPredicate(String productName, String supplierName, String category) {
        var qProduct = QProduct.product;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(!productName.equals("")) {
            booleanBuilder.and(qProduct.productName.contains(productName));
        }
        if(!supplierName.equals("All")) {
            booleanBuilder.and(qProduct.supplier.supplierName.eq(supplierName));
        }
//        if(!requirePrescription.equals(null)) {
//            booleanBuilder.and(qProduct.requirePrescription.eq(requirePrescription));
//        }
        if(!category.equals("All")) {
            booleanBuilder.and(qProduct.category.eq(category));
        }
        return booleanBuilder;
    }

    private ProductListDto getProductListDto(Page<Product> product) {
        if (product == null) {
            System.out.println("Product Not Found!");
            return null;
        } else {
            List<ProductDto> productDto = product.stream().map(productMapper::mapProductEntityToDto).collect(Collectors.toList());
            ProductListDto productListDto = new ProductListDto();
            productListDto.setProducts(productDto);
            productListDto.setTotalElements(product.getNumberOfElements());
            productListDto.setTotalPages(product.getTotalPages());
            return productListDto;
        }
    }
}
