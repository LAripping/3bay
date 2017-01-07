-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 13, 2015 at 01:31 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `SocialSecurityNumber` varchar(9) NOT NULL,
  `Username` varchar(32) NOT NULL,
  `Passwords` varchar(50) NOT NULL,
  `FirstName` varchar(32) DEFAULT NULL,
  `LastName` varchar(32) DEFAULT NULL,
  `Email` varchar(50) NOT NULL,
  `MobileNumber` varchar(10) DEFAULT NULL,
  `Country` varchar(50) DEFAULT NULL,
  `City` varchar(32) DEFAULT NULL,
  `Street` varchar(32) DEFAULT NULL,
  `StreetNumber` int(11) DEFAULT NULL,
  `PostalCode` varchar(10) DEFAULT NULL,
  `isConfirmed` tinyint(1) NOT NULL,
  `Photo` varchar(45) DEFAULT NULL,
  `SellerRating` int(2) DEFAULT '0',
  `BuyerRating` int(2) DEFAULT '0',
  PRIMARY KEY (`SocialSecurityNumber`),
  UNIQUE KEY `Username_UNIQUE` (`Username`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`SocialSecurityNumber`, `Username`, `Passwords`, `FirstName`, `LastName`, `Email`, `MobileNumber`, `Country`, `City`, `Street`, `StreetNumber`, `PostalCode`, `isConfirmed`, `Photo`, `SellerRating`, `BuyerRating`) VALUES
('000000000', 'Admin', 'admin', 'Chuck', 'Norris', 'killer@home.com', '6966666666', 'Greece', 'Heaven', 'Stairway', 2, '11111', 1, NULL, NULL, NULL),
('123456711', 'Phil12', 'cap', 'Phil', 'Coulson', 'phil@shield.us', '2362627833', 'Haiti', 'Bus', 'Air', 50, '32687', 1, NULL, 3, 3),
('123456712', 'Emma20', 'magic', 'Emma', 'Swan', 'emma@dark.ma', '6969696969', 'United States', 'Storybrooke', 'Magic', 156, '24622', 1, NULL, 0, 0),
('123456789', 'Leo13', 'qwert', 'Leo', 'Tsa', 'tsa@camp.com', '6975325626', 'Greece', 'Athens', 'bla', 12, '1255', 1, NULL, 2, 4),
('129841111', 'Jax832', 'bla', 'Jackson', 'Teller', 'jax@charming.com', '2926207532', 'United States', 'Charming', 'Club', 22, '3267', 1, NULL, 3, 3),
('147852369', 'Tyrion21', 'tyrion', 'Tyrion', 'Lannister', 'tyrion@mereen.es', '6912416137', 'Haiti', 'Mereen', 'Harpy', 31, '1255', 1, NULL, 4, 3),
('192831925', 'Walter52', 'chem', 'Walter', 'White', 'heisenberg@meth.com', '6975325626', 'United States', 'Albuquerque', 'Breaking', 1231, '32627', 0, NULL, 2, 2),
('246237893', 'Ele14', 'qwe', 'Ele', 'Mar', 'ele@as.com', '2356', 'Greece', 'a', 'a', 12, 'a', 1, NULL, 3, 1),
('26274', 'Alex21', 'qaz', 'Alex', 'Zeakis', 'alex@aga.com', '13566', 'Greece', 'Athens', 'Strimonos', 25, '2366', 1, NULL, 5, 2),
('789852123', 'Harry11', 'Hedwig', 'Harry', 'Potter', 'harry+ginny@hogwarts.com', '6913516813', 'United Kingdom', 'London', 'Privet Drive', 4, '13667', 1, NULL, 2, 3),
('875432547', 'Sherlock92', 'qwe', 'Sherlock', 'Holmes', 'sherlock@home.com', '6912136273', 'United Kingdom', 'London', 'Baker', 221, '13612', 1, NULL, 4, 2),
('912345678', 'Ned88', 'qwe', 'Eddard', 'Stark', 'winter@is.com', '6975325626', 'Haiti', 'Winterfell', 'King', 24, '1255', 1, NULL, 5, 1),
('999999999', 'Guest', 'guest', 'Guest', 'Guest', 'Guest@Guest.guest', '6666999933', 'Greece', 'Athens', 'Guest', 50, '29123', 1, NULL, 0, 0),
('Lee', 'Lee', 'Lee', 'Lee', 'Lee', 'Lee@Lee.Lee', 'Lee', 'Brazil', 'Lee', 'Lee', 2, 'Lee', 1, NULL, 0, 4);

-- --------------------------------------------------------

--
-- Table structure for table `bids`
--

