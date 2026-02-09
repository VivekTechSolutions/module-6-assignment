package com.order_management_api.response;

import java.time.LocalDateTime;
import java.util.List;

import com.order_management_api.ennums.OrderStatus;

public class OrderResponse {

    private Long orderId;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private CustomerResponse customer;
    private List<OrderItemResponse> items;
    private Double totalAmount;

    // ---------- Constructors ----------
    public OrderResponse() {}

    public OrderResponse(Long orderId, OrderStatus status, LocalDateTime orderDate,
                         CustomerResponse customer, List<OrderItemResponse> items,
                         Double totalAmount) {
        this.orderId = orderId;
        this.status = status;
        this.orderDate = orderDate;
        this.customer = customer;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    // ---------- Getters & Setters ----------
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public CustomerResponse getCustomer() { return customer; }
    public void setCustomer(CustomerResponse customer) { this.customer = customer; }

    public List<OrderItemResponse> getItems() { return items; }
    public void setItems(List<OrderItemResponse> items) { this.items = items; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    // ---------- Inner DTO ----------
    public static class CustomerResponse {

        private Long customerId;
        private String name;
        private String email;

        public CustomerResponse() {}

        public CustomerResponse(Long customerId, String name, String email) {
            this.customerId = customerId;
            this.name = name;
            this.email = email;
        }

        public Long getCustomerId() { return customerId; }
        public void setCustomerId(Long customerId) { this.customerId = customerId; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
}
