-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 12, 2023 at 06:03 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bms`
--

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `billID` int(11) NOT NULL,
  `customerID` int(11) NOT NULL,
  `issueDate` date NOT NULL,
  `dueDate` date NOT NULL,
  `docType` enum('RECEIPT','INVOICE','BILL') NOT NULL,
  `paymentType` enum('CASH','GCASH','CHECK') NOT NULL,
  `transactionAdded` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bills`
--

INSERT INTO `bills` (`billID`, `customerID`, `issueDate`, `dueDate`, `docType`, `paymentType`, `transactionAdded`) VALUES
(1, 8, '2023-12-02', '2024-01-01', 'INVOICE', 'CASH', '2023-12-01 17:36:32'),
(2, 13, '2023-12-02', '2024-01-01', 'BILL', 'CASH', '2023-12-01 18:28:19'),
(3, 11, '2023-12-02', '2024-01-01', 'BILL', 'CASH', '2023-12-02 03:14:43'),
(4, 11, '2023-12-02', '2024-01-01', 'BILL', 'CASH', '2023-12-02 06:48:26'),
(5, 10, '2023-12-02', '2024-01-01', 'BILL', 'CASH', '2023-12-02 08:22:50'),
(6, 10, '2023-12-02', '2024-01-01', 'BILL', 'CASH', '2023-12-02 14:16:10'),
(7, 11, '2023-12-02', '2024-01-01', 'BILL', 'CASH', '2023-12-02 15:48:56'),
(8, 11, '2023-12-03', '2024-01-02', 'BILL', 'CASH', '2023-12-02 16:18:53'),
(9, 8, '2023-12-03', '2024-01-02', 'RECEIPT', 'CASH', '2023-12-02 20:25:58'),
(10, 10, '2023-12-03', '2024-01-02', 'RECEIPT', 'CASH', '2023-12-02 20:27:55'),
(11, 11, '2023-12-03', '2024-01-02', 'RECEIPT', 'CASH', '2023-12-02 20:33:19'),
(12, 8, '2023-12-03', '2024-01-02', 'BILL', 'CASH', '2023-12-02 20:36:22'),
(13, 11, '2023-12-03', '2024-01-02', 'RECEIPT', 'CASH', '2023-12-03 07:42:50'),
(14, 41, '2023-12-03', '2024-01-02', 'BILL', 'CASH', '2023-12-03 07:49:37'),
(15, 41, '2023-12-03', '2024-01-02', 'RECEIPT', 'CASH', '2023-12-03 07:53:24'),
(16, 41, '2023-12-03', '2024-01-02', 'BILL', 'CASH', '2023-12-03 11:50:11'),
(17, 14, '2023-12-03', '2024-01-02', 'RECEIPT', 'CASH', '2023-12-03 11:51:49'),
(18, 8, '2023-12-03', '2024-01-02', 'RECEIPT', 'CASH', '2023-12-03 11:57:06'),
(19, 10, '2023-12-04', '2024-01-03', 'RECEIPT', 'CASH', '2023-12-04 15:08:15'),
(20, 8, '2023-12-04', '2024-01-03', 'BILL', 'CASH', '2023-12-04 15:12:56'),
(21, 8, '2023-12-04', '2024-01-03', 'INVOICE', 'CASH', '2023-12-04 15:23:29'),
(22, 10, '2023-12-05', '2024-01-04', 'INVOICE', 'CASH', '2023-12-04 16:06:06'),
(23, 13, '2023-12-05', '2024-01-04', 'INVOICE', 'CASH', '2023-12-04 16:11:00'),
(24, 11, '2023-12-05', '2024-01-04', 'BILL', 'CASH', '2023-12-04 16:55:31'),
(25, 8, '2023-12-05', '2024-01-04', 'INVOICE', 'CASH', '2023-12-04 16:56:52'),
(26, 11, '2023-12-05', '2024-01-04', 'BILL', 'CASH', '2023-12-04 16:58:17'),
(27, 11, '2023-12-05', '2024-01-04', 'BILL', 'CASH', '2023-12-04 17:28:55'),
(28, 11, '2023-12-05', '2024-01-04', 'BILL', 'CASH', '2023-12-04 17:30:03'),
(29, 11, '2023-12-05', '2024-01-04', 'BILL', 'CASH', '2023-12-05 09:07:40'),
(30, 10, '2023-12-09', '2024-01-08', 'INVOICE', 'CASH', '2023-12-09 15:31:32'),
(31, 11, '2023-12-10', '2024-01-09', 'BILL', 'CHECK', '2023-12-09 16:26:48'),
(32, 11, '2023-12-10', '2024-01-09', 'BILL', 'CASH', '2023-12-09 16:28:28'),
(33, 14, '2023-12-10', '2024-01-09', 'BILL', 'CHECK', '2023-12-09 16:42:25'),
(34, 11, '2023-12-10', '2024-01-09', 'RECEIPT', 'CASH', '2023-12-09 16:43:25'),
(35, 11, '2023-12-10', '2024-01-09', 'BILL', 'GCASH', '2023-12-09 16:46:27'),
(36, 10, '2023-12-10', '2024-01-09', 'BILL', 'CHECK', '2023-12-09 16:49:07'),
(37, 13, '2023-12-10', '2024-01-09', 'RECEIPT', 'GCASH', '2023-12-09 16:50:25'),
(38, 13, '2023-12-10', '2024-01-09', 'BILL', 'CHECK', '2023-12-09 16:53:19'),
(39, 13, '2023-12-10', '2024-01-09', 'BILL', 'CASH', '2023-12-09 17:02:03'),
(40, 8, '2023-12-10', '2024-01-09', 'RECEIPT', 'CASH', '2023-12-10 02:32:37'),
(41, 8, '2023-12-10', '2024-01-09', 'INVOICE', 'CHECK', '2023-12-10 06:05:10'),
(42, 13, '2023-12-10', '2024-01-09', 'RECEIPT', 'CASH', '2023-12-10 06:06:02'),
(43, 8, '2023-12-10', '2024-01-09', 'INVOICE', 'CASH', '2023-12-10 07:11:31'),
(44, 8, '2023-12-10', '2024-01-09', 'RECEIPT', 'CASH', '2023-12-10 07:17:46'),
(45, 8, '2023-12-10', '2024-01-09', 'RECEIPT', 'CASH', '2023-12-10 07:21:39');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `customerID` int(11) NOT NULL,
  `creationDate` datetime NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `contactNumber` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `town` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `postal` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customerID`, `creationDate`, `firstName`, `lastName`, `contactNumber`, `email`, `address`, `town`, `country`, `postal`) VALUES
(8, '2023-11-24 01:30:02', 'japher', 'saqs', '2147483647', 'poks@gmail.com', 'Karakura', '1234 Thing St.', 'KZ', '1234'),
(10, '2023-11-19 03:03:11', 'Fluffy', 'Formyx', '1234567894', 'fluffy@mail.com', 'king', 'queensville', 'USA', '1324'),
(11, '2023-11-19 03:16:26', 'Kang', 'Seulgi', '09994498312', 'rvevers@mail.com', 'Ikuranade', 'Itaewon 1234', 'SoKor', '1234'),
(13, '2023-11-24 00:57:51', 'chunchun', 'Exarta', '09568899449', 'miner@mail.com', 'Andrage 7890 Kung St.', 'Namable', 'Congo', '4561'),
(14, '2023-11-19 03:30:01', 'Stephen', 'Stange', '09664488332', 'trybeyonce@mail.com', 'Harskanaurgh', 'Queensville 4568 ANdrago St.', 'Poland', '4561'),
(15, '2023-11-19 03:39:27', 'Loki', 'Laufeyson', '09569834562', 'keylimepie@mail.com', 'TVA', 'TVA Bldg 678', 'Cosmos', '4561'),
(28, '2023-11-22 22:23:34', 'Jasper', 'Villanueva', '09559877894', 'nyonyiks@gmail.com', 'Matarik', 'Bagong Silang po Kanan estudyante', 'Philippines', '1234'),
(29, '2023-11-22 22:38:45', 'Christian', 'Serrano', '01327894562', 'tiantian@mail.com', 'Malapit ata sa may paresan', 'Saranai daw don sa malayo lagpas ng UCC', 'Philippines', '4561'),
(30, '2023-11-22 22:42:42', 'Gwyneth', 'Uy', '04561237892', 'pinagawangplaccards@mail.com', 'Sa lagpas ng uhh Brgy. 171 na lang', 'Sa ano yon eh lagpas ng St.Benedict ata diko na tanda sowre', 'Philippines', '7844'),
(31, '2023-11-23 12:29:01', 'Stephen', 'Strange', '01234567895', 'darkholdpeeker@mail.com', 'New York Sanctum', 'The Multiverse', 'USA', '4511'),
(35, '2023-11-25 12:50:01', 'Kerang', 'Chuners', '09876543211', 'munerchi@mail.com', 'there', 'therethere', 'Philippines', '7890'),
(36, '2023-11-25 13:07:54', 'Kapre', 'Tree', 'jkjjk', 'kjgjdrjk', 'jkjj', 'jjj', 'jkjk', 'jkj'),
(40, '2023-11-25 17:37:24', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k'),
(41, '2023-12-03 15:48:45', 'Rashlyy', 'Estremadura', '0987655431536', 'inuman@mail.com', 'Caloocan City', 'Bagong Silang yung may alak please', 'Philippines', '1428'),
(42, '2023-12-03 19:46:29', 'Jilen', 'Arquilita', '12345612349', 'lilbeomboom@gmail.com', ' Caloocan City', '1291 Sampaloc St. Camarin', 'Ph', '1400'),
(45, '2023-12-05 16:53:13', 'jklk', 'jklkj', 'kljlk', 'kljlkj', 'Rose Bldg Rm 809', 'Caloocan City', 'Philippines', '4567'),
(47, '2023-12-05 16:59:33', 'Seiffer', 'Salupado', '64654654', 'Mailkjflskejflk', 'Rose Bldg Rm 890 Gray Rd.', 'Caloocan City', 'Philippines', '4567'),
(48, '2023-12-05 17:03:04', 'Seiffer', 'Salupado', '4567', 'mail@mail.com', 'Rose Rm. Gray Bldg. Greenwich Rd.', 'Caloocan City', 'Philippines', '4567');

-- --------------------------------------------------------

--
-- Table structure for table `lineitems`
--

CREATE TABLE `lineitems` (
  `lineItemID` int(11) NOT NULL,
  `billID` int(11) NOT NULL,
  `productID` int(11) NOT NULL,
  `productName` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  `unitPrice` decimal(12,2) NOT NULL,
  `lineItemTotal` decimal(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lineitems`
