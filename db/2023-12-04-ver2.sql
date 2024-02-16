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
  `post_code` int NOT NULL AUTO_INCREMENT COMMENT '글코드',
  `categorycode_board` int NOT NULL COMMENT '게시판카테고리코드',
  `id` varchar(30) NOT NULL,
  `post_title` varchar(255) DEFAULT NULL,
  `post_content` varchar(255) DEFAULT NULL,
  `post_date` datetime DEFAULT NULL,
  `post_views` int DEFAULT NULL,
  PRIMARY KEY (`post_code`,`categorycode_board`,`id`),
  KEY `id_idx` (`id`),
  KEY `fk_categoryCode_board` (`categorycode_board`),
  CONSTRAINT `fk_categoryCode_board` FOREIGN KEY (`categorycode_board`) REFERENCES `category_board` (`categorycode_board`),
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
  `categorycode_board` int NOT NULL AUTO_INCREMENT COMMENT '게시판카테고리코드',
  `categoryname_board` varchar(30) DEFAULT NULL COMMENT '게시판카테고리명',
  PRIMARY KEY (`categorycode_board`)
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
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon` (
  `coupon_number` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `rate` float DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`coupon_number`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
INSERT INTO `coupon` VALUES (1,'7%할인 쿠폰',7,'첫 가입 유저를 위한 축하 쿠폰'),(2,'10%할인 쿠폰',10,'악성 유저 신고한 유저를 위한 포상 쿠폰'),(3,'20%할인 쿠폰',20,'12월 인테리어 첼린지 당첨자 쿠폰'),(4,'50%할인 쿠폰',50,'대 주주 분들을 위한 전용 쿠폰'),(5,'100%할인 쿠폰',100,'관리자 전용 숨겨진 쿠폰!');
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery`
--