CREATE TABLE IF NOT EXISTS `bids` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ItemId` int(11) NOT NULL,
  `Bid` int(11) NOT NULL,
  `Date` datetime NOT NULL,
  `Bidder` varchar(9) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ItemId` (`ItemId`),
  KEY `Bidder` (`Bidder`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=31 ;

--
-- Dumping data for table `bids`
--

INSERT INTO `bids` (`ID`, `ItemId`, `Bid`, `Date`, `Bidder`) VALUES
(2, 78, 152, '2015-08-26 18:32:50', '26274'),
(3, 79, 1583, '2015-08-28 18:48:56', '26274'),
(4, 101, 30, '2015-09-01 18:09:31', '123456789'),
(5, 101, 45, '2015-09-01 18:11:44', '246237893'),
(6, 84, 20, '2015-09-04 21:12:53', '26274'),
(7, 103, 20, '2015-09-06 14:38:10', '26274'),
(8, 104, 20, '2015-09-06 14:38:28', '26274'),
(9, 105, 20, '2015-09-06 14:38:45', '26274'),
(10, 106, 20, '2015-09-06 14:39:04', '26274'),
(11, 107, 20, '2015-09-06 14:39:20', '26274'),
(12, 103, 25, '2015-09-06 14:44:48', '123456789'),
(13, 105, 25, '2015-09-06 14:45:12', '123456789'),
(14, 107, 25, '2015-09-06 14:45:31', '123456789'),
(15, 108, 25, '2015-09-06 14:45:57', '123456789'),
(16, 103, 30, '2015-09-06 14:48:35', '246237893'),
(17, 104, 30, '2015-09-06 14:49:01', '246237893'),
(18, 108, 30, '2015-09-06 14:49:25', '246237893'),
(19, 109, 30, '2015-09-06 14:49:44', '246237893'),
(20, 107, 30, '2015-09-06 14:51:05', '129841111'),
(21, 106, 30, '2015-09-06 14:51:27', '129841111'),
(22, 104, 40, '2015-09-06 14:51:49', '129841111'),
(23, 110, 40, '2015-09-06 14:52:16', '129841111'),
(24, 106, 50, '2015-09-06 14:54:30', '123456712'),
(25, 105, 50, '2015-09-06 14:54:49', '123456712'),
(26, 104, 50, '2015-09-06 14:55:09', '123456712'),
(27, 103, 50, '2015-09-06 14:55:29', '123456712'),
(28, 112, 50, '2015-09-06 14:56:10', '123456712'),
(30, 82, 30000, '2015-09-13 13:36:57', '26274');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ItemId` int(11) NOT NULL,
  `Category` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ItemId` (`ItemId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=80 ;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`ID`, `ItemId`, `Category`) VALUES
(1, 67, 'Baby'),
(2, 67, 'Cameras & Photo'),
(3, 68, 'Gift Cards & Coupons'),
(4, 68, 'Health & Beauty'),
(5, 69, 'Baby'),
(6, 69, 'Stamps'),
(9, 71, 'Sporting Goods'),
(10, 71, 'Toys & Hobbies'),
(11, 71, 'Video Games & Consoles'),
(12, 72, 'Baby'),
(13, 72, 'Clothing, Shoes & Accessories'),
(14, 73, 'Business & Industrial'),
(15, 73, 'Pet Supplies'),
(16, 73, 'Pottery & Glass'),
(17, 77, 'Books'),
(18, 77, 'Sporting Goods'),
(19, 77, 'Stamps'),
(20, 78, 'Baby'),
(21, 78, 'Pottery & Glass'),
(22, 78, 'Tickets & Experiences'),
(23, 80, 'Baby'),
(24, 80, 'Clothing, Shoes & Accessories'),
(25, 80, 'Entertainment Memorabilia'),
(26, 81, 'Motors'),
(27, 81, 'Pottery & Glass'),
(28, 81, 'Toys & Hobbies'),
(29, 82, 'Home & Garden'),
(30, 82, 'Pet Supplies'),
(31, 84, 'Baby'),
(32, 84, 'Stamps'),
(33, 84, 'Travel'),
(34, 85, 'DVDs & Movies'),
(35, 85, 'Music'),
(44, 96, 'Books'),
(45, 96, 'Dolls & Bears'),
(46, 97, 'Antiques'),
(47, 97, 'Art'),
(48, 98, 'Antiques'),
(49, 98, 'Art'),
(50, 100, 'Antiques'),
(51, 100, 'Art'),
(52, 101, 'Antiques'),
(53, 101, 'Art'),
(54, 102, 'Music'),
(55, 102, 'Musical Instruments & Gear'),
(56, 103, 'Pet Supplies'),
(57, 103, 'Specialty Services'),
(58, 104, 'Entertainment Memorabilia'),
(59, 104, 'Pet Supplies'),
(60, 104, 'Sporting Goods'),
(61, 105, 'Gift Cards & Coupons'),
(62, 105, 'Musical Instruments & Gear'),
(63, 105, 'Tickets & Experiences'),
(64, 106, 'Entertainment Memorabilia'),
(65, 106, 'Music'),
(66, 106, 'Sports Mem, Cards & Fan Shop'),
(67, 107, 'Dolls & Bears'),
(68, 107, 'Pottery & Glass'),
(69, 107, 'Travel'),
(70, 108, 'Entertainment Memorabilia'),
(71, 108, 'Home & Garden'),
(72, 109, 'Entertainment Memorabilia'),
(73, 109, 'Home & Garden'),
(74, 110, 'Entertainment Memorabilia'),
(75, 110, 'Home & Garden'),
(76, 111, 'Entertainment Memorabilia'),
(77, 111, 'Home & Garden'),
(78, 112, 'Entertainment Memorabilia'),
(79, 112, 'Home & Garden');

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE IF NOT EXISTS `item` (
  `ItemId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` tinytext NOT NULL,
  `Description` text,
  `CurrentBid` int(11) DEFAULT NULL,
  `BuyNow` int(11) DEFAULT NULL,
  `FirstBid` int(11) NOT NULL,
  `StartTime` datetime NOT NULL,
  `EndTime` datetime NOT NULL,
  `Seller` varchar(9) NOT NULL,
  `isActive` tinyint(1) NOT NULL,
  `isSold` tinyint(1) NOT NULL,
  PRIMARY KEY (`ItemId`),
  KEY `fk_Item_User2_idx` (`Seller`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=113 ;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`ItemId`, `Name`, `Description`, `CurrentBid`, `BuyNow`, `FirstBid`, `StartTime`, `EndTime`, `Seller`, `isActive`, `isSold`) VALUES
(1, 'PlayStation 4', 'New Playstation', 0, NULL, 38, '2015-08-06 00:00:00', '2015-08-08 00:00:00', '26274', 0, 0),
(2, 'Xbox 360', NULL, 0, 40, 20, '2015-08-06 00:00:00', '2015-08-08 00:00:00', '26274', 0, 0),
(24, 'Nintendo', 'Old Nintendo', 0, 16, 193, '2015-08-09 20:28:41', '2015-08-22 00:00:00', '246237893', 0, 0),
(60, 'za', '', 0, 15, 2376, '2015-08-10 17:56:16', '2015-08-22 00:00:00', '26274', 1, 1),
(61, 'wa', '', 0, 16, 24, '2015-08-10 20:22:10', '2015-08-27 00:00:00', '26274', 1, 0),
(67, 'nanana', 'sadafasadafa', 0, 1515, 2626, '2015-08-25 20:49:15', '2015-08-28 00:00:00', '26274', 0, 0),
(68, 'Bla', 'bla', 0, 25, 267, '2015-08-12 00:33:00', '2015-08-25 00:00:00', '26274', 1, 0),
(69, 'wa', '', 0, 16, 267, '2015-08-12 12:59:44', '2015-08-31 00:00:00', '246237893', 1, 0),
(71, 'na', 'Lorem ipsum', 0, 12, 267, '2015-08-20 13:52:48', '2015-08-22 00:00:00', '26274', 1, 0),
(72, 'Jonita Pants', 'Nice pants from Jonita', 0, 10, 2, '2015-08-22 17:25:24', '2015-08-29 00:00:00', '26274', 0, 0),
(73, 'Shield1', '', 0, 12, 23, '2015-08-23 14:42:13', '2015-11-26 00:00:00', '123456711', 1, 0),
(77, 'Shield2', '', 0, 15, 152, '2015-08-23 14:46:31', '2015-10-13 00:00:00', '123456711', 0, 0),
(78, 'Auc1', '', 152, 152, 1523, '2015-08-23 14:48:21', '2015-08-27 00:00:00', '123456789', 1, 1),
(79, 'Auc2', '', 1583, 155, 1582, '2015-08-23 14:48:21', '2015-08-29 00:00:00', '123456789', 1, 0),
(80, 'Harley1', '', 0, 25, 3652, '2015-08-23 14:50:08', '2015-08-26 00:00:00', '129841111', 1, 0),
(81, 'Harley2', '', 0, 2662, 32692, '2015-08-23 14:50:40', '2015-08-28 00:00:00', '129841111', 1, 0),
(82, 'Magic1', '', 30000, 2676, 26723, '2015-08-23 14:51:14', '2015-09-30 00:00:00', '789852123', 1, 0),
(83, 'Magic2', '', 0, 23462, 24734, '2015-08-23 14:51:14', '2015-10-21 00:00:00', '789852123', 1, 0),
(84, 'Auc3', 'Bla Bla', 20, 0, 15, '2015-08-26 16:57:21', '2015-10-29 00:00:00', '246237893', 1, 0),
(85, 'Auc5', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ex libero, maximus et odio nec, elementum volutpat mauris. Quisque consectetur enim nec orci commodo, sed dictum elit sollicitudin. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse nec condimentum lectus. Vestibulum id ligula vitae diam vulputate faucibus eu a leo. Duis eget convallis eros. Mauris sit amet ullamcorper velit, at rutrum nisi. Suspendisse vitae mollis dui. Nam varius erat ut erat condimentum ornare. Nunc porta efficitur arcu, ac suscipit mi hendrerit sit amet. ', 0, 169, 69, '2015-08-31 20:38:03', '2015-10-30 00:00:00', '26274', 1, 0),
(94, 'Auc9', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ex libero, maximus et odio nec, elementum volutpat mauris. Quisque consectetur enim nec orci commodo, sed dictum elit sollicitudin. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse nec condimentum lectus. Vestibulum id ligula vitae diam vulputate faucibus eu a leo. Duis eget convallis eros. Mauris sit amet ullamcorper velit, at rutrum nisi. Suspendisse vitae mollis dui. Nam varius erat ut erat condimentum ornare. Nunc porta efficitur arcu, ac suscipit mi hendrerit sit amet. ', 0, 156, 15, '2015-08-31 21:59:38', '2015-09-22 00:00:00', '123456789', 0, 0),
(95, 'Auc9', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ex libero, maximus et odio nec, elementum volutpat mauris. Quisque consectetur enim nec orci commodo, sed dictum elit sollicitudin. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse nec condimentum lectus. Vestibulum id ligula vitae diam vulputate faucibus eu a leo. Duis eget convallis eros. Mauris sit amet ullamcorper velit, at rutrum nisi. Suspendisse vitae mollis dui. Nam varius erat ut erat condimentum ornare. Nunc porta efficitur arcu, ac suscipit mi hendrerit sit amet. ', 0, 156, 15, '2015-08-31 21:59:38', '2015-09-22 00:00:00', '123456789', 0, 0),
(96, 'Auc6', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ex libero, maximus et odio nec, elementum volutpat mauris. Quisque consectetur enim nec orci commodo, sed dictum elit sollicitudin. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse nec condimentum lectus. Vestibulum id ligula vitae diam vulputate faucibus eu a leo. Duis eget convallis eros. Mauris sit amet ullamcorper velit, at rutrum nisi. Suspendisse vitae mollis dui. Nam varius erat ut erat condimentum ornare. Nunc porta efficitur arcu, ac suscipit mi hendrerit sit amet. ', 0, 156, 16, '2015-08-31 22:01:35', '2015-09-17 00:00:00', '246237893', 0, 0),
(97, 'Auc7', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ex libero, maximus et odio nec, elementum volutpat mauris. Quisque consectetur enim nec orci commodo, sed dictum elit sollicitudin. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse nec condimentum lectus. Vestibulum id ligula vitae diam vulputate faucibus eu a leo. Duis eget convallis eros. Mauris sit amet ullamcorper velit, at rutrum nisi. Suspendisse vitae mollis dui. Nam varius erat ut erat condimentum ornare. Nunc porta efficitur arcu, ac suscipit mi hendrerit sit amet. ', 0, 156, 16, '2015-08-31 22:02:17', '2015-09-11 00:00:00', '246237893', 0, 0),
(98, 'Auc5', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ex libero, maximus et odio nec, elementum volutpat mauris. Quisque consectetur enim nec orci commodo, sed dictum elit sollicitudin. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse nec condimentum lectus. Vestibulum id ligula vitae diam vulputate faucibus eu a leo. Duis eget convallis eros. Mauris sit amet ullamcorper velit, at rutrum nisi. Suspendisse vitae mollis dui. Nam varius erat ut erat condimentum ornare. Nunc porta efficitur arcu, ac suscipit mi hendrerit sit amet. ', 0, 169, 15, '2015-08-31 22:02:41', '2015-09-23 00:00:00', '26274', 1, 0),
(99, 'Auc5', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ex libero, maximus et odio nec, elementum volutpat mauris. Quisque consectetur enim nec orci commodo, sed dictum elit sollicitudin. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse nec condimentum lectus. Vestibulum id ligula vitae diam vulputate faucibus eu a leo. Duis eget convallis eros. Mauris sit amet ullamcorper velit, at rutrum nisi. Suspendisse vitae mollis dui. Nam varius erat ut erat condimentum ornare. Nunc porta efficitur arcu, ac suscipit mi hendrerit sit amet. ', 0, 156, 15, '2015-08-31 22:04:23', '2015-09-12 00:00:00', '26274', 1, 0),
(100, 'Auc5', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ex libero, maximus et odio nec, elementum volutpat mauris. Quisque consectetur enim nec orci commodo, sed dictum elit sollicitudin. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse nec condimentum lectus. Vestibulum id ligula vitae diam vulputate faucibus eu a leo. Duis eget convallis eros. Mauris sit amet ullamcorper velit, at rutrum nisi. Suspendisse vitae mollis dui. Nam varius erat ut erat condimentum ornare. Nunc porta efficitur arcu, ac suscipit mi hendrerit sit amet. ', 0, 169, 15, '2015-08-31 22:07:45', '2015-09-23 00:00:00', '123456789', 0, 0),
(101, 'Auc9', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ex libero, maximus et odio nec, elementum volutpat mauris. Quisque consectetur enim nec orci commodo, sed dictum elit sollicitudin. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse nec condimentum lectus. Vestibulum id ligula vitae diam vulputate faucibus eu a leo. Duis eget convallis eros. Mauris sit amet ullamcorper velit, at rutrum nisi. Suspendisse vitae mollis dui. Nam varius erat ut erat condimentum ornare. Nunc porta efficitur arcu, ac suscipit mi hendrerit sit amet. ', 45, 169, 15, '2015-08-31 22:23:48', '2015-09-25 00:00:00', '26274', 1, 0),
(102, 'Auc5', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vitae arcu vel justo cursus dictum. Proin rhoncus in quam et congue. Sed ultricies lobortis diam id scelerisque. Nulla facilisi. Etiam eu libero ut velit dapibus pharetra. Aliquam erat volutpat. Sed in turpis sem. Praesent facilisis at velit nec fringilla. Morbi condimentum et mi vitae auctor. Quisque nec varius diam. Vivamus commodo lobortis lorem, tempus sodales augue laoreet id. Nam vitae arcu tortor.', 0, 169, 15, '2015-09-01 16:43:51', '2015-09-18 00:00:00', '246237893', 0, 0),
(103, 'Marvel1', '\r\n\r\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Donec scelerisque dolor at nibh blandit, et fringilla leo finibus. Vivamus mollis diam eget libero vestibulum, eget sagittis nibh fringilla. Vestibulum tristique commodo sapien in aliquet. Aliquam luctus augue id leo hendrerit, at bibendum tortor rutrum. Nullam vulputate mauris at pellentesque tristique. Nunc sapien quam, vehicula ultricies consequat vel, interdum id lorem. Aliquam neque purus, tincidunt non laoreet at, faucibus a sem. Curabitur eget magna at lacus semper venenatis eget et nisl. Nam non imperdiet risus. Etiam eget pharetra odio. Nullam fermentum tortor scelerisque orci ultricies hendrerit.\r\n\r\nMaecenas id faucibus mauris, sit amet vestibulum sem. Fusce tincidunt commodo massa, semper suscipit mauris consectetur eget. Vestibulum a rhoncus augue, in blandit risus. Vestibulum augue felis, blandit at ex et, vulputate convallis metus. Praesent sapien ipsum, semper in enim sit amet, maximus cursus massa. Integer at ipsum blandit, pulvinar ipsum in, convallis nulla. Suspendisse a nisi convallis, pulvinar tellus a, pellentesque nisl. Quisque et pharetra dolor. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse at metus in ligula efficitur vulputate. Ut ac mollis orci. Sed in massa id nisi posuere aliquam. In hac habitasse platea dictumst. Mauris viverra nunc dapibus sapien fermentum, eget volutpat tellus commodo.\r\n\r\nMaecenas ornare ante a orci commodo, ac egestas risus vulputate. Aliquam erat volutpat. Quisque ultricies elit auctor, porttitor ipsum id, facilisis massa. Curabitur luctus vestibulum turpis, vestibulum fermentum odio cursus eget. Integer libero sapien, venenatis vel venenatis a, pretium at velit. In mollis laoreet metus vitae ornare. Etiam viverra vel ex ut sagittis. Pellentesque convallis, est id imperdiet porttitor, quam mi pharetra elit, nec elementum est mauris in arcu. Quisque in sem maximus, vulputate libero ut, semper odio. Integer sagittis risus ligula, eget hendrerit magna condimentum in. Cras consequat massa turpis, sed condimentum tortor dictum tincidunt. In a erat a lorem hendrerit viverra. Donec nisi ipsum, efficitur sed dolor eget, cursus ultrices mauris.\r\n\r\nNullam tempus eros eu dolor maximus, eget pharetra neque posuere. Aliquam quam eros, vestibulum non lacus eget, sodales cursus nisi. Fusce nec sapien mauris. Nunc vitae arcu eros. Etiam pulvinar libero eu mattis cursus. Morbi mattis maximus tortor non facilisis. Pellentesque pulvinar leo sed nunc aliquam, eu imperdiet quam viverra. Phasellus id pharetra arcu. Vivamus ac convallis quam. Donec ex libero, condimentum et nunc vel, eleifend scelerisque eros. Nunc condimentum tortor vitae est venenatis volutpat. Aliquam eget tempor purus. Ut aliquet orci mi, nec varius augue feugiat vel.\r\n\r\nUt mollis vel augue blandit tristique. Proin dignissim tellus eget varius sodales. Vestibulum imperdiet fringilla accumsan. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam condimentum nibh non mi suscipit pretium a quis odio. In neque justo, scelerisque in luctus sit amet, porttitor sit amet tellus. Pellentesque ac tincidunt odio. Proin dolor est, accumsan eget ornare vitae, vulputate vel erat. Donec dignissim leo nibh, eget mattis risus viverra sit amet. Curabitur sed porta leo. ', 50, 150, 15, '2015-09-06 14:31:32', '2015-12-31 00:00:00', '123456711', 1, 0),
(104, 'Marvel2', '\r\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Donec scelerisque dolor at nibh blandit, et fringilla leo finibus. Vivamus mollis diam eget libero vestibulum, eget sagittis nibh fringilla. Vestibulum tristique commodo sapien in aliquet. Aliquam luctus augue id leo hendrerit, at bibendum tortor rutrum. Nullam vulputate mauris at pellentesque tristique. Nunc sapien quam, vehicula ultricies consequat vel, interdum id lorem. Aliquam neque purus, tincidunt non laoreet at, faucibus a sem. Curabitur eget magna at lacus semper venenatis eget et nisl. Nam non imperdiet risus. Etiam eget pharetra odio. Nullam fermentum tortor scelerisque orci ultricies hendrerit.\r\n\r\nMaecenas id faucibus mauris, sit amet vestibulum sem. Fusce tincidunt commodo massa, semper suscipit mauris consectetur eget. Vestibulum a rhoncus augue, in blandit risus. Vestibulum augue felis, blandit at ex et, vulputate convallis metus. Praesent sapien ipsum, semper in enim sit amet, maximus cursus massa. Integer at ipsum blandit, pulvinar ipsum in, convallis nulla. Suspendisse a nisi convallis, pulvinar tellus a, pellentesque nisl. Quisque et pharetra dolor. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse at metus in ligula efficitur vulputate. Ut ac mollis orci. Sed in massa id nisi posuere aliquam. In hac habitasse platea dictumst. Mauris viverra nunc dapibus sapien fermentum, eget volutpat tellus commodo.\r\n\r\nMaecenas ornare ante a orci commodo, ac egestas risus vulputate. Aliquam erat volutpat. Quisque ultricies elit auctor, porttitor ipsum id, facilisis massa. Curabitur luctus vestibulum turpis, vestibulum fermentum odio cursus eget. Integer libero sapien, venenatis vel venenatis a, pretium at velit. In mollis laoreet metus vitae ornare. Etiam viverra vel ex ut sagittis. Pellentesque convallis, est id imperdiet porttitor, quam mi pharetra elit, nec elementum est mauris in arcu. Quisque in sem maximus, vulputate libero ut, semper odio. Integer sagittis risus ligula, eget hendrerit magna condimentum in. Cras consequat massa turpis, sed condimentum tortor dictum tincidunt. In a erat a lorem hendrerit viverra. Donec nisi ipsum, efficitur sed dolor eget, cursus ultrices mauris.\r\n\r\nNullam tempus eros eu dolor maximus, eget pharetra neque posuere. Aliquam quam eros, vestibulum non lacus eget, sodales cursus nisi. Fusce nec sapien mauris. Nunc vitae arcu eros. Etiam pulvinar libero eu mattis cursus. Morbi mattis maximus tortor non facilisis. Pellentesque pulvinar leo sed nunc aliquam, eu imperdiet quam viverra. Phasellus id pharetra arcu. Vivamus ac convallis quam. Donec ex libero, condimentum et nunc vel, eleifend scelerisque eros. Nunc condimentum tortor vitae est venenatis volutpat. Aliquam eget tempor purus. Ut aliquet orci mi, nec varius augue feugiat vel.\r\n\r\nUt mollis vel augue blandit tristique. Proin dignissim tellus eget varius sodales. Vestibulum imperdiet fringilla accumsan. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam condimentum nibh non mi suscipit pretium a quis odio. In neque justo, scelerisque in luctus sit amet, porttitor sit amet tellus. Pellentesque ac tincidunt odio. Proin dolor est, accumsan eget ornare vitae, vulputate vel erat. Donec dignissim leo nibh, eget mattis risus viverra sit amet. Curabitur sed porta leo. ', 50, 150, 15, '2015-09-06 14:31:32', '2015-12-31 00:00:00', '123456711', 1, 0),
(105, 'Marvel3', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec scelerisque dolor at nibh blandit, et fringilla leo finibus. Vivamus mollis diam eget libero vestibulum, eget sagittis nibh fringilla. Vestibulum tristique commodo sapien in aliquet. Aliquam luctus augue id leo hendrerit, at bibendum tortor rutrum. Nullam vulputate mauris at pellentesque tristique. Nunc sapien quam, vehicula ultricies consequat vel, interdum id lorem. Aliquam neque purus, tincidunt non laoreet at, faucibus a sem. Curabitur eget magna at lacus semper venenatis eget et nisl. Nam non imperdiet risus. Etiam eget pharetra odio. Nullam fermentum tortor scelerisque orci ultricies hendrerit.\r\n\r\nMaecenas id faucibus mauris, sit amet vestibulum sem. Fusce tincidunt commodo massa, semper suscipit mauris consectetur eget. Vestibulum a rhoncus augue, in blandit risus. Vestibulum augue felis, blandit at ex et, vulputate convallis metus. Praesent sapien ipsum, semper in enim sit amet, maximus cursus massa. Integer at ipsum blandit, pulvinar ipsum in, convallis nulla. Suspendisse a nisi convallis, pulvinar tellus a, pellentesque nisl. Quisque et pharetra dolor. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse at metus in ligula efficitur vulputate. Ut ac mollis orci. Sed in massa id nisi posuere aliquam. In hac habitasse platea dictumst. Mauris viverra nunc dapibus sapien fermentum, eget volutpat tellus commodo.\r\n\r\nMaecenas ornare ante a orci commodo, ac egestas risus vulputate. Aliquam erat volutpat. Quisque ultricies elit auctor, porttitor ipsum id, facilisis massa. Curabitur luctus vestibulum turpis, vestibulum fermentum odio cursus eget. Integer libero sapien, venenatis vel venenatis a, pretium at velit. In mollis laoreet metus vitae ornare. Etiam viverra vel ex ut sagittis. Pellentesque convallis, est id imperdiet porttitor, quam mi pharetra elit, nec elementum est mauris in arcu. Quisque in sem maximus, vulputate libero ut, semper odio. Integer sagittis risus ligula, eget hendrerit magna condimentum in. Cras consequat massa turpis, sed condimentum tortor dictum tincidunt. In a erat a lorem hendrerit viverra. Donec nisi ipsum, efficitur sed dolor eget, cursus ultrices mauris.\r\n\r\nNullam tempus eros eu dolor maximus, eget pharetra neque posuere. Aliquam quam eros, vestibulum non lacus eget, sodales cursus nisi. Fusce nec sapien mauris. Nunc vitae arcu eros. Etiam pulvinar libero eu mattis cursus. Morbi mattis maximus tortor non facilisis. Pellentesque pulvinar leo sed nunc aliquam, eu imperdiet quam viverra. Phasellus id pharetra arcu. Vivamus ac convallis quam. Donec ex libero, condimentum et nunc vel, eleifend scelerisque eros. Nunc condimentum tortor vitae est venenatis volutpat. Aliquam eget tempor purus. Ut aliquet orci mi, nec varius augue feugiat vel.\r\n\r\nUt mollis vel augue blandit tristique. Proin dignissim tellus eget varius sodales. Vestibulum imperdiet fringilla accumsan. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam condimentum nibh non mi suscipit pretium a quis odio. In neque justo, scelerisque in luctus sit amet, porttitor sit amet tellus. Pellentesque ac tincidunt odio. Proin dolor est, accumsan eget ornare vitae, vulputate vel erat. Donec dignissim leo nibh, eget mattis risus viverra sit amet. Curabitur sed porta leo. ', 50, 150, 15, '2015-09-06 14:31:32', '2015-12-31 00:00:00', '123456711', 1, 0),
(106, 'Marvel4', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec scelerisque dolor at nibh blandit, et fringilla leo finibus. Vivamus mollis diam eget libero vestibulum, eget sagittis nibh fringilla. Vestibulum tristique commodo sapien in aliquet. Aliquam luctus augue id leo hendrerit, at bibendum tortor rutrum. Nullam vulputate mauris at pellentesque tristique. Nunc sapien quam, vehicula ultricies consequat vel, interdum id lorem. Aliquam neque purus, tincidunt non laoreet at, faucibus a sem. Curabitur eget magna at lacus semper venenatis eget et nisl. Nam non imperdiet risus. Etiam eget pharetra odio. Nullam fermentum tortor scelerisque orci ultricies hendrerit.\r\n\r\nMaecenas id faucibus mauris, sit amet vestibulum sem. Fusce tincidunt commodo massa, semper suscipit mauris consectetur eget. Vestibulum a rhoncus augue, in blandit risus. Vestibulum augue felis, blandit at ex et, vulputate convallis metus. Praesent sapien ipsum, semper in enim sit amet, maximus cursus massa. Integer at ipsum blandit, pulvinar ipsum in, convallis nulla. Suspendisse a nisi convallis, pulvinar tellus a, pellentesque nisl. Quisque et pharetra dolor. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse at metus in ligula efficitur vulputate. Ut ac mollis orci. Sed in massa id nisi posuere aliquam. In hac habitasse platea dictumst. Mauris viverra nunc dapibus sapien fermentum, eget volutpat tellus commodo.\r\n\r\nMaecenas ornare ante a orci commodo, ac egestas risus vulputate. Aliquam erat volutpat. Quisque ultricies elit auctor, porttitor ipsum id, facilisis massa. Curabitur luctus vestibulum turpis, vestibulum fermentum odio cursus eget. Integer libero sapien, venenatis vel venenatis a, pretium at velit. In mollis laoreet metus vitae ornare. Etiam viverra vel ex ut sagittis. Pellentesque convallis, est id imperdiet porttitor, quam mi pharetra elit, nec elementum est mauris in arcu. Quisque in sem maximus, vulputate libero ut, semper odio. Integer sagittis risus ligula, eget hendrerit magna condimentum in. Cras consequat massa turpis, sed condimentum tortor dictum tincidunt. In a erat a lorem hendrerit viverra. Donec nisi ipsum, efficitur sed dolor eget, cursus ultrices mauris.\r\n\r\nNullam tempus eros eu dolor maximus, eget pharetra neque posuere. Aliquam quam eros, vestibulum non lacus eget, sodales cursus nisi. Fusce nec sapien mauris. Nunc vitae arcu eros. Etiam pulvinar libero eu mattis cursus. Morbi mattis maximus tortor non facilisis. Pellentesque pulvinar leo sed nunc aliquam, eu imperdiet quam viverra. Phasellus id pharetra arcu. Vivamus ac convallis quam. Donec ex libero, condimentum et nunc vel, eleifend scelerisque eros. Nunc condimentum tortor vitae est venenatis volutpat. Aliquam eget tempor purus. Ut aliquet orci mi, nec varius augue feugiat vel.\r\n\r\nUt mollis vel augue blandit tristique. Proin dignissim tellus eget varius sodales. Vestibulum imperdiet fringilla accumsan. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam condimentum nibh non mi suscipit pretium a quis odio. In neque justo, scelerisque in luctus sit amet, porttitor sit amet tellus. Pellentesque ac tincidunt odio. Proin dolor est, accumsan eget ornare vitae, vulputate vel erat. Donec dignissim leo nibh, eget mattis risus viverra sit amet. Curabitur sed porta leo. ', 50, 150, 15, '2015-09-06 14:31:32', '2015-12-31 00:00:00', '123456711', 1, 0),
(107, 'Marvel5', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec scelerisque dolor at nibh blandit, et fringilla leo finibus. Vivamus mollis diam eget libero vestibulum, eget sagittis nibh fringilla. Vestibulum tristique commodo sapien in aliquet. Aliquam luctus augue id leo hendrerit, at bibendum tortor rutrum. Nullam vulputate mauris at pellentesque tristique. Nunc sapien quam, vehicula ultricies consequat vel, interdum id lorem. Aliquam neque purus, tincidunt non laoreet at, faucibus a sem. Curabitur eget magna at lacus semper venenatis eget et nisl. Nam non imperdiet risus. Etiam eget pharetra odio. Nullam fermentum tortor scelerisque orci ultricies hendrerit.\r\n\r\nMaecenas id faucibus mauris, sit amet vestibulum sem. Fusce tincidunt commodo massa, semper suscipit mauris consectetur eget. Vestibulum a rhoncus augue, in blandit risus. Vestibulum augue felis, blandit at ex et, vulputate convallis metus. Praesent sapien ipsum, semper in enim sit amet, maximus cursus massa. Integer at ipsum blandit, pulvinar ipsum in, convallis nulla. Suspendisse a nisi convallis, pulvinar tellus a, pellentesque nisl. Quisque et pharetra dolor. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse at metus in ligula efficitur vulputate. Ut ac mollis orci. Sed in massa id nisi posuere aliquam. In hac habitasse platea dictumst. Mauris viverra nunc dapibus sapien fermentum, eget volutpat tellus commodo.\r\n\r\nMaecenas ornare ante a orci commodo, ac egestas risus vulputate. Aliquam erat volutpat. Quisque ultricies elit auctor, porttitor ipsum id, facilisis massa. Curabitur luctus vestibulum turpis, vestibulum fermentum odio cursus eget. Integer libero sapien, venenatis vel venenatis a, pretium at velit. In mollis laoreet metus vitae ornare. Etiam viverra vel ex ut sagittis. Pellentesque convallis, est id imperdiet porttitor, quam mi pharetra elit, nec elementum est mauris in arcu. Quisque in sem maximus, vulputate libero ut, semper odio. Integer sagittis risus ligula, eget hendrerit magna condimentum in. Cras consequat massa turpis, sed condimentum tortor dictum tincidunt. In a erat a lorem hendrerit viverra. Donec nisi ipsum, efficitur sed dolor eget, cursus ultrices mauris.\r\n\r\nNullam tempus eros eu dolor maximus, eget pharetra neque posuere. Aliquam quam eros, vestibulum non lacus eget, sodales cursus nisi. Fusce nec sapien mauris. Nunc vitae arcu eros. Etiam pulvinar libero eu mattis cursus. Morbi mattis maximus tortor non facilisis. Pellentesque pulvinar leo sed nunc aliquam, eu imperdiet quam viverra. Phasellus id pharetra arcu. Vivamus ac convallis quam. Donec ex libero, condimentum et nunc vel, eleifend scelerisque eros. Nunc condimentum tortor vitae est venenatis volutpat. Aliquam eget tempor purus. Ut aliquet orci mi, nec varius augue feugiat vel.\r\n\r\nUt mollis vel augue blandit tristique. Proin dignissim tellus eget varius sodales. Vestibulum imperdiet fringilla accumsan. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam condimentum nibh non mi suscipit pretium a quis odio. In neque justo, scelerisque in luctus sit amet, porttitor sit amet tellus. Pellentesque ac tincidunt odio. Proin dolor est, accumsan eget ornare vitae, vulputate vel erat. Donec dignissim leo nibh, eget mattis risus viverra sit amet. Curabitur sed porta leo. ', 30, 150, 15, '2015-09-06 14:31:32', '2015-12-31 00:00:00', '123456711', 1, 0),
(108, 'Potter1', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec scelerisque dolor at nibh blandit, et fringilla leo finibus. Vivamus mollis diam eget libero vestibulum, eget sagittis nibh fringilla. Vestibulum tristique commodo sapien in aliquet. Aliquam luctus augue id leo hendrerit, at bibendum tortor rutrum. Nullam vulputate mauris at pellentesque tristique. Nunc sapien quam, vehicula ultricies consequat vel, interdum id lorem. Aliquam neque purus, tincidunt non laoreet at, faucibus a sem. Curabitur eget magna at lacus semper venenatis eget et nisl. Nam non imperdiet risus. Etiam eget pharetra odio. Nullam fermentum tortor scelerisque orci ultricies hendrerit.', 30, 200, 20, '2015-09-06 14:41:43', '2015-12-31 00:00:00', '789852123', 1, 0),
(109, 'Potter2', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec scelerisque dolor at nibh blandit, et fringilla leo finibus. Vivamus mollis diam eget libero vestibulum, eget sagittis nibh fringilla. Vestibulum tristique commodo sapien in aliquet. Aliquam luctus augue id leo hendrerit, at bibendum tortor rutrum. Nullam vulputate mauris at pellentesque tristique. Nunc sapien quam, vehicula ultricies consequat vel, interdum id lorem. Aliquam neque purus, tincidunt non laoreet at, faucibus a sem. Curabitur eget magna at lacus semper venenatis eget et nisl. Nam non imperdiet risus. Etiam eget pharetra odio. Nullam fermentum tortor scelerisque orci ultricies hendrerit.', 30, 200, 20, '2015-09-06 14:41:43', '2015-12-31 00:00:00', '789852123', 1, 0),
(110, 'Potter3', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec scelerisque dolor at nibh blandit, et fringilla leo finibus. Vivamus mollis diam eget libero vestibulum, eget sagittis nibh fringilla. Vestibulum tristique commodo sapien in aliquet. Aliquam luctus augue id leo hendrerit, at bibendum tortor rutrum. Nullam vulputate mauris at pellentesque tristique. Nunc sapien quam, vehicula ultricies consequat vel, interdum id lorem. Aliquam neque purus, tincidunt non laoreet at, faucibus a sem. Curabitur eget magna at lacus semper venenatis eget et nisl. Nam non imperdiet risus. Etiam eget pharetra odio. Nullam fermentum tortor scelerisque orci ultricies hendrerit.', 40, 200, 20, '2015-09-06 14:41:43', '2015-12-31 00:00:00', '789852123', 1, 0),
(111, 'Potter4', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec scelerisque dolor at nibh blandit, et fringilla leo finibus. Vivamus mollis diam eget libero vestibulum, eget sagittis nibh fringilla. Vestibulum tristique commodo sapien in aliquet. Aliquam luctus augue id leo hendrerit, at bibendum tortor rutrum. Nullam vulputate mauris at pellentesque tristique. Nunc sapien quam, vehicula ultricies consequat vel, interdum id lorem. Aliquam neque purus, tincidunt non laoreet at, faucibus a sem. Curabitur eget magna at lacus semper venenatis eget et nisl. Nam non imperdiet risus. Etiam eget pharetra odio. Nullam fermentum tortor scelerisque orci ultricies hendrerit.', 0, 200, 20, '2015-09-06 14:41:43', '2015-12-31 00:00:00', '789852123', 1, 0),
(112, 'Potter5', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec scelerisque dolor at nibh blandit, et fringilla leo finibus. Vivamus mollis diam eget libero vestibulum, eget sagittis nibh fringilla. Vestibulum tristique commodo sapien in aliquet. Aliquam luctus augue id leo hendrerit, at bibendum tortor rutrum. Nullam vulputate mauris at pellentesque tristique. Nunc sapien quam, vehicula ultricies consequat vel, interdum id lorem. Aliquam neque purus, tincidunt non laoreet at, faucibus a sem. Curabitur eget magna at lacus semper venenatis eget et nisl. Nam non imperdiet risus. Etiam eget pharetra odio. Nullam fermentum tortor scelerisque orci ultricies hendrerit.', 50, 200, 20, '2015-09-06 14:41:43', '2015-12-31 00:00:00', '789852123', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `item_photo`
--

CREATE TABLE IF NOT EXISTS `item_photo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ItemId` int(11) NOT NULL,
  `Path` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ItemId` (`ItemId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=45 ;

--
-- Dumping data for table `item_photo`
--

INSERT INTO `item_photo` (`ID`, `ItemId`, `Path`) VALUES
(1, 101, '/uploads/photos/photo5788213841387124648.jpg'),
(2, 101, '/uploads/photos/xbox8316078409962964529.jpg'),
(3, 102, '/uploads/photos/xbox3388748176912074561.jpg'),
(4, 102, '/uploads/photos/photo339567480957348480.jpg'),
(5, 103, '/uploads/photos/xbox2133118735133533203.jpg'),
(6, 103, '/uploads/photos/photo1039043068138132981.jpg'),
(7, 104, '/uploads/photos/xbox2133118735133533203.jpg'),
(8, 104, '/uploads/photos/photo1039043068138132981.jpg'),
(9, 104, '/uploads/photos/xbox2549026428937519659.jpg'),
(10, 104, '/uploads/photos/photo8592886777224907615.jpg'),
(11, 105, '/uploads/photos/xbox2133118735133533203.jpg'),
(12, 105, '/uploads/photos/photo1039043068138132981.jpg'),
(13, 105, '/uploads/photos/xbox2549026428937519659.jpg'),
(14, 105, '/uploads/photos/photo8592886777224907615.jpg'),
(15, 105, '/uploads/photos/xbox1600427820891291365.jpg'),
(16, 105, '/uploads/photos/photo857319227543294871.jpg'),
(17, 106, '/uploads/photos/xbox2133118735133533203.jpg'),
(18, 106, '/uploads/photos/photo1039043068138132981.jpg'),
(19, 106, '/uploads/photos/xbox2549026428937519659.jpg'),
(20, 106, '/uploads/photos/photo8592886777224907615.jpg'),
(21, 106, '/uploads/photos/xbox1600427820891291365.jpg'),
(22, 106, '/uploads/photos/photo857319227543294871.jpg'),
(23, 106, '/uploads/photos/xbox3482763203561166959.jpg'),
(24, 106, '/uploads/photos/photo63993068052652015.jpg'),
(25, 107, '/uploads/photos/xbox2133118735133533203.jpg'),
(26, 107, '/uploads/photos/photo1039043068138132981.jpg'),
(27, 107, '/uploads/photos/xbox2549026428937519659.jpg'),
(28, 107, '/uploads/photos/photo8592886777224907615.jpg'),
(29, 107, '/uploads/photos/xbox1600427820891291365.jpg'),
(30, 107, '/uploads/photos/photo857319227543294871.jpg'),
(31, 107, '/uploads/photos/xbox3482763203561166959.jpg'),
(32, 107, '/uploads/photos/photo63993068052652015.jpg'),
(33, 107, '/uploads/photos/photo5708315162361826294.jpg'),
(34, 107, '/uploads/photos/xbox7264738456328320630.jpg'),
(35, 108, '/uploads/photos/photo8690443656658627100.jpg'),
(36, 108, '/uploads/photos/xbox4059685232550007680.jpg'),
(37, 109, '/uploads/photos/photo8690443656658627100.jpg'),
(38, 109, '/uploads/photos/xbox4059685232550007680.jpg'),
(39, 110, '/uploads/photos/photo8690443656658627100.jpg'),
(40, 110, '/uploads/photos/xbox4059685232550007680.jpg'),
(41, 111, '/uploads/photos/photo8690443656658627100.jpg'),
(42, 111, '/uploads/photos/xbox4059685232550007680.jpg'),
(43, 112, '/uploads/photos/photo8690443656658627100.jpg'),
(44, 112, '/uploads/photos/xbox4059685232550007680.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE IF NOT EXISTS `message` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Text` mediumtext NOT NULL,
  `TimeSent` datetime NOT NULL,
  `isRead` tinyint(1) NOT NULL,
  `Sender` varchar(9) NOT NULL,
  `Recipient` varchar(9) NOT NULL,
  `hiddenFromInbox` tinyint(1) NOT NULL DEFAULT '0',
  `hiddenFromOutbox` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `Sender` (`Sender`),
  KEY `Recipient` (`Recipient`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`ID`, `Text`, `TimeSent`, `isRead`, `Sender`, `Recipient`, `hiddenFromInbox`, `hiddenFromOutbox`) VALUES
(1, 'Mpla Mpla<br>', '2015-08-29 01:15:41', 1, '26274', '123456789', 0, 0),
(2, 'Test message, to check if all is right<br>', '2015-09-05 21:17:11', 1, '123456789', '26274', 0, 0),
(3, '<div id="lipsum">\r\n<p>\r\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse \r\ntristique, purus in laoreet consectetur, risus risus ultrices nunc, a \r\negestas elit nunc id ligula. In ut lectus in sapien pellentesque \r\negestas. Etiam sapien turpis, condimentum id malesuada a, imperdiet eget\r\n sapien. Suspendisse arcu risus, facilisis in vehicula semper, sagittis \r\nut dolor. Nulla faucibus mauris sed euismod consectetur. Nam vulputate, \r\ntellus in sollicitudin tempor, diam sapien viverra est, sed dapibus \r\nnulla velit vel dui. Mauris elementum, massa eget eleifend consequat, \r\nrisus orci tristique erat, in consectetur sem nisi ac diam. Nunc et \r\nmauris at tortor eleifend facilisis. Praesent venenatis felis maximus \r\nest gravida mollis. Sed in metus nulla. Suspendisse et molestie neque.\r\n</p>\r\n<p>\r\nCras in suscipit eros. Etiam facilisis sapien id urna finibus volutpat. \r\nSed et massa ut lorem vehicula vulputate eu dignissim dolor. Cras \r\neleifend nec ex ac rhoncus. Nulla non metus leo. Quisque placerat \r\nsagittis nulla. Nunc gravida quam gravida sodales consequat. Aenean eget\r\n ultricies leo, sed iaculis diam. Maecenas sed quam at nisi pulvinar \r\nmaximus.\r\n</p>\r\n<p>\r\nNunc sagittis ultrices vestibulum. Nam et pretium eros. Donec id varius \r\ndui. Nullam tempor elementum viverra. Suspendisse in ante faucibus, \r\nblandit odio ut, rutrum sem. Duis scelerisque auctor justo nec \r\nconvallis. Suspendisse mollis risus eget lacus volutpat interdum. Sed \r\nposuere consectetur ligula nec bibendum. Nunc mi arcu, tempor id dolor \r\nsed, tincidunt porta enim. Cras finibus velit a odio vehicula, sit amet \r\ngravida ipsum euismod. Sed eget risus ullamcorper, consequat ante eu, \r\ntristique ligula. Vestibulum maximus rutrum orci, ut tempus odio cursus \r\nid. Mauris sodales vulputate nisi nec accumsan. In hac habitasse platea \r\ndictumst. Praesent magna dolor, molestie in tellus a, euismod \r\nscelerisque quam.\r\n</p>\r\n<p>\r\nInteger dictum mauris non erat finibus luctus. Nullam blandit quam ex, \r\neu pulvinar turpis aliquet at. Etiam ante ligula, sodales nec \r\nullamcorper sit amet, tincidunt et libero. Sed lacinia congue efficitur.\r\n Donec nec accumsan quam, quis posuere urna. Sed euismod viverra enim \r\nnec congue. Class aptent taciti sociosqu ad litora torquent per conubia \r\nnostra, per inceptos himenaeos. Proin pellentesque odio tortor, sed \r\nlacinia massa ultrices finibus. Quisque luctus eu odio sed efficitur.\r\n</p>\r\n<p>\r\nPraesent vestibulum felis eu ante sollicitudin volutpat. Curabitur ac \r\ndui ac felis ultricies feugiat. Sed venenatis nisi at metus tincidunt, \r\nnec finibus orci eleifend. Maecenas ultricies ex quis turpis eleifend \r\nmalesuada. Proin tempor condimentum ipsum, sit amet eleifend nisl \r\naccumsan eget. Maecenas tristique luctus vehicula. Pellentesque a risus \r\nvel mi vulputate auctor.\r\n</p></div>', '2015-09-05 21:20:01', 1, '26274', '123456789', 0, 0),
(4, '<div id="lipsum">\r\n<p>\r\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse \r\ntristique, purus in laoreet consectetur, risus risus ultrices nunc, a \r\negestas elit nunc id ligula. In ut lectus in sapien pellentesque \r\negestas. Etiam sapien turpis, condimentum id malesuada a, imperdiet eget\r\n sapien. Suspendisse arcu risus, facilisis in vehicula semper, sagittis \r\nut dolor. Nulla faucibus mauris sed euismod consectetur. Nam vulputate, \r\ntellus in sollicitudin tempor, diam sapien viverra est, sed dapibus \r\nnulla velit vel dui. Mauris elementum, massa eget eleifend consequat, \r\nrisus orci tristique erat, in consectetur sem nisi ac diam. Nunc et \r\nmauris at tortor eleifend facilisis. Praesent venenatis felis maximus \r\nest gravida mollis. Sed in metus nulla. Suspendisse et molestie neque.\r\n</p>\r\n<p>\r\nCras in suscipit eros. Etiam facilisis sapien id urna finibus volutpat. \r\nSed et massa ut lorem vehicula vulputate eu dignissim dolor. Cras \r\neleifend nec ex ac rhoncus. Nulla non metus leo. Quisque placerat \r\nsagittis nulla. Nunc gravida quam gravida sodales consequat. Aenean eget\r\n ultricies leo, sed iaculis diam. Maecenas sed quam at nisi pulvinar \r\nmaximus.\r\n</p>\r\n<p>\r\nNunc sagittis ultrices vestibulum. Nam et pretium eros. Donec id varius \r\ndui. Nullam tempor elementum viverra. Suspendisse in ante faucibus, \r\nblandit odio ut, rutrum sem. Duis scelerisque auctor justo nec \r\nconvallis. Suspendisse mollis risus eget lacus volutpat interdum. Sed \r\nposuere consectetur ligula nec bibendum. Nunc mi arcu, tempor id dolor \r\nsed, tincidunt porta enim. Cras finibus velit a odio vehicula, sit amet \r\ngravida ipsum euismod. Sed eget risus ullamcorper, consequat ante eu, \r\ntristique ligula. Vestibulum maximus rutrum orci, ut tempus odio cursus \r\nid. Mauris sodales vulputate nisi nec accumsan. In hac habitasse platea \r\ndictumst. Praesent magna dolor, molestie in tellus a, euismod \r\nscelerisque quam.\r\n</p>\r\n<p>\r\nInteger dictum mauris non erat finibus luctus. Nullam blandit quam ex, \r\neu pulvinar turpis aliquet at. Etiam ante ligula, sodales nec \r\nullamcorper sit amet, tincidunt et libero. Sed lacinia congue efficitur.\r\n Donec nec accumsan quam, quis posuere urna. Sed euismod viverra enim \r\nnec congue. Class aptent taciti sociosqu ad litora torquent per conubia \r\nnostra, per inceptos himenaeos. Proin pellentesque odio tortor, sed \r\nlacinia massa ultrices finibus. Quisque luctus eu odio sed efficitur.\r\n</p>\r\n<p>\r\nPraesent vestibulum felis eu ante sollicitudin volutpat. Curabitur ac \r\ndui ac felis ultricies feugiat. Sed venenatis nisi at metus tincidunt, \r\nnec finibus orci eleifend. Maecenas ultricies ex quis turpis eleifend \r\nmalesuada. Proin tempor condimentum ipsum, sit amet eleifend nisl \r\naccumsan eget. Maecenas tristique luctus vehicula. Pellentesque a risus \r\nvel mi vulputate auctor.\r\n</p></div>', '2015-09-05 21:20:01', 1, '26274', '123456789', 0, 0);


--
-- Constraints for dumped tables
--

--
-- Constraints for table `bids`
--
ALTER TABLE `bids`
  ADD CONSTRAINT `bids_ibfk_1` FOREIGN KEY (`ItemId`) REFERENCES `item` (`ItemId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bids_ibfk_2` FOREIGN KEY (`Bidder`) REFERENCES `user` (`SocialSecurityNumber`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `category_ibfk_1` FOREIGN KEY (`ItemId`) REFERENCES `item` (`ItemId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `fk_Item_User2` FOREIGN KEY (`Seller`) REFERENCES `user` (`SocialSecurityNumber`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `item_photo`
--
ALTER TABLE `item_photo`
  ADD CONSTRAINT `item_photo_ibfk_1` FOREIGN KEY (`ItemId`) REFERENCES `item` (`ItemId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `message_ibfk_1` FOREIGN KEY (`Sender`) REFERENCES `user` (`SocialSecurityNumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `message_ibfk_2` FOREIGN KEY (`Recipient`) REFERENCES `user` (`SocialSecurityNumber`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
