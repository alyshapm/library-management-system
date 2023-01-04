-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 26, 2022 at 01:46 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userId` int(10) NOT NULL,
  `userFname` varchar(50) NOT NULL,
  `userLname` varchar(50) NOT NULL,
  `birthday` date NOT NULL,
  `email` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `regisDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `admin`
--


CREATE TABLE `admin` (
  `adminId` int(10) NOT NULL,
  `adminFname` varchar(20) NOT NULL,
  `adminLname` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phoneNo` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `adminRole` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `bookId` int(10) NOT NULL,
  `title` varchar(100) NOT NULL,
  `author_name` varchar(50) NOT NULL,
  `publishId` varchar(10) NOT NULL,
  `serialNo` int(13) NOT NULL, -- isbn
  `quantity` int(11) NOT NULL,
  `regisDate` date NOT NULL,
  `regisBy` varchar(50) NOT NULL,
  `availability` varchar(20) NOT NULL,
  `shelfNo` int(11) NOT NULL,
  `floor` int(11) NOT NULL,
  `genre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `book_borrowreturn`
--

CREATE TABLE `book_borrowreturn` (
  `borrowId` int(10) NOT NULL,
  `userId` int(10) NOT NULL,
  `bookId` int(10) NOT NULL,
  `adminId` int(10) NOT NULL,
  `borrowDate` date NOT NULL,
  `returnDate` date NOT NULL,
  `status` varchar(10) NOT NULL,
  `fine` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `publisher`
--

CREATE TABLE `publisher` (
  `publishId` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `city` varchar(20) NOT NULL,
  `country` varchar (20) NOT NULL,
  `zip` int (5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `userFname`, `userLname`, `birthday`, `email`, `address`, `regisDate`) VALUES
(20000101, `John`, `Smith`, `2000-01-01`, `johnsmith@mail.com`, `Jl. Jenderal Sudirman`, `2022-10-05`);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminId`);


-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`bookId`),
  ADD KEY `publishId` (`publishId`);

--
-- Indexes for table `book_borrowreturn`
--
ALTER TABLE `book_borrowreturn`
  ADD PRIMARY KEY (`borrowId`),
  ADD UNIQUE KEY `bookId` (`bookId`),
  ADD KEY `userId` (`userId`,`adminId`),
  ADD KEY `adminId` (`adminId`);

--
-- Indexes for table `publisher`
--
ALTER TABLE `publisher`
  ADD PRIMARY KEY (`publishId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `adminId` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `book_borrowreturn`
--
ALTER TABLE `book_borrowreturn`
  MODIFY `borrowId` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12346;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`bookId`) REFERENCES `book_borrowreturn` (`bookId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `book_borrowreturn`
--
ALTER TABLE `book_borrowreturn`
  ADD CONSTRAINT `book_borrowreturn_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `book_borrowreturn_ibfk_2` FOREIGN KEY (`adminId`) REFERENCES `admin` (`adminId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `publisher`
--
ALTER TABLE `publisher`
  ADD CONSTRAINT `publisher_ibfk_1` FOREIGN KEY (`publishId`) REFERENCES `book` (`publishId`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;