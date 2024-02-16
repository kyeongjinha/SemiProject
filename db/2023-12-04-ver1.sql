-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: mysql.coffit.today    Database: notfound
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account_type`
--

DROP TABLE IF EXISTS `account_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_type` (
  `type_code` int NOT NULL,
  `type_name` varchar(20) NOT NULL,
  PRIMARY KEY (`type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_type`
--

LOCK TABLES `account_type` WRITE;
/*!40000 ALTER TABLE `account_type` DISABLE KEYS */;
INSERT INTO `account_type` VALUES (0,'user'),(10,'admin');
/*!40000 ALTER TABLE `account_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `id` varchar(30) NOT NULL,
  `account_type_code` int NOT NULL DEFAULT '0',
  `password` varchar(30) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `regist_date` date DEFAULT NULL,
  `pay_amount` int NOT NULL DEFAULT '0',
  `mileage` int DEFAULT '0',
  `last_login` date DEFAULT NULL,
  `half_year_accumulated_amount` int DEFAULT '0',
  `account_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `account_type_code_idx` (`account_type_code`),
  CONSTRAINT `account_type_code` FOREIGN KEY (`account_type_code`) REFERENCES `account_type` (`type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES ('asshole',0,'user7','작지만큰손','asshole55@gmail.com','010-1013-1013','2023-12-04',2000000,20000,'2023-12-04',120000,NULL),('drg',0,'user2','드라군','drg55@gmail.com','010-6666-6666','2023-12-04',0,0,'2023-12-04',0,'black'),('hk',10,'root','이현강','test2@gmail.com','010-2222-2222','2023-12-04',100000000,10000000,'2023-12-04',5000000,NULL),('jc',10,'root','윤종철','test3@gmail.com','010-3333-3333','2023-12-04',100000000,10000000,'2023-12-04',5000000,NULL),('kj',10,'root','하경진','test4@gmail.com','010-4444-4444','2023-12-04',100000000,10000000,'2023-12-04',5000000,NULL),('marthin',0,'user3','마틴루터킹','marthin55@gmail.com','010-7777-7777','2023-12-04',0,0,'2023-12-04',0,NULL),('mok',0,'user3','목화','mok55@gmail.com','010-8888-8888','2023-12-04',0,0,'2023-12-04',0,NULL),('root',10,'root','정지섭','wjdwltjq7289@gmail.com','010-5790-8482','2023-12-04',100000000,10000000,'2023-12-04',5000000,NULL),('stv',0,'user1','스티브','stv55@gmail.com','010-5555-5555','2023-12-04',0,0,'2023-12-04',0,'dormant'),('sy',10,'root','김수연','test@gmail.com','010-1111-1111','2023-12-04',100000000,10000000,'2023-12-04',5000000,NULL),('tobighand',0,'user5','큰손','tobighand55@gmail.com','010-1010-1010','2023-12-04',4500000,45000,'2023-12-04',2100000,NULL),('tooobighand',0,'user6','더욱더큰손','tooobighand55@gmail.com','010-1012-1012','2023-12-04',14500000,145000,'2023-12-04',12100000,NULL),('who',0,'user4','누굴까','who55@gmail.com','010-9999-9999','2023-12-04',0,0,'2023-12-04',0,NULL);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `delivery_address` int NOT NULL AUTO_INCREMENT,
  `id` varchar(30) NOT NULL,
  `zipcode` varchar(5) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `address_detail` varchar(255) DEFAULT NULL,
  `address_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`delivery_address`,`id`),
  KEY `id` (`id`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`id`) REFERENCES `accounts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'root','10111','사우로51','105동 402호','집'),(2,'root','03163','인사동43','7층 하이미디어 아카데미','학원'),(3,'root','1','서울 영등포구 여의도동 의사당대로1','국회의사당 앞','직장'),(4,'marthin','07265','서울 영등포구 당산로98','세진빌딩 2층 203호','직장'),(5,'mok','07265','경기 양평군 양평읍 시민로78번길 9-8','예청그린팰리스아파트 402호','집'),(6,'mok','12561','경기 양평군 양평읍 남북로61','쌍용자동차 양평영업소','직장');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attachment` (
  `AttachmentCode` int NOT NULL,
  `PostCode` int NOT NULL,
  `CategoryCode` int NOT NULL,
  `Id` varchar(30) NOT NULL,
  `OriginalAttachmentName` varchar(255) DEFAULT NULL,
  `AttachmentName` varchar(255) DEFAULT NULL,
  `IsDelete` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`AttachmentCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment`
--

LOCK TABLES `attachment` WRITE;
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blacklist`
--

