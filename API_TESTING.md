# Banking Portal API Testing Guide

## Base URL
```
http://localhost:8080/api
```

## 1. Authentication

### Login
```bash
POST /auth/login
Content-Type: application/json

{
  "email": "admin@bank.com",
  "password": "Admin@123"
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "email": "admin@bank.com",
  "role": "ADMIN",
  "employeeId": "EMP00000001",
  "name": "Admin User"
}
```

## 2. Employee Management

### Get All Employees
```bash
GET /employees
Authorization: Bearer {token}
```

### Create Employee (Admin Only)
```bash
POST /employees
Authorization: Bearer {token}
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@bank.com",
  "password": "Pass@123",
  "phone": "+1234567890",
  "department": "IT",
  "designation": "Developer",
  "role": "CLERK"
}
```

### Update Employee
```bash
PUT /employees/{id}
Authorization: Bearer {token}
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "phone": "+1234567890",
  "department": "IT",
  "designation": "Senior Developer",
  "role": "MANAGER"
}
```

## 3. Customer Management

### Get All Customers
```bash
GET /customers
Authorization: Bearer {token}
```

### Create Customer
```bash
POST /customers
Authorization: Bearer {token}
Content-Type: application/json

{
  "firstName": "Jane",
  "lastName": "Smith",
  "email": "jane.smith@email.com",
  "phone": "+1234567890",
  "address": "123 Main St",
  "dateOfBirth": "1990-01-01",
  "panNumber": "ABCDE1234F",
  "aadharNumber": "123456789012"
}
```

### Get Customer by ID
```bash
GET /customers/{id}
Authorization: Bearer {token}
```

## 4. Account Management

### Get All Accounts
```bash
GET /accounts
Authorization: Bearer {token}
```

### Create Account
```bash
POST /accounts?customerId={customerId}
Authorization: Bearer {token}
Content-Type: application/json

{
  "accountType": "SAVINGS"
}
```

### Get Accounts by Customer
```bash
GET /accounts/customer/{customerId}
Authorization: Bearer {token}
```

### Get Account by Account Number
```bash
GET /accounts/number/{accountNumber}
Authorization: Bearer {token}
```

### Deposit Money
```bash
POST /accounts/deposit
Authorization: Bearer {token}
Content-Type: application/json

{
  "accountNumber": "123456789012",
  "amount": 1000.00
}
```

### Withdraw Money
```bash
POST /accounts/withdraw
Authorization: Bearer {token}
Content-Type: application/json

{
  "accountNumber": "123456789012",
  "amount": 500.00
}
```

### Toggle Account Status (Freeze/Unfreeze)
```bash
PUT /accounts/{accountNumber}/toggle-status
Authorization: Bearer {token}
```

## 5. Transaction Management

### Get All Transactions
```bash
GET /transactions
Authorization: Bearer {token}
```

### Get Transactions by Account
```bash
GET /transactions/account/{accountId}
Authorization: Bearer {token}
```

## 6. Admin Operations

### Get All Audit Logs
```bash
GET /admin/audit-logs
Authorization: Bearer {token}
```

### Get Audit Logs by User
```bash
GET /admin/audit-logs/user/{email}
Authorization: Bearer {token}
```

## Testing with cURL

### Login Example
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@bank.com","password":"Admin@123"}'
```

### Get Employees Example
```bash
curl -X GET http://localhost:8080/api/employees \
  -H "Authorization: Bearer YOUR_TOKEN_HERE"
```

### Create Customer Example
```bash
curl -X POST http://localhost:8080/api/customers \
  -H "Authorization: Bearer YOUR_TOKEN_HERE" \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Test",
    "lastName": "User",
    "email": "test@email.com",
    "phone": "+1234567890",
    "address": "123 Test St"
  }'
```

## Testing with Postman

1. Import the endpoints as a collection
2. Set up environment variables:
   - `base_url`: http://localhost:8080/api
   - `token`: (will be set after login)

3. Create a login request and use Tests tab to save token:
```javascript
if (pm.response.code === 200) {
    var jsonData = pm.response.json();
    pm.environment.set("token", jsonData.token);
}
```

4. Use `{{base_url}}` and `Bearer {{token}}` in subsequent requests

## Common HTTP Status Codes

- `200 OK` - Request successful
- `201 Created` - Resource created successfully
- `400 Bad Request` - Invalid input
- `401 Unauthorized` - Authentication required or failed
- `403 Forbidden` - Insufficient permissions
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Server error
