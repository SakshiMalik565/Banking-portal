# 🚀 Banking Portal - Quick Reference Guide

## 📱 Application URLs

| Service | URL | Description |
|---------|-----|-------------|
| Backend API | http://localhost:8080/api | REST API endpoints |
| Frontend | Open `frontend/login.html` | Web application |
| MySQL | localhost:3306 | Database server |

## 🔑 Default Credentials

| Role | Email | Password |
|------|-------|----------|
| Admin | admin@bank.com | Admin@123 |
| Manager | manager@bank.com | Admin@123 |
| Clerk | clerk@bank.com | Admin@123 |

## 🛠️ Quick Commands

### Maven Commands
```bash
# Clean build
mvn clean

# Compile only
mvn compile

# Run tests
mvn test

# Package JAR
mvn package

# Skip tests
mvn package -DskipTests

# Run application
mvn spring-boot:run

# Clean and install
mvn clean install
```

### Docker Commands
```bash
# Build and start
docker-compose up --build

# Start in background
docker-compose up -d

# Stop services
docker-compose down

# View logs
docker-compose logs -f

# Remove volumes
docker-compose down -v

# Restart single service
docker-compose restart backend
```

### MySQL Commands
```bash
# Login to MySQL
mysql -u root -p

# Create database
CREATE DATABASE bankdb;

# Show databases
SHOW DATABASES;

# Use database
USE bankdb;

# Show tables
SHOW TABLES;

# Describe table
DESCRIBE employees;

# View data
SELECT * FROM employees;
```

### Git Commands
```bash
# Initialize repository
git init

# Add all files
git add .

# Commit changes
git commit -m "Initial commit"

# Create branch
git checkout -b feature/new-feature

# Push to GitHub
git push origin main

# Pull latest
git pull origin main
```

## 📂 Important Files

| File | Location | Purpose |
|------|----------|---------|
| Main Application | `backend/src/main/java/com/bank/portal/BankingPortalApplication.java` | Entry point |
| Configuration | `backend/src/main/resources/application.yml` | App config |
| Security Config | `backend/src/main/java/com/bank/portal/security/SecurityConfig.java` | Security setup |
| pom.xml | `backend/pom.xml` | Dependencies |
| Dockerfile | `Dockerfile` | Container image |
| Docker Compose | `docker-compose.yml` | Multi-container |

## 🔗 API Endpoints Quick Reference

### Authentication
```
POST   /api/auth/login          - Login
GET    /api/auth/validate       - Validate token
```

### Employees
```
GET    /api/employees           - Get all
POST   /api/employees           - Create (Admin)
PUT    /api/employees/{id}      - Update (Admin)
GET    /api/employees/{id}      - Get by ID
PUT    /api/employees/{id}/status - Change status
```

### Customers
```
GET    /api/customers           - Get all
POST   /api/customers           - Create
PUT    /api/customers/{id}      - Update
GET    /api/customers/{id}      - Get by ID
```

### Accounts
```
GET    /api/accounts            - Get all
POST   /api/accounts            - Create
POST   /api/accounts/deposit    - Deposit
POST   /api/accounts/withdraw   - Withdraw
PUT    /api/accounts/{number}/toggle-status - Toggle
```

### Transactions
```
GET    /api/transactions        - Get all
GET    /api/transactions/account/{id} - Get by account
```

### Admin
```
GET    /api/admin/audit-logs    - Get all logs
GET    /api/admin/audit-logs/user/{email} - Get user logs
```

## 🎨 Frontend Pages

| Page | Purpose |
|------|---------|
| login.html | User login |
| dashboard.html | Main dashboard |
| employees.html | Employee management |
| customers.html | Customer management |
| accounts.html | Account operations |

## 🔐 Security Headers

```javascript
{
  "Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "Content-Type": "application/json"
}
```

## 🗃️ Database Tables

| Table | Description |
|-------|-------------|
| employees | Staff information |
| customers | Customer details |
| accounts | Bank accounts |
| transactions | Transaction records |
| audit_logs | Activity logs |

## 📊 Entity Relationships

```
Customer 1 ─── * Account
Account 1 ─── * Transaction
```

## 🎯 Role Permissions

