# Banking Portal - DevOps Project

A comprehensive full-stack banking management portal built with Java Spring Boot, featuring role-based access control, JWT authentication, and complete DevOps integration.

## 🎯 Project Overview

The **Secure Banking Management Portal** is an enterprise-grade web application designed for internal bank operations. It provides secure management of employees, customers, accounts, and transactions with comprehensive audit logging.

## ✨ Features

### 👨‍💼 Employee Management (HRMS Module)
- Employee registration & profile management
- Role-based access (Admin, Manager, Clerk)
- Department assignment
- Employee status management (Active/Suspended/Terminated)
- Secure authentication with JWT

### 🏦 Bank Management
- Customer account creation and management
- Multiple account types (Savings, Current)
- Real-time balance inquiry
- Deposit & withdrawal operations
- Comprehensive transaction history
- Account freeze/unfreeze functionality

### 🔐 Security Features
- Password hashing using BCrypt
- Role-Based Access Control (RBAC)
- JWT-based authentication
- Input validation & SQL injection protection
- Session management
- Complete audit logging

## 🛠️ Technology Stack

### Backend
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Security** - Authentication & Authorization
- **Spring Data JPA** - Database operations
- **JWT** - Token-based authentication
- **MySQL 8.0** - Database

### Frontend
- **HTML5**
- **CSS3**
- **JavaScript (Vanilla)**
- **Tailwind CSS** - Styling

### Build & DevOps
- **Maven** - Build automation
- **Docker** - Containerization
- **Docker Compose** - Multi-container orchestration
- **Git/GitHub** - Version control

## 📁 Project Structure

```
banking-portal/
├── backend/
│   ├── src/main/java/com/bank/portal/
│   │   ├── controller/        # REST API controllers
│   │   ├── service/           # Business logic layer
│   │   ├── repository/        # Data access layer
│   │   ├── model/             # Entity classes
│   │   ├── security/          # Security configuration
│   │   └── dto/               # Data transfer objects
│   ├── src/main/resources/
│   │   └── application.yml    # Application configuration
│   └── pom.xml                # Maven dependencies
│
├── frontend/
│   ├── login.html             # Login page
│   ├── dashboard.html         # Main dashboard
│   ├── employees.html         # Employee management
│   ├── customers.html         # Customer management
│   ├── accounts.html          # Account management
│   └── assets/
│       └── js/
│           └── auth.js        # Authentication utilities
│
├── Dockerfile                 # Backend container image
├── docker-compose.yml         # Full stack orchestration
└── README.md
```

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- Docker & Docker Compose (for containerized deployment)
- Git

### Option 1: Local Setup

#### 1. Clone the Repository
```bash
git clone <repository-url>
cd banking-portal
```

#### 2. Configure Database
Create a MySQL database:
```sql
CREATE DATABASE bankdb;
```

Update `backend/src/main/resources/application.yml` if needed:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/bankdb
    username: root
    password: root
```

#### 3. Build the Backend
```bash
cd backend
mvn clean install
```

#### 4. Run the Application
```bash
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

#### 5. Open Frontend
Open `frontend/login.html` in a web browser, or use a local server:
```bash
cd frontend
# Using Python
python -m http.server 3000

# Using Node.js
npx http-server -p 3000
```

### Option 2: Docker Deployment

#### 1. Build the JAR file
```bash
cd backend
mvn clean package
```

#### 2. Build and Run with Docker Compose
```bash
cd ..
docker-compose up --build
```

This will:
- Start MySQL container on port 3306
- Build and start the backend on port 8080
- Configure networking between containers

#### 3. Access the Application
- Backend API: `http://localhost:8080/api`
- Frontend: Open `frontend/login.html` in browser

### Default Credentials

To create an admin user, you can use the API or insert directly into the database:

**Sample Admin Account:**
```sql
INSERT INTO employees (employee_id, first_name, last_name, email, password, phone, role, status, department, designation)
VALUES ('EMP00000001', 'Admin', 'User', 'admin@bank.com', 
        '$2a$10$XYZ...', '+1234567890', 'ADMIN', 'ACTIVE', 'IT', 'System Admin');
```

