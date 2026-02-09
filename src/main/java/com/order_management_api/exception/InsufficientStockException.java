package com.order_management_api.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
    	super(message); 
    	}
}