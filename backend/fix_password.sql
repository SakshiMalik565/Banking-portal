USE bankdb;
UPDATE employees SET password = '$2a$10$pDp6.nsMvGcSHebuTWSV0O/amYZJVaIxH.GpdIUM1OHMZiVAwt6IG' WHERE email='admin@bank.com';
SELECT employee_id, email, 'Password fixed - try login now!' as status FROM employees WHERE email='admin@bank.com';
