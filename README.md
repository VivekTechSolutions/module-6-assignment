📦 Order Management API – Module 6 (Assignment Set 2)
========================================================

📝 Context
------------

This project implements a RESTful Order Management API for handling orders, products, and customers.
The API is designed with a clear contract, versioning support, and standardized error handling.

📌 Features
-------------


✅ Create orders with multiple items

✅ Retrieve order details with nested items

✅ Product stock management

✅ Standardized error responses

✅ Swagger documentation for API usability

✅ API versioning support

📖 API Versioning
------------------

The API is versioned using URL paths to allow future updates without breaking existing clients:

Version	Description
v1	Initial version of Order & Product APIs
v2	Future improvements (e.g., additional fields, optional status updates)

Example:
-----------

POST /api/v1/orders
GET  /api/v1/orders/{id}
POST /api/v2/orders

🛠 API Endpoints & Contracts

1️⃣ Create Order
---------------

POST /api/v1/orders
----------------------

Request JSON Example:

{
  "customer": {
    "customerId": 1,
    "name": "Viraj",
    "email": "vivek@gmail.com"
  },
  "status": "COMPLETED",
  "items": [
    {
      "productId": 2,
      "quantity": 2
    }
  ]
}


Response JSON Example:
---------------------

{
  "orderId": 9,
  "status": "COMPLETED",
  "orderDate": "2026-02-09T11:00:00",
  "customer": {
    "customerId": 23,
    "name": "Viraj",
    "email": "vivek12@gmail.com"
  },
  "items": [
    {
      "productId": 3,
      "productName": "keyboard",
      "quantity": 1,
      "unitPrice": 6500.0,
      "totalPrice": 6500.0
    }
  ],
  "totalAmount": 6500.0
}

2️⃣ Get Order by ID
-------------------

GET /api/v1/orders/{id}
-----------------------
Response JSON Example:

{
  "orderId": 9,
  "status": "COMPLETED",
  "orderDate": "2026-02-09T11:00:00",
  "customer": {
    "customerId": 23,
    "name": "Viraj",
    "email": "vivek12@gmail.com"
  },
  "items": [
    {
      "productId": 3,
      "productName": "keyboard",
      "quantity": 1,
      "unitPrice": 6500.0,
      "totalPrice": 6500.0
    }
  ],
  "totalAmount": 6500.0
}

⚠ Standardized Error Response
---------------------------------


All errors follow the same structure:

Example Error JSON:

{
  "errorCode": "PRODUCT_NOT_FOUND",
  "message": "Product with ID 5 not found",
  "timestamp": "2026-02-09T11:10:00"
}


Fields:

Field	Description
errorCode	Unique machine-readable error code
message	Human-readable error description
timestamp	Server time when error occurred

📑 Swagger Documentation
---------------------------

All endpoints are documented with operation summaries, request/response examples, and parameter descriptions.

Accessible via:

http://localhost:8080/swagger-ui/index.html
-----------------------------------------

🌐 JSON & API Contract Rules
--------------------------------

No undocumented fields

Only necessary nested objects used

Arrays used properly for multiple items

No internal IDs exposed unless justified

API should remain readable for non-developers

💡 Notes for Future Versions
------------------------------

Add optional order status in requests

Support partial updates (PATCH) for orders

Introduce pagination for products/orders

Versioning ensures backward compatibility

📂 Deliverables
================

Swagger UI for API testing

JSON examples for all endpoints

Standardized error responses

Fully versioned API
