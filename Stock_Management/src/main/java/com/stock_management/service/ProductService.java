package com.stock_management.service;

import com.stock_management.dto.CountProductsDto;
import com.stock_management.dto.ProductDto;
import com.stock_management.dto.ProductListDto;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductService {
    // GET
    List<ProductDto> findAllProducts();

    ProductListDto findAllProductList(Pageable pageable);

    CountProductsDto countAllProducts();

    ProductDto findProductById(Long productId);

//    List<ProductDto> findProductsByStockId(Long stockId);

//    CountProductsDto countProductsByStockId(Long stockId);

    // POST
    void saveProduct(ProductDto productDto);

    // PUT
    void editProduct(ProductDto productDto);

    void saveProducts(ProductListDto productListDto);

    ProductListDto findListOfProductsByFilters(String productName, String supplierName, String Category, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize);
}
