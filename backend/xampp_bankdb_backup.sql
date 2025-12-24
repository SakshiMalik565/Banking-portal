-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 22, 2025 at 01:46 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bankdb`
--

-- Drop existing tables to avoid conflicts
DROP TABLE IF EXISTS `transactions`;
DROP TABLE IF EXISTS `accounts`;
DROP TABLE IF EXISTS `audit_logs`;
DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `employees`;

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `id` bigint(20) NOT NULL,
  `account_number` varchar(255) NOT NULL,
  `account_type` enum('SAVINGS','CURRENT') NOT NULL,
  `balance` decimal(15,2) NOT NULL,
  `branch` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `ifsc_code` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','FROZEN','CLOSED') NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `customer_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`id`, `account_number`, `account_type`, `balance`, `branch`, `created_at`, `ifsc_code`, `status`, `updated_at`, `customer_id`) VALUES
(2, '786769522524', 'SAVINGS', 15000.00, 'Main Branch', '2025-12-18 23:42:24.000000', 'BANK0001234', 'ACTIVE', '2025-12-19 06:14:57.000000', 1),
(3, '493337726674', 'SAVINGS', 9500.00, 'Main Branch', '2025-12-18 23:42:30.000000', 'BANK0001234', 'ACTIVE', '2025-12-19 06:12:30.000000', 3),
(4, '872475448396', 'CURRENT', 0.00, 'Main Branch', '2025-12-19 06:13:49.000000', 'BANK0001234', 'ACTIVE', '2025-12-19 06:13:49.000000', 2);

-- --------------------------------------------------------

--
-- Table structure for table `audit_logs`
--