DROP TABLE IF EXISTS `delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery` (
  `delivery_code` int NOT NULL COMMENT '배송코드',
  `payment_code` int NOT NULL COMMENT '결제코드',
  `id` int NOT NULL COMMENT '아이디',
  `delivery_address` int NOT NULL COMMENT '배송지번호',
  `product_code` int DEFAULT NULL COMMENT '상품콛'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='배송';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery`
--

LOCK TABLES `delivery` WRITE;
/*!40000 ALTER TABLE `delivery` DISABLE KEYS */;
/*!40000 ALTER TABLE `delivery` ENABLE KEYS */;
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
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `event_code` int NOT NULL AUTO_INCREMENT,
  `product_code` int NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `discount_rate` double DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `status` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`event_code`,`product_code`),
  KEY `product_code` (`product_code`),
  CONSTRAINT `event_ibfk_1` FOREIGN KEY (`product_code`) REFERENCES `product` (`product_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,2,'신규오픈이벤트',0.3,'2023-12-04','2024-01-04','Y'),(2,5,'종료된',0.2,'2023-10-04','2023-11-04','N');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `image_code` int NOT NULL,
  `product_code` int NOT NULL,
  `thumnail_path` varchar(255) DEFAULT NULL,
  `field_name` varchar(255) DEFAULT NULL,
  `originalImage` varchar(255) DEFAULT NULL,
  `modify_image` varchar(255) DEFAULT NULL,
  `modify_image_name` varchar(255) DEFAULT NULL,
  `is_delete` varchar(1) DEFAULT 'N',
  `status` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`image_code`,`product_code`),
  KEY `product_code` (`product_code`),
  CONSTRAINT `image_ibfk_1` FOREIGN KEY (`product_code`) REFERENCES `product` (`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `options` (
  `color_code` varchar(20) NOT NULL,
  `product_code` int NOT NULL,
  PRIMARY KEY (`color_code`,`product_code`),
  KEY `product_code` (`product_code`),
  CONSTRAINT `options_ibfk_1` FOREIGN KEY (`product_code`) REFERENCES `product` (`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES ('레드와인',2),('스카이블루',2),('오렌지',2),('블루베리블루',3),('지섭이흰머리색',4),('코발트화이트',5),('핫핑크',8);
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_code` int NOT NULL AUTO_INCREMENT,
  `id` varchar(30) NOT NULL,
  `delivery_address` int NOT NULL,
  `product_code` int NOT NULL,
  `price` int NOT NULL,
  `amout` int NOT NULL,
  `payment_date` date DEFAULT NULL,
  `deliver_request` varchar(255) NOT NULL DEFAULT '조심히 안전하게 와주세요',
  `delivery_cost` int NOT NULL DEFAULT '0',
  `coupon_number` int DEFAULT NULL,
  PRIMARY KEY (`payment_code`,`id`,`delivery_address`,`product_code`),
  KEY `id` (`id`),
  KEY `delivery_address` (`delivery_address`),
  KEY `product_code` (`product_code`),
  KEY `payment_ibfk_4_idx` (`coupon_number`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`id`) REFERENCES `accounts` (`id`),
  CONSTRAINT `payment_ibfk_2` FOREIGN KEY (`delivery_address`) REFERENCES `address` (`delivery_address`),
  CONSTRAINT `payment_ibfk_3` FOREIGN KEY (`product_code`) REFERENCES `product` (`product_code`),
  CONSTRAINT `payment_ibfk_4` FOREIGN KEY (`coupon_number`) REFERENCES `coupon` (`coupon_number`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,'root',1,5,90000,3,'2023-12-04','탱크타고 와주세요.',3000,NULL),(2,'root',1,2,78000,3,'2023-12-04','탱크타고 와주세요.',3000,NULL),(3,'root',1,7,53000,1,'2023-12-04','탱크타고 와주세요.',3000,NULL);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
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
  `delete_status` varchar(1) DEFAULT 'N',
  `category_code` int NOT NULL,
  PRIMARY KEY (`product_code`),
  KEY `category_code` (`category_code`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_code`) REFERENCES `product_category` (`category_code`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,21000,'식탁의자',55,'2023-12-04','N',3),(2,26000,'게이밍의자',55,'2023-12-04','N',3),(3,27000,'사무용의자',22,'2023-12-04','N',3),(4,15000,'인테리어의자',123,'2023-12-04','N',3),(5,30000,'사무용책상',21,'2023-12-04','N',4),(6,35000,'게이밍책상',23,'2023-12-04','N',4),(7,53000,'러버식탁',28,'2023-12-04','N',4),(8,60000,'발렌티스탠드',41,'2023-12-04','N',5),(9,9900,'무선LED센서등',31,'2023-12-04','N',5),(10,230000,'우물천장조명',54,'2023-12-04','N',5),(11,1600,'창문시트지',31,'2023-12-04','N',6),(12,8700,'유리시트지',28,'2023-12-04','N',6),(13,900,'풀바른벽지',66,'2023-12-04','N',7),(14,33000,'포인트벽지',10,'2023-12-04','N',7),(15,36000,'무선충전드라이버',25,'2023-12-04','N',8),(16,21000,'쇠망치',31,'2023-12-04','N',8);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_arrival_details`
--

DROP TABLE IF EXISTS `product_arrival_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_arrival_details` (
  `receive_code` int NOT NULL AUTO_INCREMENT,
  `product_code` int NOT NULL,
  `receive_Date` date DEFAULT NULL,
  `receive_amount` int DEFAULT NULL,
  PRIMARY KEY (`receive_code`,`product_code`),
  KEY `product_code` (`product_code`),
  CONSTRAINT `product_arrival_details_ibfk_1` FOREIGN KEY (`product_code`) REFERENCES `product` (`product_code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_arrival_details`
--

LOCK TABLES `product_arrival_details` WRITE;
/*!40000 ALTER TABLE `product_arrival_details` DISABLE KEYS */;
INSERT INTO `product_arrival_details` VALUES (1,1,'2023-12-04',32),(2,2,'2023-12-04',30),(3,3,'2023-12-04',30),(4,4,'2023-12-04',30),(5,5,'2023-12-04',30);
/*!40000 ALTER TABLE `product_arrival_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
  `category_code` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) DEFAULT NULL,
  `ref_category_code` int DEFAULT NULL,
  PRIMARY KEY (`category_code`),
  KEY `ref_category_code` (`ref_category_code`),
  CONSTRAINT `product_category_ibfk_1` FOREIGN KEY (`ref_category_code`) REFERENCES `product_category` (`category_code`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,'가구',NULL),(2,'자재',NULL),(3,'의자',1),(4,'책상',1),(5,'조명',1),(6,'시트지',2),(7,'벽지',2),(8,'공구',2);
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `post_code` int NOT NULL COMMENT '글코드',
  `categorycode_board` int NOT NULL COMMENT '게시판 카테고리코드',
  `product_code` int NOT NULL COMMENT '상품코드',
  `id` varchar(30) NOT NULL COMMENT '아이디',
  `score_review` decimal(2,1) DEFAULT NULL COMMENT '리뷰별점',
  `recommend_review` int DEFAULT NULL COMMENT '리뷰추천수',
  PRIMARY KEY (`post_code`,`categorycode_board`,`product_code`,`id`),
  KEY `review_accounts_id_fk` (`id`),
  KEY `review_category_board_categorycode_board_fk` (`categorycode_board`),
  KEY `review_product_code_idx` (`product_code`),
  CONSTRAINT `review_accounts_id_fk` FOREIGN KEY (`id`) REFERENCES `accounts` (`id`),
  CONSTRAINT `review_board_post_code_fk` FOREIGN KEY (`post_code`) REFERENCES `board` (`post_code`),
  CONSTRAINT `review_category_board_categorycode_board_fk` FOREIGN KEY (`categorycode_board`) REFERENCES `category_board` (`categorycode_board`),
  CONSTRAINT `review_product_code` FOREIGN KEY (`product_code`) REFERENCES `product` (`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='리뷰게시판';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopping_cart`
--

DROP TABLE IF EXISTS `shopping_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopping_cart` (
  `id` varchar(30) NOT NULL,
  `product_code` int NOT NULL,
  `enter_date` date DEFAULT NULL,
  `amount` int DEFAULT '1',
  PRIMARY KEY (`id`,`product_code`),
  KEY `product_code` (`product_code`),
  CONSTRAINT `shopping_cart_ibfk_1` FOREIGN KEY (`id`) REFERENCES `accounts` (`id`),
  CONSTRAINT `shopping_cart_ibfk_2` FOREIGN KEY (`product_code`) REFERENCES `product` (`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping_cart`
--

LOCK TABLES `shopping_cart` WRITE;
/*!40000 ALTER TABLE `shopping_cart` DISABLE KEYS */;
INSERT INTO `shopping_cart` VALUES ('hk',1,'2023-12-04',4),('hk',5,'2023-12-04',2),('hk',6,'2023-12-04',1);
/*!40000 ALTER TABLE `shopping_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_coupon`
--

DROP TABLE IF EXISTS `user_coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_coupon` (
  `id` varchar(30) NOT NULL,
  `coupon_number` int NOT NULL,
  `expiry_date` date DEFAULT NULL,
  `date_of_issue` date DEFAULT NULL,
  `coupon_status` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`,`coupon_number`),
  KEY `coupon_number` (`coupon_number`),
  CONSTRAINT `user_coupon_ibfk_1` FOREIGN KEY (`id`) REFERENCES `accounts` (`id`),
  CONSTRAINT `user_coupon_ibfk_2` FOREIGN KEY (`coupon_number`) REFERENCES `coupon` (`coupon_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_coupon`
--

LOCK TABLES `user_coupon` WRITE;
/*!40000 ALTER TABLE `user_coupon` DISABLE KEYS */;
INSERT INTO `user_coupon` VALUES ('hk',5,'2023-12-04','2024-01-04','Y'),('jc',5,'2023-12-04','2024-01-04','Y'),('kj',5,'2023-12-04','2024-01-04','Y'),('root',5,'2023-12-04','2024-01-04','Y'),('sy',5,'2023-12-04','2024-01-04','Y');
/*!40000 ALTER TABLE `user_coupon` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-04 17:44:30
