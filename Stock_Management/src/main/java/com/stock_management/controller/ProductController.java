package com.stock_management.controller;

import com.stock_management.dto.CountProductsDto;
import com.stock_management.dto.ProductDto;
import com.stock_management.entity.Product;
import com.stock_management.repository.ProductRepository;
import com.stock_management.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/countProduct")
    public ResponseEntity<Long>getNumberOfProducts(){
        return new ResponseEntity(productService.countAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto>getProductById(@PathVariable Long productId){
        return new ResponseEntity<>(productService.findProductById(productId), HttpStatus.OK);
    }

    @GetMapping("/byStock/{stockId}")
    public ResponseEntity<List<ProductDto>>getProductsByStockId(@PathVariable Long stockId){
        return new ResponseEntity<>(productService.findProductsByStockId(stockId), HttpStatus.OK);
    }

    @GetMapping("/countProductsByStockId/{stockId}")
    public ResponseEntity<Long>getNumberOfProductsByStockId(@PathVariable Long stockId){
        return new ResponseEntity(productService.countProductsByStockId(stockId), HttpStatus.OK);
    }

    // POST GOES HERE
    @PostMapping("/saveProduct")
    public ResponseEntity saveProduct(@RequestBody ProductDto productDto){
        productService.saveProduct(productDto);
        return new ResponseEntity<String>("Product saved successfully!", HttpStatus.OK);
    }

    // PUT GOES HERE
    @PutMapping("/editProduct")
    public ResponseEntity editProduct(@RequestBody ProductDto productDto){
        productService.editProduct(productDto);
        return new ResponseEntity<String>("Product edited successfully!", HttpStatus.OK);
    }
}
