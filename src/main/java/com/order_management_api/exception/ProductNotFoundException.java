package com.order_management_api.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) { 
    	super(message);
    	}
}