Or use: `admin@bank.com` / `Admin@123` (after creating via API)

## 📡 API Endpoints

### Authentication
- `POST /api/auth/login` - Employee login
- `GET /api/auth/validate` - Validate JWT token

### Employees (Admin/Manager)
- `GET /api/employees` - Get all employees
- `POST /api/employees` - Create employee (Admin only)
- `PUT /api/employees/{id}` - Update employee
- `PUT /api/employees/{id}/status` - Change employee status

### Customers
- `GET /api/customers` - Get all customers
- `POST /api/customers` - Create customer
- `GET /api/customers/{id}` - Get customer by ID
- `PUT /api/customers/{id}` - Update customer

### Accounts
- `GET /api/accounts` - Get all accounts
- `POST /api/accounts` - Create account
- `POST /api/accounts/deposit` - Deposit money
- `POST /api/accounts/withdraw` - Withdraw money
- `PUT /api/accounts/{accountNumber}/toggle-status` - Freeze/Unfreeze account

### Transactions
- `GET /api/transactions` - Get all transactions
- `GET /api/transactions/account/{accountId}` - Get account transactions

### Admin
- `GET /api/admin/audit-logs` - Get all audit logs
- `GET /api/admin/audit-logs/user/{email}` - Get user-specific logs

## 🔒 Security Implementation

### Authentication Flow
1. User submits credentials via login form
2. Backend validates credentials
3. JWT token generated and returned
4. Token stored in localStorage
5. Token included in Authorization header for subsequent requests
6. Backend validates token on each request

### Password Security
- Passwords hashed using BCrypt (strength: 10)
- Never stored in plain text
- Secure password validation

### Role-Based Access Control
- **ADMIN**: Full system access
- **MANAGER**: Employee view, full customer/account access
- **CLERK**: Customer and account operations

## 🧪 Testing the Application

### Manual Testing Steps

1. **Login**
   - Navigate to login page
   - Enter credentials
   - Verify JWT token storage

2. **Create Customer**
   - Go to Customers page
   - Click "Add Customer"
   - Fill form and submit

3. **Create Account**
   - Go to Accounts page
   - Click "Create Account"
   - Select customer and account type

4. **Transactions**
   - Select an account
   - Perform deposit/withdrawal
   - Verify balance update

5. **Audit Logs** (Admin only)
   - Navigate to Audit page
   - Verify all actions are logged

## 🐳 Docker Commands

```bash
# Build images
docker-compose build

# Start services
docker-compose up

# Start in background
docker-compose up -d

# View logs
docker-compose logs -f

# Stop services
docker-compose down

# Remove volumes
docker-compose down -v
```

## 🔧 Maven Commands

```bash
# Clean build
mvn clean

# Compile
mvn compile

# Run tests
mvn test

# Package JAR
mvn package

# Install to local repository
mvn install

# Run application
mvn spring-boot:run
```

## 📊 Database Schema

The application automatically creates the following tables:
- `employees` - Employee information
- `customers` - Customer details
- `accounts` - Bank accounts
- `transactions` - Transaction records
- `audit_logs` - System audit trail

## 🎓 Educational Value

This project demonstrates:
- ✅ Full-stack Java development
- ✅ RESTful API design
- ✅ Spring Boot ecosystem
- ✅ Database design & JPA
- ✅ Security best practices
- ✅ Docker containerization
- ✅ Git version control
- ✅ Enterprise architecture patterns
- ✅ DevOps practices

## 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

## 📝 License

This project is created for educational purposes.

## 👨‍💻 Author

Created as a DevOps + Java Full-Stack project for college evaluation and interview preparation.

## 🙏 Acknowledgments

- Spring Boot Documentation
- Tailwind CSS
- Docker Documentation
- MySQL

---

**Note**: This is a demonstration project for educational purposes. For production use, additional security measures, testing, and optimizations would be required.
#   B a n k i n g - p o r t a l  
 