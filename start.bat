@echo off
REM Banking Portal - Quick Start Script for Windows

echo ==========================================
echo Banking Portal - Quick Start
echo ==========================================

REM Check if Maven is installed
where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo X Maven is not installed. Please install Maven first.
    pause
    exit /b 1
)

echo + Maven found

REM Check if Java is installed
where java >nul 2>nul
if %errorlevel% neq 0 (
    echo X Java is not installed. Please install Java 17 or higher.
    pause
    exit /b 1
)

echo + Java found

REM Navigate to backend directory
cd backend

echo.
echo Building the application...
call mvn clean package -DskipTests

if %errorlevel% equ 0 (
    echo + Build successful!
    echo.
    echo Starting the application...
    echo.
    java -jar target\banking-portal.jar
) else (
    echo X Build failed. Please check the errors above.
    pause
    exit /b 1
)

pause
