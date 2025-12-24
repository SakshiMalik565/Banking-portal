-- Banking Portal - Database Initialization Script
-- Run this script after the application creates the tables

-- Use the banking database
USE bankdb;

-- Insert default Admin user
-- Email: admin@bank.com
-- Password: Admin@123 (BCrypt hashed)
INSERT INTO employees (employee_id, first_name, last_name, email, password, phone, department, designation, role, status, created_at, updated_at)
VALUES 
('EMPADMIN001', 'System', 'Administrator', 'admin@bank.com', 
 '$2a$10$N8qZmZ6YHqXvIoVxGkBJUOKdG8sYHDqYqKJLHjF8qZJZGqXqZGqZG', 
 '+1234567890', 'IT', 'System Administrator', 'ADMIN', 'ACTIVE', NOW(), NOW())
ON DUPLICATE KEY UPDATE email=email;

-- Insert sample Manager
INSERT INTO employees (employee_id, first_name, last_name, email, password, phone, department, designation, role, status, created_at, updated_at)
VALUES 
('EMPMGR001', 'John', 'Manager', 'manager@bank.com', 
 '$2a$10$N8qZmZ6YHqXvIoVxGkBJUOKdG8sYHDqYqKJLHjF8qZJZGqXqZGqZG', 
 '+1234567891', 'Operations', 'Branch Manager', 'MANAGER', 'ACTIVE', NOW(), NOW())
ON DUPLICATE KEY UPDATE email=email;

-- Insert sample Clerk
INSERT INTO employees (employee_id, first_name, last_name, email, password, phone, department, designation, role, status, created_at, updated_at)
VALUES 
('EMPCLK001', 'Sarah', 'Clerk', 'clerk@bank.com', 
 '$2a$10$N8qZmZ6YHqXvIoVxGkBJUOKdG8sYHDqYqKJLHjF8qZJZGqXqZGqZG', 
 '+1234567892', 'Customer Service', 'Senior Clerk', 'CLERK', 'ACTIVE', NOW(), NOW())
ON DUPLICATE KEY UPDATE email=email;

-- Verify insertions
SELECT employee_id, first_name, last_name, email, role, status 
FROM employees 
WHERE employee_id IN ('EMPADMIN001', 'EMPMGR001', 'EMPCLK001');

-- Display success message
SELECT 'Default users created successfully!' AS Message;
SELECT 'Login Credentials:' AS Info;
SELECT 'Admin: admin@bank.com / Admin@123' AS Admin;
SELECT 'Manager: manager@bank.com / Admin@123' AS Manager;
SELECT 'Clerk: clerk@bank.com / Admin@123' AS Clerk;
