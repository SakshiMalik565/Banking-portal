DELETE FROM employees WHERE email='admin@bank.com';

INSERT INTO employees (employee_id, first_name, last_name, email, password, phone, department, designation, role, status, created_at, updated_at)
VALUES ('EMP00000001', 'Admin', 'User', 'admin@bank.com', '$2a$10$vXJyKZlEfNdPL5bSQXz6s.GtH7VKqLXPXPvzrPJxXpJFZKfCZSHHe', '+1234567890', 'IT', 'System Administrator', 'ADMIN', 'ACTIVE', NOW(), NOW());
