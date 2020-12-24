package com.stock_management.service;

import com.stock_management.dto.CountProductsDto;
import com.stock_management.dto.ProductDto;
import com.stock_management.dto.ProductListDto;
import com.stock_management.dto.UpdateStockAmountDto;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;

public interface ProductService {
    // GET
    List<ProductDto> findAllProducts();

    ProductListDto findAllProductList(Pageable pageable);

    CountProductsDto countAllProducts();

    ProductDto findProductById(Long productId);

    Long findNumberOfProductsLowInStock();

    ProductListDto findListOfProductsByFilters(String productName, Long supplierId, String Category, String slot, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize);

    List<String> findAllSlots();
    // POST
    void saveProduct(ProductDto productDto);

    void saveProducts(ProductListDto productListDto);

    void upload(MultipartFile file) throws IOException;

    boolean hasExcelFormat(MultipartFile file);

    // PUT
    void editProduct(ProductDto productDto);

    void quickStockControl(List<UpdateStockAmountDto> updateStockAmountDto);

}
