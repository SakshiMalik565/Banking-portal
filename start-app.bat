@echo off
echo ========================================
echo  Banking Portal - Starting Application
echo ========================================
echo.

REM Set Java 17
set JAVA_HOME=C:\Program Files\Java\jdk-17
set PATH=%JAVA_HOME%\bin;%PATH%

REM Change to backend directory
cd /d "%~dp0backend"

echo Starting Spring Boot Application...
echo Application will be available at: http://localhost:8080/api
echo Frontend available at: file:///%~dp0frontend/login.html
echo.
echo Login Credentials:
echo   Email: admin@bank.com
echo   Password: Admin@123
echo.
echo Press Ctrl+C to stop the application
echo ========================================
echo.

REM Run the application
java -jar target\banking-portal.jar

pause
