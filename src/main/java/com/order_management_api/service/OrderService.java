package com.order_management_api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order_management_api.dto.request.CreateOrderRequest;
import com.order_management_api.ennums.OrderStatus;
import com.order_management_api.entity.Customer;
import com.order_management_api.entity.Order;
import com.order_management_api.entity.OrderItem;
import com.order_management_api.entity.Product;
import com.order_management_api.exception.InsufficientStockException;
import com.order_management_api.exception.OrderNotFoundException;
import com.order_management_api.exception.ProductNotFoundException;
import com.order_management_api.repository.CustomerRepository;
import com.order_management_api.repository.OrderRepository;
import com.order_management_api.repository.ProductRepository;
import com.order_management_api.response.OrderItemResponse;
import com.order_management_api.response.OrderResponse;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    // Create order
    public OrderResponse createOrder(CreateOrderRequest request) {

        // Check customer or create
        Customer customer = customerRepository.findById(request.getCustomer().getCustomerId())
                .orElseGet(() -> {
                    Customer newCustomer = new Customer();
                    newCustomer.setCustomerId(request.getCustomer().getCustomerId());
                    newCustomer.setName(request.getCustomer().getName());
                    newCustomer.setEmail(request.getCustomer().getEmail());
                    return customerRepository.save(newCustomer);
                });

        // Create order
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(request.getOrderDate() != null ? request.getOrderDate() : LocalDateTime.now());

        // Set status from request if provided, else default to PENDING
        order.setStatus(request.getStatus() != null ? request.getStatus() : OrderStatus.PENDING);

        // Add items
        List<OrderItem> items = request.getItems().stream().map(itemReq -> {
            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found: " + itemReq.getProductId()));

            if (product.getStockQuantity() < itemReq.getQuantity()) {
                throw new InsufficientStockException("Not enough stock for product: " + product.getName());
            }

            // Reduce stock
            product.setStockQuantity(product.getStockQuantity() - itemReq.getQuantity());
            productRepository.save(product);

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProductId(product.getProductId());
            item.setProductName(product.getName());
            item.setQuantity(itemReq.getQuantity());
            item.setUnitPrice(product.getUnitPrice());
            item.setTotalPrice(product.getUnitPrice() * itemReq.getQuantity());
            return item;
        }).collect(Collectors.toList());

        order.setItems(items);
        double totalAmount = items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
        order.setTotalAmount(totalAmount);

        orderRepository.save(order);

        // Convert to Response
        return mapToResponse(order);
    }

    // Get order by ID
    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));
        return mapToResponse(order);
    }

    private OrderResponse mapToResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getOrderId());
        response.setStatus(order.getStatus());
        response.setOrderDate(order.getOrderDate());
        response.setTotalAmount(order.getTotalAmount());

        // Customer
        OrderResponse.CustomerResponse custResp = new OrderResponse.CustomerResponse();
        custResp.setCustomerId(order.getCustomer().getCustomerId());
        custResp.setName(order.getCustomer().getName());
        custResp.setEmail(order.getCustomer().getEmail());
        response.setCustomer(custResp);

        // Items
        List<OrderItemResponse> itemResponses = order.getItems().stream()
                .map(item -> {
                    OrderItemResponse iResp = new OrderItemResponse();
                    iResp.setProductId(item.getProductId());
                    iResp.setProductName(item.getProductName());
                    iResp.setQuantity(item.getQuantity());
                    iResp.setUnitPrice(item.getUnitPrice());
                    iResp.setTotalPrice(item.getTotalPrice());
                    return iResp;
                }).collect(Collectors.toList());

        response.setItems(itemResponses);

        return response;
    }
}
