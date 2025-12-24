# Banking Portal - Setup & Deployment Guide

## 📋 Prerequisites Checklist

Before starting, ensure you have:

- [ ] Java Development Kit (JDK) 17 or higher
- [ ] Apache Maven 3.6 or higher
- [ ] MySQL 8.0 or higher
- [ ] Git
- [ ] Docker & Docker Compose (optional, for containerized deployment)
- [ ] Modern web browser (Chrome, Firefox, Edge)
- [ ] Text editor or IDE (VS Code, IntelliJ IDEA, Eclipse)

## 🔧 Installation Guide

### Step 1: Install Java JDK 17

#### Windows:
1. Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)
2. Run the installer
3. Set JAVA_HOME environment variable:
   ```
   JAVA_HOME=C:\Program Files\Java\jdk-17
   ```
4. Add to PATH: `%JAVA_HOME%\bin`
5. Verify: `java -version`

#### Linux:
```bash
sudo apt update
sudo apt install openjdk-17-jdk
java -version
```

#### macOS:
```bash
brew install openjdk@17
java -version
```

### Step 2: Install Maven

#### Windows:
1. Download from [Apache Maven](https://maven.apache.org/download.cgi)
2. Extract to `C:\Program Files\Apache\maven`
3. Set M2_HOME: `C:\Program Files\Apache\maven`
4. Add to PATH: `%M2_HOME%\bin`
5. Verify: `mvn -version`

#### Linux:
```bash
sudo apt update
sudo apt install maven
mvn -version
```

#### macOS:
```bash
brew install maven
mvn -version
```

### Step 3: Install MySQL

#### Windows:
1. Download [MySQL Installer](https://dev.mysql.com/downloads/installer/)
2. Run installer and select "Server only" or "Full"
3. Set root password during installation
4. Start MySQL service

#### Linux:
```bash
sudo apt update
sudo apt install mysql-server
sudo systemctl start mysql
sudo mysql_secure_installation
```

#### macOS:
```bash
brew install mysql
brew services start mysql
```

### Step 4: Install Docker (Optional)

#### Windows:
Download and install [Docker Desktop for Windows](https://www.docker.com/products/docker-desktop/)

#### Linux:
```bash
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh
sudo systemctl start docker
sudo docker --version
```

#### macOS:
Download and install [Docker Desktop for Mac](https://www.docker.com/products/docker-desktop/)

## 🚀 Project Setup

### Method 1: Local Development Setup

#### 1. Create MySQL Database
```bash
mysql -u root -p
```

```sql
CREATE DATABASE bankdb;
CREATE USER 'bankuser'@'localhost' IDENTIFIED BY 'bankpass';
GRANT ALL PRIVILEGES ON bankdb.* TO 'bankuser'@'localhost';
FLUSH PRIVILEGES;
EXIT;
```

#### 2. Clone/Navigate to Project
```bash
cd "Banking Portal"
```

#### 3. Configure Application (Optional)
Edit `backend/src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/bankdb
    username: bankuser  # Change if different
    password: bankpass  # Change if different
```

#### 4. Build the Project
```bash
cd backend
mvn clean install
```

#### 5. Run the Application

**Option A: Using Maven**
```bash
mvn spring-boot:run
```

**Option B: Using JAR file**
```bash
java -jar target/banking-portal.jar
```

**Option C: Using Start Script**

Windows:
```bash
cd ..
start.bat
```

Linux/Mac:
```bash
cd ..
chmod +x start.sh
./start.sh
```

#### 6. Access the Application
- Backend API: http://localhost:8080/api
- Frontend: Open `frontend/login.html` in browser

### Method 2: Docker Deployment

#### 1. Build the JAR file
```bash
cd backend
mvn clean package -DskipTests
cd ..
```

#### 2. Start with Docker Compose
```bash
docker-compose up --build
```

Or run in detached mode:
```bash
docker-compose up -d
```

#### 3. View Logs
```bash
docker-compose logs -f
```

#### 4. Stop Services
```bash
docker-compose down
```

#### 5. Remove All Data
```bash
docker-compose down -v
```

## 🧪 Testing the Setup

### 1. Check Backend Health
```bash
curl http://localhost:8080/api/auth/validate
```

### 2. Create Initial Admin User

**Option A: Using SQL**
Run the SQL file:
```bash
mysql -u root -p bankdb < backend/src/main/resources/data.sql
```

**Option B: Manual API Call**
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer {admin-token}" \
  -d '{
    "firstName": "Admin",
    "lastName": "User",
    "email": "admin@bank.com",
    "password": "Admin@123",
    "phone": "+1234567890",
    "department": "IT",
    "designation": "Administrator",
    "role": "ADMIN"
  }'
```

### 3. Login Test
1. Open `frontend/login.html`
2. Enter credentials:
   - Email: `admin@bank.com`
   - Password: `Admin@123`
3. Should redirect to dashboard

## 🔍 Troubleshooting

### Issue: Port Already in Use

**Solution for Port 8080:**
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux/Mac
lsof -i :8080
kill -9 <PID>
```

**Or change port in application.yml:**
```yaml
server:
  port: 8081
```

### Issue: MySQL Connection Failed

**Check MySQL is running:**
```bash
# Windows
sc query MySQL80

# Linux
sudo systemctl status mysql

# Mac
brew services list
```

**Test connection:**
```bash
mysql -u root -p -e "SELECT 1"
```

### Issue: Maven Build Fails

**Clear Maven cache:**
```bash
mvn dependency:purge-local-repository
mvn clean install
```

### Issue: Docker Container Fails

**Check logs:**
```bash
docker-compose logs mysql
docker-compose logs backend
```

**Rebuild containers:**
```bash
docker-compose down -v
docker-compose up --build
```

### Issue: Frontend Can't Connect to Backend

**Check CORS settings in SecurityConfig.java**
**Verify API_BASE_URL in frontend HTML files**

## 📱 Frontend Development Server

For better development experience, use a local server:

### Using Python
```bash
cd frontend
python -m http.server 3000
```
Access: http://localhost:3000

### Using Node.js
```bash
cd frontend
npx http-server -p 3000
```
Access: http://localhost:3000

### Using VS Code
Install "Live Server" extension and click "Go Live"

## 🔐 Security Considerations

### For Production:
1. Change default passwords
2. Update JWT secret in application.yml
3. Enable HTTPS
4. Use environment variables for sensitive data
5. Implement rate limiting
6. Add input sanitization
7. Enable SQL injection protection
8. Set up proper CORS policies

### Environment Variables
Create `.env` file:
```bash
DB_HOST=localhost
DB_PORT=3306
DB_NAME=bankdb
DB_USER=root
DB_PASSWORD=root
JWT_SECRET=your-secret-key-here
```

## 📊 Database Management

### View Tables
```sql
USE bankdb;
SHOW TABLES;
DESCRIBE employees;
```

### Reset Database
```sql
DROP DATABASE bankdb;
CREATE DATABASE bankdb;
```

### Backup Database
```bash
mysqldump -u root -p bankdb > backup.sql
```

### Restore Database
```bash
mysql -u root -p bankdb < backup.sql
```

## 🎯 Next Steps

1. ✅ Set up development environment
2. ✅ Run the application
3. ✅ Create admin user
4. ✅ Test all features
5. 📝 Customize for your needs
6. 🚀 Deploy to production
7. 📚 Read API_TESTING.md for API documentation

## 📞 Support

For issues:
1. Check troubleshooting section
2. Review application logs
3. Check MySQL logs
4. Verify configuration files

## 🎓 Learning Resources

- Spring Boot: https://spring.io/guides
- Spring Security: https://spring.io/projects/spring-security
- JWT: https://jwt.io/
- Docker: https://docs.docker.com/
- Maven: https://maven.apache.org/guides/

---

Happy Coding! 🚀
