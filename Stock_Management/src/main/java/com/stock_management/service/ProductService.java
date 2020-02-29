package com.stock_management.service;

import com.stock_management.dto.CountProductsDto;
import com.stock_management.dto.ProductDto;
import com.stock_management.entity.Product;

import java.util.List;

public interface ProductService {
    // GET
    List<ProductDto> findAllProducts();

    CountProductsDto countAllProducts();

    ProductDto findProductById(Long productId);

    List<ProductDto> findProductsByStockId(Long stockId);

    CountProductsDto countProductsByStockId(Long stockId);

    // POST
    void saveProduct(ProductDto productDto);

    // PUT
    void editProduct(ProductDto productDto);
}
