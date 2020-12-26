package com.stock_management.controller;

import com.stock_management.dto.ProductDto;
import com.stock_management.dto.ProductListDto;
import com.stock_management.dto.UpdateStockAmountDto;
import com.stock_management.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET GOES HERE
    @GetMapping("all")
    public ResponseEntity<List<ProductDto>>getAllProducts(){
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }

    @GetMapping("filter")
    public ResponseEntity<ProductListDto> getProductsViaFilter(@RequestParam String productName, @RequestParam Long supplierId, @RequestParam String category, @RequestParam String slot, @RequestParam String sortOrder, @RequestParam String sortBy, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return new ResponseEntity<>(productService.findListOfProductsByFilters(productName, supplierId, category, slot, sortOrder, sortBy, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("count-product")
    public ResponseEntity<Long>getNumberOfProducts(){
        return new ResponseEntity(productService.countAllProducts(), HttpStatus.OK);
    }

    @GetMapping("id")
    public ResponseEntity<ProductDto>getProductById(@RequestParam Long productId){
        return new ResponseEntity<>(productService.findProductById(productId), HttpStatus.OK);
    }

    @GetMapping("product-low-stock-count")
    public ResponseEntity<Long>getNumberOfProductsLowInStock(){
        return new ResponseEntity<Long>(productService.findNumberOfProductsLowInStock(), HttpStatus.OK);
    }

    @GetMapping("slots")
    public ResponseEntity<List<String>>getAllSlots(){
        return new ResponseEntity<>(productService.findAllSlots(), HttpStatus.OK);
    }

    // POST GOES HERE
    @PostMapping("save-product")
    public ResponseEntity saveProduct(@RequestBody ProductDto productDto){
        productService.saveProduct(productDto);
        return new ResponseEntity<String>("Product saved successfully!", HttpStatus.OK);
    }

    @PostMapping("save-products")
    public ResponseEntity<String> saveProducts(@RequestBody ProductListDto productListDto){
        productService.saveProducts(productListDto);
        return new ResponseEntity<String>("Products saved successfully!", HttpStatus.OK);
    }

    @PostMapping("upload-csv-product")
    public void uploadProduct(@RequestParam("file")MultipartFile file) throws IOException {
        if (productService.hasExcelFormat(file)) {
            try {
                productService.upload(file);
            } catch (Exception e) {
                return;
            }
        }
    }

    // PUT GOES HERE
    @PutMapping("edit-product")
    public ResponseEntity<String> editProduct(@RequestBody ProductDto productDto){
        productService.editProduct(productDto);
        return new ResponseEntity<String>("Product edited successfully!", HttpStatus.OK);
    }

    @PutMapping("quick-stock-control")
    public ResponseEntity<String> quickStockControl(@RequestBody List<UpdateStockAmountDto> updateStockAmountDto){
        productService.quickStockControl(updateStockAmountDto);
        return new ResponseEntity<String>("Product stock successfully increased!", HttpStatus.OK);
    }
}
