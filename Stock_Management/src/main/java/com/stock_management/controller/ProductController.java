package com.stock_management.controller;

import com.stock_management.dto.ProductDto;
import com.stock_management.dto.ProductListDto;
import com.stock_management.entity.Product;
import com.stock_management.repository.ProductRepository;
import com.stock_management.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    // GET GOES HERE
    @GetMapping("all")
    public ResponseEntity<List<ProductDto>>getAllProducts(){
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }

    @GetMapping("filter")
    public ResponseEntity<ProductListDto> getProductsViaFilter(@RequestParam String productName, @RequestParam String supplierName, @RequestParam String category, @RequestParam String sortOrder, @RequestParam String sortBy, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return new ResponseEntity<>(productService.findListOfProductsByFilters(productName, supplierName, category, sortOrder, sortBy, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/countProduct")
    public ResponseEntity<Long>getNumberOfProducts(){
        return new ResponseEntity(productService.countAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto>getProductById(@PathVariable Long productId){
        return new ResponseEntity<>(productService.findProductById(productId), HttpStatus.OK);
    }

//    @GetMapping("/byStock/{stockId}")
//    public ResponseEntity<List<ProductDto>>getProductsByStockId(@PathVariable Long stockId){
//        return new ResponseEntity<>(productService.findProductsByStockId(stockId), HttpStatus.OK);
//    }
//
//    @GetMapping("/countProductsByStockId/{stockId}")
//    public ResponseEntity<Long>getNumberOfProductsByStockId(@PathVariable Long stockId){
//        return new ResponseEntity(productService.countProductsByStockId(stockId), HttpStatus.OK);
//    }

    // POST GOES HERE
    @PostMapping("/saveProduct")
    public ResponseEntity saveProduct(@RequestBody ProductDto productDto){
        productService.saveProduct(productDto);
        return new ResponseEntity<String>("Product saved successfully!", HttpStatus.OK);
    }

    @PostMapping("/saveProducts")
    public ResponseEntity saveProducts(@RequestBody ProductListDto productListDto){
        productService.saveProducts(productListDto);
        return new ResponseEntity<String>("Products saved successfully!", HttpStatus.OK);
    }

    // PUT GOES HERE
    @PutMapping("/editProduct")
    public ResponseEntity editProduct(@RequestBody ProductDto productDto){
        productService.editProduct(productDto);
        return new ResponseEntity<String>("Product edited successfully!", HttpStatus.OK);
    }
}
