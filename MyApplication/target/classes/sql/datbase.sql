-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.0.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for demo_report
CREATE DATABASE IF NOT EXISTS `demo_report` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `demo_report`;

-- Dumping structure for table demo_report.address_type_master
CREATE TABLE IF NOT EXISTS `address_type_master` (
  `ADDRESS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDRESS_SHORT_NAME` varchar(50) NOT NULL,
  `ADDRESS_FULL_NAME` varchar(100) NOT NULL,
  `CREATED_BY` varchar(100) DEFAULT NULL,
  `CREATED_DATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ADDRESS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table demo_report.address_type_master: ~2 rows (approximately)
REPLACE INTO `address_type_master` (`ADDRESS_ID`, `ADDRESS_SHORT_NAME`, `ADDRESS_FULL_NAME`, `CREATED_BY`, `CREATED_DATETIME`) VALUES
	(1, 'AT_TEMP', 'Temporary Address', NULL, NULL),
	(2, 'AT_PERM', 'Permanant Address', NULL, NULL),
	(3, 'AT_NATV', 'Native Address', NULL, NULL);

-- Dumping structure for table demo_report.status_master
CREATE TABLE IF NOT EXISTS `status_master` (
  `STATUS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STATUS_SHORT_NAME` varchar(50) NOT NULL DEFAULT '',
  `STATUS_FULL_NAME` varchar(100) NOT NULL DEFAULT '',
  `CREATED_BY` varchar(100) DEFAULT NULL,
  `CREATED_DATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`STATUS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table demo_report.status_master: ~2 rows (approximately)
REPLACE INTO `status_master` (`STATUS_ID`, `STATUS_SHORT_NAME`, `STATUS_FULL_NAME`, `CREATED_BY`, `CREATED_DATETIME`) VALUES
	(1, 'SM_ACTV', 'Active', NULL, NULL),
	(2, 'SM_INAC', 'In-Active', NULL, NULL),
	(3, 'SM_DELE', 'Deleted', NULL, NULL);

-- Dumping structure for table demo_report.student
CREATE TABLE IF NOT EXISTS `student` (
  `STUDENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STUDENT_NAME` varchar(200) NOT NULL,
  `FATHER_NAME` varchar(100) DEFAULT NULL,
  `MOTHER_NAME` varchar(100) DEFAULT NULL,
  `STUDENT_STATUS` varchar(50) DEFAULT NULL,
  `CREATED_BY` varchar(200) DEFAULT NULL,
  `CREATED_DATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`STUDENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table demo_report.student: ~3 rows (approximately)
REPLACE INTO `student` (`STUDENT_ID`, `STUDENT_NAME`, `FATHER_NAME`, `MOTHER_NAME`, `STUDENT_STATUS`, `CREATED_BY`, `CREATED_DATETIME`) VALUES
	(1, 'Sathiswaran', 'Rathinachalam', 'Eswari', 'SM_ACTV', 'Admin', '2024-04-10 15:48:45'),
	(2, 'sathis', 'Rathinam', 'Esu', 'SM_ACTV', 'Admin', '2024-04-10 15:48:45'),
	(3, 'John', 'peter', 'lee', 'SM_ACTV', 'Admin', '2024-04-10 15:18:35'),
	(4, 'spidy', 'spiderman', 'Merry', 'SM_ACTV', 'Admin', '2024-04-10 15:48:45');

-- Dumping structure for table demo_report.student_address
CREATE TABLE IF NOT EXISTS `student_address` (
  `STUDENT_ADDRESS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STUDENT_ID` int(11) NOT NULL,
  `STU_ADDRESS_1` varchar(100) NOT NULL,
  `STU_ADDRESS_2` varchar(100) DEFAULT NULL,
  `STU_COUNTRY` varchar(50) DEFAULT NULL,
  `STU_ADDRESS_TYPE` varchar(50) DEFAULT NULL,
  `CREATED_BY` varchar(50) DEFAULT NULL,
  `CREATED_DATETIME` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`STUDENT_ADDRESS_ID`),
  KEY `FK__student` (`STUDENT_ID`),
  CONSTRAINT `FK__student` FOREIGN KEY (`STUDENT_ID`) REFERENCES `student` (`STUDENT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table demo_report.student_address: ~2 rows (approximately)
REPLACE INTO `student_address` (`STUDENT_ADDRESS_ID`, `STUDENT_ID`, `STU_ADDRESS_1`, `STU_ADDRESS_2`, `STU_COUNTRY`, `STU_ADDRESS_TYPE`, `CREATED_BY`, `CREATED_DATETIME`) VALUES
	(1, 1, 'Thanthondrimalai,karur', 'Thanthondrimalai,karur', 'India', 'AT_TEMP', 'Admin', '2024-04-10 18:04:27.147'),
	(2, 1, 'Newyork, USA', 'Newyork,India', 'USA', 'AT_PERM', 'Admin', '2024-04-10 18:04:27.147');

-- Dumping structure for table demo_report.student_mark_details
CREATE TABLE IF NOT EXISTS `student_mark_details` (
  `STUDENT_MARK_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STUDENT_ID` int(11) NOT NULL,
  `STU_SEM_DETAILS` varchar(50) DEFAULT NULL,
  `STU_SCORED_MARK` varchar(50) DEFAULT NULL,
  `STU_TOTAL_MARK` varchar(50) DEFAULT NULL,
  `STU_MARK_STATUS` varchar(50) DEFAULT NULL,
  `CREATED_BY` varchar(50) DEFAULT NULL,
  `CREATED_DATETIME` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`STUDENT_MARK_ID`),
  KEY `FK_student_mark_details_student` (`STUDENT_ID`),
  CONSTRAINT `FK_student_mark_details_student` FOREIGN KEY (`STUDENT_ID`) REFERENCES `student` (`STUDENT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table demo_report.student_mark_details: ~2 rows (approximately)
REPLACE INTO `student_mark_details` (`STUDENT_MARK_ID`, `STUDENT_ID`, `STU_SEM_DETAILS`, `STU_SCORED_MARK`, `STU_TOTAL_MARK`, `STU_MARK_STATUS`, `CREATED_BY`, `CREATED_DATETIME`) VALUES
	(1, 1, '1', '2400', '1969', 'SM_ACTV', 'Admin', '2024-04-10 18:02:40.304'),
	(2, 1, '2', '2400', '1969', 'SM_ACTV', 'Admin', '2024-04-10 18:03:07.898');

-- Dumping structure for table demo_report.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_role` (`role`),
  CONSTRAINT `FK_user_role` FOREIGN KEY (`role`) REFERENCES `role` (`Role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table demo_report.user: ~2 rows (approximately)
REPLACE INTO `user` (`id`, `username`, `role`) VALUES
	(1, 'john_doe', 1),
	(2, 'jane_doe', 2);

-- Dumping structure for table demo_report.user_profile
CREATE TABLE IF NOT EXISTS `user_profile` (
  `user_id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email_address` varchar(255) NOT NULL,
  `date_of_birth` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` varchar(20) NOT NULL,
  `city_name` varchar(50) NOT NULL,
  `blood_group` varchar(10) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table demo_report.user_profile: ~130 rows (approximately)
REPLACE INTO `user_profile` (`user_id`, `first_name`, `last_name`, `email_address`, `date_of_birth`, `status`, `city_name`, `blood_group`) VALUES
	(1, 'ADMIN', '001', 'dk@gmail.com', '1997-12-08 00:00:00', 'ACTIVE', 'Palani', 'B+'),
	(2, 'manager', '002', 'test@gmail.com', '1997-12-08 00:00:00', 'ACTIVE', 'Palani', 'B+'),
	(3, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(4, 'Member', '003', 'user1@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(5, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(6, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(7, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(8, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(9, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(10, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(11, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(12, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(13, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(14, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(15, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(16, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(17, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(18, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(19, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(20, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(21, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(22, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(23, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(24, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(25, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(26, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(27, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(28, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(29, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(30, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(31, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(32, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(33, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(34, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(35, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(36, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(37, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(38, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(39, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(40, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(41, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(42, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(43, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(44, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(45, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(46, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(47, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(48, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(49, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(50, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(51, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(52, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(53, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(54, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(55, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(56, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(57, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(58, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(59, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(60, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(61, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(62, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(63, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(64, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(65, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(66, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(67, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(68, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(69, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(70, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(71, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(72, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(73, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(74, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(75, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(76, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(77, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(78, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(79, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(80, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(81, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(82, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(83, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(84, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(85, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(86, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(87, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(88, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(89, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(90, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(91, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(92, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(93, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(94, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(95, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(96, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(97, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(98, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(99, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(100, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(101, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(102, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(103, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(104, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(105, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(106, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(107, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(108, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(109, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(110, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(111, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(112, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(113, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(114, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(115, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(116, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(117, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(118, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(119, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(120, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(121, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(122, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(123, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(124, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(125, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(126, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(127, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(128, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(129, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+'),
	(130, 'Member', '003', 'user@gmail.com', '2000-12-08 00:00:00', 'ACTIVE', 'Dindigul', 'B1+');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
