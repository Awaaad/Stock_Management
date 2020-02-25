package com.stock_management.controller;

import com.stock_management.dto.CountProductsDto;
import com.stock_management.dto.ProductDto;
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

    @GetMapping("/countProduct")
    public ResponseEntity<Long>getNumberOfProducts(){
        return new ResponseEntity<>(productRepository.count(), HttpStatus.OK);
    }

}
