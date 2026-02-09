package com.order_management_api.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) { 
    	super(message);
    	}
}