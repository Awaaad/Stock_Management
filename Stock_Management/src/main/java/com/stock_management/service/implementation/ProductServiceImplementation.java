package com.stock_management.service.implementation;

import com.stock_management.dto.CountProductsDto;
import com.stock_management.dto.ProductDto;
import com.stock_management.entity.Product;
import com.stock_management.mapper.ProductMapper;
import com.stock_management.repository.ProductRepository;
import com.stock_management.service.ProductService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService {
    public final ProductRepository productRepository;
    public final ProductMapper productMapper;

    public ProductServiceImplementation(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDto> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::mapProductEntityToDto).collect(Collectors.toList());
    }

//    @Override
//    public CountProductsDto countProductByProductId(Long ProductId) {
//        Integer numberOfProducts = productRepository.countByProductId(ProductId);
//        CountProductsDto countProductsDto = new CountProductsDto();
//        countProductsDto.setNumberOfProducts(numberOfProducts);
//        return countProductsDto;
//    }
}
