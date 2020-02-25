package com.stock_management.service;

import com.stock_management.dto.CountProductsDto;
import com.stock_management.dto.ProductDto;
import com.stock_management.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAllProducts();

//    CountProductsDto countProductByProductId(Long ProductId);
}
