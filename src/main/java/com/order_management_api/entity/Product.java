package com.order_management_api.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "product",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
    }
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    // Required by JPA
    protected Product() {}

    public Product(String name, Double unitPrice, Integer stockQuantity) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
    }

    // Getters & Setters
    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
