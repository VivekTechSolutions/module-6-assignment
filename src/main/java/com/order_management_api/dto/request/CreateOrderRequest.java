package com.order_management_api.dto.request;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.order_management_api.ennums.OrderStatus;

public class CreateOrderRequest {

    @NotNull(message = "Customer information is required")
    @Valid
    private CustomerRequest customer;

    private LocalDateTime orderDate;   // optional, defaults to now

    private OrderStatus status;       // <-- NEW: optional status

    @NotEmpty(message = "Order must contain at least one item")
    @Valid
    private List<OrderItemRequest> items;

    // ---------- Getters & Setters ----------
    public CustomerRequest getCustomer() { return customer; }
    public void setCustomer(CustomerRequest customer) { this.customer = customer; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public List<OrderItemRequest> getItems() { return items; }
    public void setItems(List<OrderItemRequest> items) { this.items = items; }

    // ---------- Inner DTO ----------
    public static class CustomerRequest {

        @NotNull(message = "Customer ID is required")
        private Long customerId;

        @NotNull(message = "Customer name is required")
        private String name;

        @NotNull(message = "Customer email is required")
        private String email;

        // Getters & Setters
        public Long getCustomerId() { return customerId; }
        public void setCustomerId(Long customerId) { this.customerId = customerId; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
}
