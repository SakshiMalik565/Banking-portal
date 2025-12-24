#!/bin/bash

# Banking Portal - Quick Start Script

echo "=========================================="
echo "Banking Portal - Quick Start"
echo "=========================================="

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven is not installed. Please install Maven first."
    exit 1
fi

echo "✓ Maven found"

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 17 or higher."
    exit 1
fi

echo "✓ Java found"

# Navigate to backend directory
cd backend || exit

echo ""
echo "📦 Building the application..."
mvn clean package -DskipTests

if [ $? -eq 0 ]; then
    echo "✓ Build successful!"
    echo ""
    echo "🚀 Starting the application..."
    echo ""
    java -jar target/banking-portal.jar
else
    echo "❌ Build failed. Please check the errors above."
    exit 1
fi