CREATE TABLE `audit_logs` (
  `id` bigint(20) NOT NULL,
  `action` varchar(255) NOT NULL,
  `details` varchar(1000) DEFAULT NULL,
  `ip_address` varchar(255) DEFAULT NULL,
  `performed_by` varchar(255) NOT NULL,
  `target_entity` varchar(255) DEFAULT NULL,
  `target_id` varchar(255) DEFAULT NULL,
  `timestamp` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `audit_logs`
--

INSERT INTO `audit_logs` (`id`, `action`, `details`, `ip_address`, `performed_by`, `target_entity`, `target_id`, `timestamp`) VALUES
(1, 'CREATE_EMPLOYEE', 'Created employee: EMP5A24B662', NULL, 'admin@bank.com', 'Employee', '3', '2025-12-18 22:59:20.000000'),
(2, 'CREATE_EMPLOYEE', 'Created employee: EMPDC22DAFC', NULL, 'admin@bank.com', 'Employee', '4', '2025-12-18 23:01:10.000000'),
(3, 'DELETE_EMPLOYEE', 'Deleted employee: EMPDC22DAFC', NULL, 'admin@bank.com', 'Employee', '4', '2025-12-18 23:04:51.000000'),
(4, 'CREATE_EMPLOYEE', 'Created employee: EMP6263ED5E', NULL, 'admin@bank.com', 'Employee', '5', '2025-12-18 23:05:30.000000'),
(5, 'CREATE_ACCOUNT', 'Created account: 843125479768', NULL, 'nitesh@gmail.com', 'Account', '1', '2025-12-18 23:06:10.000000'),
(6, 'DEPOSIT', 'Deposited: 500000 to account: 843125479768', NULL, 'nitesh@gmail.com', 'Account', '1', '2025-12-18 23:06:22.000000'),
(7, 'WITHDRAWAL', 'Withdrew: 149 from account: 843125479768', NULL, 'nitesh@gmail.com', 'Account', '1', '2025-12-18 23:06:27.000000'),
(8, 'WITHDRAWAL', 'Withdrew: 499851 from account: 843125479768', NULL, 'nitesh@gmail.com', 'Account', '1', '2025-12-18 23:11:37.000000'),
(9, 'DELETE_ACCOUNT', 'Deleted account: 843125479768', NULL, 'nitesh@gmail.com', 'Account', '1', '2025-12-18 23:11:41.000000'),
(10, 'CREATE_ACCOUNT', 'Created account: 786769522524', NULL, 'admin@bank.com', 'Account', '2', '2025-12-18 23:42:24.000000'),
(11, 'CREATE_ACCOUNT', 'Created account: 493337726674', NULL, 'admin@bank.com', 'Account', '3', '2025-12-18 23:42:30.000000'),
(12, 'DEPOSIT', 'Deposited: 10000 to account: 786769522524', NULL, 'admin@bank.com', 'Account', '2', '2025-12-18 23:42:38.000000'),
(13, 'DEPOSIT', 'Deposited: 20000 to account: 493337726674', NULL, 'admin@bank.com', 'Account', '3', '2025-12-18 23:42:45.000000'),
(14, 'TRANSFER', 'Transferred: 20000 from 493337726674 to 786769522524', NULL, 'admin@bank.com', 'Account', '3', '2025-12-18 23:48:11.000000'),
(15, 'TRANSFER', 'Transferred: 2000 from 786769522524 to 493337726674', NULL, 'admin@bank.com', 'Account', '2', '2025-12-18 23:49:02.000000'),
(16, 'DELETE_EMPLOYEE', 'Deleted employee: EMP6263ED5E', NULL, 'admin@bank.com', 'Employee', '5', '2025-12-19 06:09:32.000000'),
(17, 'CREATE_EMPLOYEE', 'Created employee: EMP968AD366', NULL, 'admin@bank.com', 'Employee', '6', '2025-12-19 06:10:42.000000'),
(18, 'TRANSFER', 'Transferred: 7500 from 786769522524 to 493337726674', NULL, 'admin@bank.com', 'Account', '2', '2025-12-19 06:12:30.000000'),
(19, 'CREATE_ACCOUNT', 'Created account: 872475448396', NULL, 'admin@bank.com', 'Account', '4', '2025-12-19 06:13:49.000000'),
(20, 'WITHDRAWAL', 'Withdrew: 500 from account: 786769522524', NULL, 'admin@bank.com', 'Account', '2', '2025-12-19 06:14:27.000000'),
(21, 'WITHDRAWAL', 'Withdrew: 5000 from account: 786769522524', NULL, 'admin@bank.com', 'Account', '2', '2025-12-19 06:14:57.000000');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` bigint(20) NOT NULL,
  `aadhar_number` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `customer_id` varchar(255) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `pan_number` varchar(255) DEFAULT NULL,
  `phone` varchar(255) NOT NULL,
  `status` enum('ACTIVE','INACTIVE','BLOCKED') NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `aadhar_number`, `address`, `created_at`, `customer_id`, `date_of_birth`, `email`, `first_name`, `last_name`, `pan_number`, `phone`, `status`, `updated_at`) VALUES
(1, '123456789012', '123 Main Street, City', '2025-12-19 04:24:25.000000', 'CUS00000001', '1990-05-15', 'michael.smith@email.com', 'Michael', 'Smith', 'ABCDE1234F', '+1234567893', 'ACTIVE', '2025-12-19 04:24:25.000000'),
(2, '987654321098', '456 Oak Avenue, Town', '2025-12-19 04:24:25.000000', 'CUS00000002', '1985-08-22', 'emily.johnson@email.com', 'Emily', 'Johnson', 'FGHIJ5678K', '+1234567894', 'ACTIVE', '2025-12-19 04:24:25.000000'),
(3, '456789012345', '789 Pine Road, Village', '2025-12-19 04:24:25.000000', 'CUS00000003', '1988-03-10', 'robert.williams@email.com', 'Robert', 'Williams', 'KLMNO9012P', '+1234567895', 'ACTIVE', '2025-12-19 04:24:25.000000'),
(4, '789012345678', '321 Elm Street, City', '2025-12-19 04:24:25.000000', 'CUS00000004', '1992-11-25', 'jennifer.brown@email.com', 'Jennifer', 'Brown', 'QRSTU3456V', '+1234567896', 'ACTIVE', '2025-12-19 04:24:25.000000'),
(5, '012345678901', '654 Maple Avenue, Town', '2025-12-19 04:24:25.000000', 'CUS00000005', '1987-07-18', 'david.davis@email.com', 'David', 'Davis', 'WXYZW7890A', '+1234567897', 'ACTIVE', '2025-12-19 04:24:25.000000');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `employee_id` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `role` enum('ADMIN','MANAGER','CLERK') NOT NULL,
  `status` enum('ACTIVE','SUSPENDED','TERMINATED') NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`id`, `created_at`, `department`, `designation`, `email`, `employee_id`, `first_name`, `last_name`, `password`, `phone`, `role`, `status`, `updated_at`) VALUES
(1, '2025-12-19 03:37:45.000000', 'IT', 'System Administrator', 'admin@bank.com', 'EMP00000001', 'Admin', 'User', '$2a$10$pDp6.nsMvGcSHebuTWSV0O/amYZJVaIxH.GpdIUM1OHMZiVAwt6IG', '+1234567890', 'ADMIN', 'ACTIVE', '2025-12-19 03:37:45.000000'),
(3, '2025-12-18 22:59:20.000000', 'Cashier', 'Head Cashier', 'pratiyush123@gmail.com', 'EMP5A24B662', 'Pratiyush', 'Singh', '$2a$10$I6GBFr8.bYuytyzlq06vbedVnHDcUISdgNVQ2ZcfQTXpZZ33C3IjC', '7380772342', 'MANAGER', 'ACTIVE', '2025-12-18 22:59:20.000000'),
(6, '2025-12-19 06:10:42.000000', 'Cashier', 'Head Cashier', 'sakshimalik234@gmail.com', 'EMP968AD366', 'sakshi', 'malik', '$2a$10$P78PEq7gDADiUUVYXzFDjuUQvyB8unJbEWpq4WwOfmDN59IdKqCCW', '98565679651', 'CLERK', 'ACTIVE', '2025-12-19 06:10:42.000000');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `id` bigint(20) NOT NULL,
  `amount` decimal(15,2) NOT NULL,
  `balance_after` decimal(15,2) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `performed_by` varchar(255) DEFAULT NULL,
  `status` enum('SUCCESS','FAILED','PENDING') NOT NULL,
  `transaction_id` varchar(255) NOT NULL,
  `type` enum('DEPOSIT','WITHDRAWAL','TRANSFER') NOT NULL,
  `account_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`id`, `amount`, `balance_after`, `created_at`, `description`, `performed_by`, `status`, `transaction_id`, `type`, `account_id`) VALUES
