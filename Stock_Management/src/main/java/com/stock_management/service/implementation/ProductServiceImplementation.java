package com.stock_management.service.implementation;

import com.stock_management.dto.CountProductsDto;
import com.stock_management.dto.ManipulateProductQuantityDto;
import com.stock_management.dto.ProductDto;
import com.stock_management.dto.SaveMultipleProductsDto;
import com.stock_management.entity.Product;
import com.stock_management.mapper.MultipleSaveProductMapper;
import com.stock_management.mapper.ProductMapper;
import com.stock_management.repository.ProductRepository;
import com.stock_management.service.ProductService;
import lombok.var;
import org.springframework.stereotype.Service;
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

    @Override
    public void saveMultipleProduct(SaveMultipleProductsDto saveMultipleProductsDto) {
        List<ManipulateProductQuantityDto> manipulateProductQuantityDtos = (List<ManipulateProductQuantityDto>) multipleSaveProductMapper.mapSaveMultipleProductsDtoToEntity(saveMultipleProductsDto);
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
    public void ManipulateProductQuantity(ProductDto productDto) {
        var product = findProductById(productDto.getProductId());
        if (product != null) {
            product.setBox(productDto.getBox());
            product.setUnitsPerBox(productDto.getUnitsPerBox());
            product.setUnitsTotal(productDto.getUnitsTotal());
            productRepository.save(productMapper.mapProductDtoToEntity(product));
        } else {
            System.out.println("Product Not Found!");
        }
    }
}
