USE bankdb;

-- Insert sample customers
INSERT INTO customers (customer_id, first_name, last_name, email, phone, address, date_of_birth, pan_number, aadhar_number, status, created_at, updated_at)
VALUES 
('CUS00000001', 'Michael', 'Smith', 'michael.smith@email.com', '+1234567893', 
 '123 Main Street, City', '1990-05-15', 'ABCDE1234F', '123456789012', 'ACTIVE', NOW(), NOW()),
('CUS00000002', 'Emily', 'Johnson', 'emily.johnson@email.com', '+1234567894', 
 '456 Oak Avenue, Town', '1985-08-22', 'FGHIJ5678K', '987654321098', 'ACTIVE', NOW(), NOW()),
('CUS00000003', 'Robert', 'Williams', 'robert.williams@email.com', '+1234567895', 
 '789 Pine Road, Village', '1988-03-10', 'KLMNO9012P', '456789012345', 'ACTIVE', NOW(), NOW()),
('CUS00000004', 'Jennifer', 'Brown', 'jennifer.brown@email.com', '+1234567896', 
 '321 Elm Street, City', '1992-11-25', 'QRSTU3456V', '789012345678', 'ACTIVE', NOW(), NOW()),
('CUS00000005', 'David', 'Davis', 'david.davis@email.com', '+1234567897', 
 '654 Maple Avenue, Town', '1987-07-18', 'WXYZW7890A', '012345678901', 'ACTIVE', NOW(), NOW());

SELECT COUNT(*) as total_customers FROM customers;
SELECT customer_id, first_name, last_name, email FROM customers;