--

INSERT INTO `lineitems` (`lineItemID`, `billID`, `productID`, `productName`, `quantity`, `unitPrice`, `lineItemTotal`) VALUES
(1, 6, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(2, 6, 1, 'Taylor Chuck ConverseXPlay', 3, 1200.00, 3600.00),
(3, 6, 1, 'Taylor Chuck ConverseXPlay', 4, 1200.00, 4800.00),
(4, 7, 1, 'Taylor Chuck ConverseXPlay', 6, 1200.00, 7200.00),
(5, 7, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(6, 8, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(7, 8, 1, 'Taylor Chuck ConverseXPlay', 2, 1200.00, 2400.00),
(8, 9, 1, 'Taylor Chuck ConverseXPlay', 3, 1200.00, 3600.00),
(9, 9, 1, 'Taylor Chuck ConverseXPlay', 5, 1200.00, 6000.00),
(10, 9, 1, 'Taylor Chuck ConverseXPlay', 3, 1200.00, 3600.00),
(11, 10, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(12, 10, 1, 'Taylor Chuck ConverseXPlay', 2, 1200.00, 2400.00),
(13, 11, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(14, 11, 1, 'Taylor Chuck ConverseXPlay', 3, 1200.00, 3600.00),
(15, 12, 1, 'Taylor Chuck ConverseXPlay', 12, 1200.00, 14400.00),
(16, 12, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(17, 12, 1, 'Taylor Chuck ConverseXPlay', 2, 1200.00, 2400.00),
(18, 12, 1, 'Taylor Chuck ConverseXPlay', 2, 1200.00, 2400.00),
(19, 12, 1, 'Taylor Chuck ConverseXPlay', 2, 1200.00, 2400.00),
(20, 13, 1, 'Taylor Chuck ConverseXPlay', 2, 1200.00, 2400.00),
(21, 13, 1, 'Taylor Chuck ConverseXPlay', 2, 1200.00, 2400.00),
(22, 14, 1, 'Taylor Chuck ConverseXPlay', 2, 1200.00, 2400.00),
(23, 15, 1, 'Taylor Chuck ConverseXPlay', 2, 1200.00, 2400.00),
(24, 15, 1, 'Taylor Chuck ConverseXPlay', 12, 1200.00, 14400.00),
(25, 15, 1, 'Taylor Chuck ConverseXPlay', 12, 1200.00, 14400.00),
(26, 15, 1, 'Taylor Chuck ConverseXPlay', 12, 1200.00, 14400.00),
(27, 16, 1, 'Taylor Chuck ConverseXPlay', 12, 1200.00, 14400.00),
(28, 16, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(29, 16, 1, 'Taylor Chuck ConverseXPlay', 3, 1200.00, 3600.00),
(30, 16, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(31, 16, 1, 'Taylor Chuck ConverseXPlay', 3, 1200.00, 3600.00),
(32, 17, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(33, 17, 1, 'Taylor Chuck ConverseXPlay', 3, 1200.00, 3600.00),
(34, 18, 1, 'Taylor Chuck ConverseXPlay', 3, 1200.00, 3600.00),
(35, 19, 1, 'Taylor Chuck ConverseXPlay', 2, 1200.00, 2400.00),
(36, 20, 1, 'Taylor Chuck ConverseXPlay', 2, 1200.00, 2400.00),
(37, 21, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(38, 22, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(39, 23, 1, 'Taylor Chuck ConverseXPlay', 3, 1200.00, 3600.00),
(40, 24, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(41, 25, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(42, 25, 1, 'Taylor Chuck ConverseXPlay', 2, 1200.00, 2400.00),
(43, 26, 1, 'Taylor Chuck ConverseXPlay', 2, 1200.00, 2400.00),
(44, 27, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(45, 27, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(46, 27, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(47, 27, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(48, 27, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(49, 27, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(50, 27, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(51, 27, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(52, 27, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(53, 27, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(54, 27, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(55, 27, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(56, 27, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(57, 27, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(58, 27, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(59, 27, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(60, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(61, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(62, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(63, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(64, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(65, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(66, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(67, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(68, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(69, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(70, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(71, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(72, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(73, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(74, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(75, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(76, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(77, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(78, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(79, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(80, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(81, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(82, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(83, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(84, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(85, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(86, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(87, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(88, 28, 1, 'Taylor Chuck ConverseXPlay', 1, 1200.00, 1200.00),
(89, 29, 1, 'Taylor Chuck ConverseXPlay', 3, 1200.00, 3600.00),
(90, 30, 10001, 'Nike Air Force 1 \'07 All White', 50, 5495.00, 274750.00),
(91, 31, 10002, 'Nike Air Force 1 \'07 All White', 2, 5280.00, 10560.00),
(92, 32, 10002, 'Nike Air Force 1 \'07 All White', 5, 5280.00, 26400.00),
(93, 33, 10002, 'Nike Air Force 1 \'07 All White', 5, 5280.00, 26400.00),
(94, 34, 10004, 'Nike Air Force 1 \'07 All Black', 3, 5490.00, 16470.00),
(95, 35, 10002, 'Nike Air Force 1 \'07 All White', 2, 5280.00, 10560.00),
(96, 36, 10002, 'Nike Air Force 1 \'07 All White', 2, 5280.00, 10560.00),
(97, 37, 10004, 'Nike Air Force 1 \'07 All Black', 2, 5490.00, 10980.00),
(98, 38, 10004, 'Nike Air Force 1 \'07 All Black', 5, 5490.00, 27450.00),
(99, 39, 10002, 'Nike Air Force 1 \'07 All White', 2, 5280.00, 10560.00),
(100, 40, 10002, 'Nike Air Force 1 \'07 All White', 1, 5280.00, 5280.00),
(101, 41, 10002, 'Nike Air Force 1 \'07 All White', 2, 5280.00, 10560.00),
(102, 42, 10002, 'Nike Air Force 1 \'07 All White', 2, 5280.00, 10560.00),
(103, 43, 10002, 'Nike Air Force 1 \'07 All White', 2, 5280.00, 10560.00),
(104, 44, 10002, 'Nike Air Force 1 \'07 All White', 2, 5280.00, 10560.00),
(105, 45, 10002, 'Nike Air Force 1 \'07 All White', 10, 5280.00, 52800.00);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `userID` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `userType` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`userID`, `username`, `password`, `userType`) VALUES
(1, 'admin', 'admin', 'Admin'),
(2, 'employee1', 'employee1', 'Employee'),
(3, 'group4', 'group4', 'Employee'),
(4, 'group1', 'group1', 'Employee'),
(5, 'twentyone', 'twentyone', 'Employee'),
(6, 'charles', 'charles', 'Employee'),
(8, 'group412', 'group4656', 'Employee');

-- --------------------------------------------------------

--
-- Table structure for table `productcategory`
--

CREATE TABLE `productcategory` (
  `ProductID` int(11) NOT NULL,
  `parentType` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `Brand` varchar(255) NOT NULL,
  `otherAttributes` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `productcategory`
--

INSERT INTO `productcategory` (`ProductID`, `parentType`, `type`, `Brand`, `otherAttributes`) VALUES
(10002, 'Footwear', 'Shoes', 'Nike', 'Men'),
(10003, 'Footwear', 'Shoes', 'Nike', 'Men'),
(10004, 'Footwear', 'Shoes', 'Nike', 'Men'),
(10005, 'Footwear', 'Flops', 'Nike', 'Women'),
(10006, 'Footwear', 'Shoes', 'Nike', 'Kids');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `ProductID` int(11) NOT NULL,
  `ProductName` varchar(255) NOT NULL,
  `Price` double NOT NULL,
  `Stocks` int(11) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `Image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`ProductID`, `ProductName`, `Price`, `Stocks`, `Description`, `Image`) VALUES
(10002, 'Nike Air Force 1 _07 All Yellow Pro Tech', 5490, 100, 'Nike Air Force 1 _07 All Yellow Pro Tech', 'file:/C:/Users/User/Documents/NetBeansProjects/BillingManagementSystem/ProductsGraphic/Mens%20Shoes/Air%20Force%201/Nike%20Air%20Force%201%20_07%20All%20Yellow%20Pro%20Tech.PNG'),
(10003, 'Nike Air Force 1 _07 All Black Pro Tech', 5000, 50, 'Nike Air Force 1 _07 All Black Pro Tech', 'file:/C:/Users/User/Documents/NetBeansProjects/BillingManagementSystem/ProductsGraphic/Mens%20Shoes/Air%20Force%201/Nike%20Air%20Force%201%20_07%20All%20Black%20Pro%20Tech.PNG'),
(10004, 'Nike Air Force 1 Low by you 5', 4980, 50, 'Nike Air Force 1 Low by you 5', 'file:/C:/Users/User/Documents/NetBeansProjects/BillingManagementSystem/ProductsGraphic/Mens%20Shoes/Air%20Force%201/Nike%20Air%20Force%201%20Low%20by%20you%205.PNG'),
(10005, 'Nike Air Max Koko', 2550, 100, 'Nike Sandals/Flops', 'file:/C:/Users/User/Documents/NetBeansProjects/BillingManagementSystem/ProductsGraphic/Women_s%20Sandals/Nike%20Air%20Max%20Koko.png'),
(10006, 'Air Jordan 1 Low', 3589, 50, 'Air Jordan !', 'file:/C:/Users/User/Documents/NetBeansProjects/BillingManagementSystem/ProductsGraphic/Kids%20Shoes/Air%20Jordan%201%20Low.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`billID`),
  ADD KEY `FK_customerID` (`customerID`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customerID`);

--
-- Indexes for table `lineitems`
--
ALTER TABLE `lineitems`
  ADD PRIMARY KEY (`lineItemID`),
  ADD KEY `FK_billID` (`billID`),
  ADD KEY `FK_productID` (`productID`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`userID`);

--
-- Indexes for table `productcategory`
--
ALTER TABLE `productcategory`
  ADD PRIMARY KEY (`ProductID`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`ProductID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `billID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `customerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT for table `lineitems`
--
ALTER TABLE `lineitems`
  MODIFY `lineItemID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=106;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `ProductID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10007;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bills`
--
ALTER TABLE `bills`
  ADD CONSTRAINT `FK_customerID` FOREIGN KEY (`customerID`) REFERENCES `customers` (`customerID`);

--
-- Constraints for table `lineitems`
--
ALTER TABLE `lineitems`
  ADD CONSTRAINT `FK_billID` FOREIGN KEY (`billID`) REFERENCES `bills` (`billID`);

--
-- Constraints for table `productcategory`
--
ALTER TABLE `productcategory`
  ADD CONSTRAINT `fk_products` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
