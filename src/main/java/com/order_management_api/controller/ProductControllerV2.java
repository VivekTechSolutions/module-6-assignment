package com.order_management_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order_management_api.entity.Product;
import com.order_management_api.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/products")  // <-- v2 URL
@Tag(name = "Product API v2", description = "Master product operations (v2)")
public class ProductControllerV2 {   // <-- v2 controller name

    @Autowired
    private ProductService productService;

    @PostMapping
    @Operation(summary = "Add a new product", description = "Add product to master table")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product saved = productService.addProduct(product);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Retrieve all products from master table")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