(4, 10000.00, 10000.00, '2025-12-18 23:42:38.000000', 'Deposit', 'admin@bank.com', 'SUCCESS', 'TXNF4B59C91-49D', 'DEPOSIT', 2),
(5, 20000.00, 20000.00, '2025-12-18 23:42:45.000000', 'Deposit', 'admin@bank.com', 'SUCCESS', 'TXN2C661111-63F', 'DEPOSIT', 3),
(6, -20000.00, 0.00, '2025-12-18 23:48:11.000000', 'Transfer to 786769522524', 'admin@bank.com', 'SUCCESS', 'TXN8846D5CE-151', 'TRANSFER', 3),
(7, 20000.00, 30000.00, '2025-12-18 23:48:11.000000', 'Transfer from 493337726674', 'admin@bank.com', 'SUCCESS', 'TXNDF6D1E16-FEB', 'TRANSFER', 2),
(8, -2000.00, 28000.00, '2025-12-18 23:49:02.000000', 'Transfer to 493337726674', 'admin@bank.com', 'SUCCESS', 'TXNDC83C01D-EDB', 'TRANSFER', 2),
(9, 2000.00, 2000.00, '2025-12-18 23:49:02.000000', 'Transfer from 786769522524', 'admin@bank.com', 'SUCCESS', 'TXNAD84C230-6F2', 'TRANSFER', 3),
(10, -7500.00, 20500.00, '2025-12-19 06:12:30.000000', 'Transfer to 493337726674', 'admin@bank.com', 'SUCCESS', 'TXN7C4E3E57-A25', 'TRANSFER', 2),
(11, 7500.00, 9500.00, '2025-12-19 06:12:30.000000', 'Transfer from 786769522524', 'admin@bank.com', 'SUCCESS', 'TXN48FFA646-61B', 'TRANSFER', 3),
(12, 500.00, 20000.00, '2025-12-19 06:14:27.000000', 'Withdrawal', 'admin@bank.com', 'SUCCESS', 'TXN618DD284-72C', 'WITHDRAWAL', 2),
(13, 5000.00, 15000.00, '2025-12-19 06:14:57.000000', 'Withdrawal', 'admin@bank.com', 'SUCCESS', 'TXN3AEB4D91-A3C', 'WITHDRAWAL', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6kplolsdtr3slnvx97xsy2kc8` (`account_number`),
  ADD KEY `FKn6x8pdp50os8bq5rbb792upse` (`customer_id`);

--
-- Indexes for table `audit_logs`
--
ALTER TABLE `audit_logs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_n6axkf7qwn8r7s7ce5gso1xpr` (`customer_id`),
  ADD UNIQUE KEY `UK_rfbvkrffamfql7cjmen8v976v` (`email`),
  ADD UNIQUE KEY `UK_q64gq09f9mjsrglv36l3esdrs` (`aadhar_number`),
  ADD UNIQUE KEY `UK_33fklpyee1eppx1mdo65kc5rl` (`pan_number`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_j9xgmd0ya5jmus09o0b8pqrpb` (`email`),
  ADD UNIQUE KEY `UK_ovvvp79dq21byf7svnuekb6iw` (`employee_id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6plyfbm3wy6ds7hongoml5xbk` (`transaction_id`),
  ADD KEY `FK20w7wsg13u9srbq3bd7chfxdh` (`account_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts`
--
ALTER TABLE `accounts`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `audit_logs`
--
ALTER TABLE `audit_logs`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `accounts`
--
ALTER TABLE `accounts`
  ADD CONSTRAINT `FKn6x8pdp50os8bq5rbb792upse` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `FK20w7wsg13u9srbq3bd7chfxdh` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