DROP TABLE IF EXISTS `blacklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blacklist` (
  `id` varchar(30) NOT NULL,
  `blacked_date` date DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `id` FOREIGN KEY (`id`) REFERENCES `accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blacklist`
--

LOCK TABLES `blacklist` WRITE;
/*!40000 ALTER TABLE `blacklist` DISABLE KEYS */;
INSERT INTO `blacklist` VALUES ('drg','2023-12-04','또 버그 걸려서 공격을 못함','drg55@gmail.com');
/*!40000 ALTER TABLE `blacklist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `postCode` int NOT NULL AUTO_INCREMENT,
  `categoryCode_board` int NOT NULL,
  `id` varchar(30) NOT NULL,
  `postTitle` varchar(255) DEFAULT NULL,
  `postContent` varchar(255) DEFAULT NULL,
  `postDate` datetime DEFAULT NULL,
  `postViews` int DEFAULT NULL,
  PRIMARY KEY (`postCode`,`categoryCode_board`,`id`),
  KEY `id_idx` (`id`),
  KEY `fk_categoryCode_board` (`categoryCode_board`),
  CONSTRAINT `fk_categoryCode_board` FOREIGN KEY (`categoryCode_board`) REFERENCES `category_board` (`categoryCode_board`),
  CONSTRAINT `fk_id` FOREIGN KEY (`id`) REFERENCES `accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_board`
--

DROP TABLE IF EXISTS `category_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_board` (
  `categoryCode_board` int NOT NULL AUTO_INCREMENT,
  `categoryName_board` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`categoryCode_board`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_board`
--

LOCK TABLES `category_board` WRITE;
/*!40000 ALTER TABLE `category_board` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_code`
--

DROP TABLE IF EXISTS `category_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_code` (
  `category_code` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `ref_name` varchar(20) DEFAULT NULL,
  `ref_code` int DEFAULT NULL,
  PRIMARY KEY (`category_code`),
  KEY `ref_code_idx` (`ref_code`),
  CONSTRAINT `ref_code` FOREIGN KEY (`ref_code`) REFERENCES `category_code` (`category_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_code`
--

LOCK TABLES `category_code` WRITE;
/*!40000 ALTER TABLE `category_code` DISABLE KEYS */;
INSERT INTO `category_code` VALUES (1,'의자','가구',NULL),(2,'책상','가구',1),(3,'옷장','가구',1);
/*!40000 ALTER TABLE `category_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `CommentCode` int NOT NULL,
  `PostCode` int NOT NULL,
  `CategoryCode` int NOT NULL,
  `Id` varchar(30) NOT NULL,
  `Comment` varchar(255) DEFAULT NULL,
  `CommentDate` date DEFAULT NULL,
  PRIMARY KEY (`CommentCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon` (
  `coupon_number` int NOT NULL AUTO_INCREMENT,
  `id` varchar(30) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `expirydate` date DEFAULT NULL,
  `date_of_issue` date DEFAULT NULL,
  `discount_rate` float DEFAULT NULL,
  `coupon_comment` varchar(255) DEFAULT NULL,
  `coupon_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`coupon_number`,`id`),
  KEY `id` (`id`),
  CONSTRAINT `coupon_ibfk_1` FOREIGN KEY (`id`) REFERENCES `accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dormant_account`
--

DROP TABLE IF EXISTS `dormant_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dormant_account` (
  `id` varchar(30) NOT NULL,
  `enter_date` date NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'Y',
  `dormant_termination_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `ref_id` FOREIGN KEY (`id`) REFERENCES `accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dormant_account`
--

LOCK TABLES `dormant_account` WRITE;
/*!40000 ALTER TABLE `dormant_account` DISABLE KEYS */;
INSERT INTO `dormant_account` VALUES ('stv','2023-12-04','Y','2023-12-04');
/*!40000 ALTER TABLE `dormant_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_code` int NOT NULL AUTO_INCREMENT,
  `price` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `amount` int DEFAULT NULL,
  `enter_date` date DEFAULT NULL,
  `delete_status` char(1) DEFAULT 'N',
  PRIMARY KEY (`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-04 15:13:04
