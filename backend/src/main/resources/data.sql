-- Create initial admin user
-- Password: Admin@123 (BCrypt hash)
INSERT INTO employees (employee_id, first_name, last_name, email, password, phone, department, designation, role, status, created_at, updated_at)
VALUES ('EMP00000001', 'Admin', 'User', 'admin@bank.com', 
        '$2a$10$vXJyKZlEfNdPL5bSQXz6s.GtH7VKqLXPXPvzrPJxXpJFZKfCZSHHe', 
        '+1234567890', 'IT', 'System Administrator', 'ADMIN', 'ACTIVE', NOW(), NOW());

-- Create sample manager
INSERT INTO employees (employee_id, first_name, last_name, email, password, phone, department, designation, role, status, created_at, updated_at)
VALUES ('EMP00000002', 'John', 'Manager', 'manager@bank.com', 
        '$2a$10$vXJyKZlEfNdPL5bSQXz6s.GtH7VKqLXPXPvzrPJxXpJFZKfCZSHHe', 
        '+1234567891', 'Operations', 'Branch Manager', 'MANAGER', 'ACTIVE', NOW(), NOW());

-- Create sample clerk
INSERT INTO employees (employee_id, first_name, last_name, email, password, phone, department, designation, role, status, created_at, updated_at)
VALUES ('EMP00000003', 'Sarah', 'Clerk', 'clerk@bank.com', 
        '$2a$10$vXJyKZlEfNdPL5bSQXz6s.GtH7VKqLXPXPvzrPJxXpJFZKfCZSHHe', 
        '+1234567892', 'Customer Service', 'Senior Clerk', 'CLERK', 'ACTIVE', NOW(), NOW());

-- Create sample customers
INSERT INTO customers (customer_id, first_name, last_name, email, phone, address, date_of_birth, pan_number, aadhar_number, status, created_at, updated_at)
VALUES ('CUS00000001', 'Michael', 'Smith', 'michael.smith@email.com', '+1234567893', 
        '123 Main Street, City', '1990-05-15', 'ABCDE1234F', '123456789012', 'ACTIVE', NOW(), NOW());

INSERT INTO customers (customer_id, first_name, last_name, email, phone, address, date_of_birth, pan_number, aadhar_number, status, created_at, updated_at)
VALUES ('CUS00000002', 'Emily', 'Johnson', 'emily.johnson@email.com', '+1234567894', 
        '456 Oak Avenue, Town', '1985-08-22', 'FGHIJ5678K', '987654321098', 'ACTIVE', NOW(), NOW());
