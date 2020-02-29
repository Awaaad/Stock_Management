package com.stock_management.service.implementation;

import com.stock_management.dto.CountProductsDto;
import com.stock_management.dto.ProductDto;
import com.stock_management.entity.Product;
import com.stock_management.mapper.ProductMapper;
import com.stock_management.repository.ProductRepository;
import com.stock_management.service.ProductService;
import lombok.var;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductServiceImplementation implements ProductService {
    public final ProductRepository productRepository;
    public final ProductMapper productMapper;

    public ProductServiceImplementation(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
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

    @Override
    public ProductDto findProductById(Long productId) {
            java.util.Optional<Product> product = productRepository.findById(productId);
            var oneProduct = product.orElse(null);
            return productMapper.mapProductEntityToDto(oneProduct);
    }

    @Override
    public List<ProductDto> findProductsByStockId(Long stockId) {
        List<Product> products = productRepository.findBystock_stockId(stockId);
        return products.stream().map(productMapper::mapProductEntityToDto).collect(Collectors.toList());
    }

    @Override
    public CountProductsDto countProductsByStockId(Long stockId) {
        Long numberOfProducts = productRepository.countBystock_stockId(stockId);
        CountProductsDto countProductsDto = new CountProductsDto();
        countProductsDto.setNumberOfProducts(numberOfProducts);
        return countProductsDto;
    }

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
            product.setMakeDate(productDto.getMakeDate());
            product.setExpiryDate(productDto.getExpiryDate());
            product.setPrice(productDto.getPrice());
            product.setQuantity(productDto.getQuantity());
            product.setSupplier(productDto.getSupplier());
            product.setStock(productDto.getStock());
            productRepository.save(productMapper.mapProductDtoToEntity(product));
        } else {
            System.out.println("Product Not Found!");
        }
    }
}
