package com.order_management_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order_management_api.entity.Product;
import com.order_management_api.exception.ProductNotFoundException;
import com.order_management_api.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Add a new product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Get product by id
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}

