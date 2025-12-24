USE bankdb;
UPDATE employees SET password = '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy' WHERE email='admin@bank.com';
SELECT employee_id, email, 'Password updated' as status FROM employees WHERE email='admin@bank.com';
