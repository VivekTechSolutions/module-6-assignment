package com.order_management_api.response;

import java.time.LocalDateTime;

public class ErrorResponse {

    private String errorCode;     // e.g. PRODUCT_NOT_FOUND
    private String message;       // Human-readable message
    private LocalDateTime timestamp;

    // ---------- Constructors ----------
    public ErrorResponse() {}

    public ErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(String errorCode, String message, LocalDateTime timestamp) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = timestamp;
    }

    // ---------- Getters & Setters ----------
    public String getErrorCode() { return errorCode; }
    public void setErrorCode(String errorCode) { this.errorCode = errorCode; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
