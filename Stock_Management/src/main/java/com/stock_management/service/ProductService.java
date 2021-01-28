package com.stock_management.service;

import com.stock_management.dto.CountProductsDto;
import com.stock_management.dto.ProductDto;
import com.stock_management.dto.ProductListDto;
import com.stock_management.dto.SaveProductDto;
import com.stock_management.dto.UpdateStockAmountDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface ProductService {
    // GET
    List<ProductDto> findAllProducts();

    ProductListDto findAllProductLessThanMinStockAmount(Pageable pageable);

    ProductListDto findAllProductList(Pageable pageable);

    CountProductsDto countAllProducts();

    ProductDto findProductById(Long productId);

    Long findNumberOfProductsLowInStock();

    ProductListDto findListOfProductsByFilters(String productName, Long supplierId, String Category, String slot, LocalDate expiryDate, Boolean productLowInStock, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize);

    List<String> findAllSlots();
    // POST
    void saveProduct(SaveProductDto saveProductDto);

    void saveProducts(List<SaveProductDto> saveProductsDto);

    void upload(MultipartFile file) throws IOException;

    boolean hasExcelFormat(MultipartFile file);

    // PUT
    void editProduct(ProductDto productDto) throws Exception;

}