| Feature | Admin | Manager | Clerk |
|---------|-------|---------|-------|
| View Employees | ✓ | ✓ | ✗ |
| Add Employee | ✓ | ✗ | ✗ |
| Manage Customers | ✓ | ✓ | ✓ |
| Manage Accounts | ✓ | ✓ | ✓ |
| View Audit Logs | ✓ | ✗ | ✗ |
| Freeze Accounts | ✓ | ✓ | ✗ |

## 🐛 Troubleshooting Quick Fixes

### Port 8080 in use
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux/Mac
lsof -i :8080
kill -9 <PID>
```

### MySQL not starting
```bash
# Windows
net start MySQL80

# Linux
sudo systemctl start mysql

# Mac
brew services start mysql
```

### Can't connect to database
```bash
# Check MySQL is running
mysql -u root -p -e "SELECT 1"

# Verify credentials in application.yml
```

### Frontend can't reach backend
```javascript
// Check API_BASE_URL in HTML files
const API_BASE_URL = 'http://localhost:8080/api';
```

### Maven build fails
```bash
# Clear cache
mvn dependency:purge-local-repository
mvn clean install
```

## 📦 Project Dependencies

### Core Spring Boot
- spring-boot-starter-web
- spring-boot-starter-security
- spring-boot-starter-data-jpa
- spring-boot-starter-validation

### Database
- mysql-connector-j

### Security
- jjwt-api
- jjwt-impl
- jjwt-jackson

### Utilities
- lombok
- spring-boot-devtools

## 🔢 Environment Variables

```bash
# Database
DB_HOST=localhost
DB_PORT=3306
DB_NAME=bankdb
DB_USER=root
DB_PASSWORD=root

# JWT
JWT_SECRET=your-secret-key
JWT_EXPIRATION=86400000

# Server
SERVER_PORT=8080
```

## 📱 Testing Checklist

- [ ] Login with admin credentials
- [ ] Create a new employee (Admin)
- [ ] Create a new customer
- [ ] Create a bank account
- [ ] Perform deposit operation
- [ ] Perform withdrawal operation
- [ ] View transaction history
- [ ] Check audit logs (Admin)
- [ ] Test role-based access
- [ ] Logout and login again

## 🎓 Learning Resources

- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [JWT.io](https://jwt.io/)
- [Docker Docs](https://docs.docker.com/)
- [Maven Guide](https://maven.apache.org/guides/)

## 📞 Common HTTP Status Codes

| Code | Meaning |
|------|---------|
| 200 | Success |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 500 | Server Error |

## 🚦 Application Flow

1. User enters credentials → POST /api/auth/login
2. Backend validates → Returns JWT token
3. Frontend stores token → localStorage
4. User makes request → Token in Authorization header
5. Backend validates token → Processes request
6. Action logged → audit_logs table
7. Response returned → Frontend updates UI

## 💾 Backup & Restore

### Backup Database
```bash
mysqldump -u root -p bankdb > backup_$(date +%Y%m%d).sql
```

### Restore Database
```bash
mysql -u root -p bankdb < backup_20241219.sql
```

## 🎯 Performance Tips

1. **Database Indexing**: Add indexes on frequently queried columns
2. **Lazy Loading**: Use @Lazy for large collections
3. **Caching**: Implement Redis for session data
4. **Connection Pool**: Configure HikariCP properly
5. **Query Optimization**: Use JPQL wisely
6. **Frontend**: Minimize API calls

## 📈 Monitoring Queries

```sql
-- Count records
SELECT 
  (SELECT COUNT(*) FROM employees) as employees,
  (SELECT COUNT(*) FROM customers) as customers,
  (SELECT COUNT(*) FROM accounts) as accounts,
  (SELECT COUNT(*) FROM transactions) as transactions;

-- Recent transactions
SELECT * FROM transactions 
ORDER BY created_at DESC 
LIMIT 10;

-- Active accounts by type
SELECT account_type, COUNT(*) 
FROM accounts 
WHERE status = 'ACTIVE' 
GROUP BY account_type;
```

---

## 🎉 Quick Start (30 seconds)

```bash
# 1. Start MySQL
# 2. Navigate to project
cd "Banking Portal/backend"

# 3. Build and run
mvn clean package -DskipTests
java -jar target/banking-portal.jar

# 4. Open frontend
# Open frontend/login.html in browser

# 5. Login
# Email: admin@bank.com
# Password: Admin@123
```

---

**Pro Tip:** Bookmark this file for quick reference during development! 🔖
