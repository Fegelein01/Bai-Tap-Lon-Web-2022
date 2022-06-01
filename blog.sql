-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: blog
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `tblcategory`
--

DROP TABLE IF EXISTS `tblcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblcategory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `parentid` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcategory`
--

LOCK TABLES `tblcategory` WRITE;
/*!40000 ALTER TABLE `tblcategory` DISABLE KEYS */;
INSERT INTO `tblcategory` VALUES (1,'Action1','note11',NULL),(2,'Health',NULL,NULL),(3,'Revengence',NULL,NULL),(5,'Test3','',NULL),(6,'Test4','',NULL),(7,'Test 5','None',NULL);
/*!40000 ALTER TABLE `tblcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcommenttest`
--

DROP TABLE IF EXISTS `tblcommenttest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblcommenttest` (
  `id` int NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `postid` int DEFAULT NULL,
  `parentid` int DEFAULT '-1',
  `username` varchar(255) DEFAULT NULL,
  `postdate` date DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_postid` (`postid`),
  KEY `fk_parentId` (`parentid`),
  CONSTRAINT `fk_parentId` FOREIGN KEY (`parentid`) REFERENCES `tblcommenttest` (`id`),
  CONSTRAINT `fk_postid` FOREIGN KEY (`postid`) REFERENCES `tblposttest` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1578 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcommenttest`
--

LOCK TABLES `tblcommenttest` WRITE;
/*!40000 ALTER TABLE `tblcommenttest` DISABLE KEYS */;
INSERT INTO `tblcommenttest` VALUES (-1,NULL,NULL,NULL,-1,NULL,NULL,NULL),(8,'Alex','I am a GOD',1,-1,NULL,'2022-04-04','(edited)'),(9,'Raiden','I am a god',1,-1,NULL,'2022-04-04',''),(10,'Raiden','I am a good too',1,-1,NULL,'2022-04-04',''),(11,'Raiden',NULL,1,-1,NULL,'2022-04-04','(edited)'),(13,'Raiden','hell yeal',2,-1,NULL,'2022-04-04','(edited)'),(14,'Raiden','hell yeal',2,-1,NULL,'2022-04-04','(edited)'),(15,'Raiden','hell yeal',2,-1,NULL,'2022-04-04',NULL),(996,'Raiden','hello',1,-1,NULL,'2022-04-04',NULL),(1528,'Raiden','444444444',1,-1,NULL,'2022-05-17',NULL),(1529,'Raiden','444444444',1,-1,NULL,'2022-05-17',NULL),(1530,'Raiden','hello',1,-1,NULL,'2022-05-17',NULL),(1545,'Raiden','12344aa4',4,-1,NULL,'2022-05-29','(edited)'),(1560,'Raiden','adsasda',4,1545,NULL,'2022-05-29','(edited)'),(1565,'Raiden','This is inspring',4,-1,NULL,'2022-05-31',NULL),(1566,'Raiden','This is inspring',4,-1,NULL,'2022-05-31',NULL),(1567,'Raiden','HAHAHA',4,-1,NULL,'2022-05-31',NULL),(1570,'Raiden','hello<br />comment line2<br />line 3',4,-1,'user1','2022-05-31',NULL);
/*!40000 ALTER TABLE `tblcommenttest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbldislike`
--

DROP TABLE IF EXISTS `tbldislike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbldislike` (
  `username` varchar(255) DEFAULT NULL,
  `postid` int DEFAULT NULL,
  KEY `username` (`username`),
  KEY `postid` (`postid`),
  CONSTRAINT `tbldislike_ibfk_1` FOREIGN KEY (`username`) REFERENCES `tblusertest` (`username`),
  CONSTRAINT `tbldislike_ibfk_2` FOREIGN KEY (`postid`) REFERENCES `tblposttest` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbldislike`
--

LOCK TABLES `tbldislike` WRITE;
/*!40000 ALTER TABLE `tbldislike` DISABLE KEYS */;
INSERT INTO `tbldislike` VALUES ('user1',25);
/*!40000 ALTER TABLE `tbldislike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbllike`
--

DROP TABLE IF EXISTS `tbllike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbllike` (
  `username` varchar(255) DEFAULT NULL,
  `postid` int DEFAULT NULL,
  KEY `username` (`username`),
  KEY `postid` (`postid`),
  CONSTRAINT `tbllike_ibfk_1` FOREIGN KEY (`username`) REFERENCES `tblusertest` (`username`),
  CONSTRAINT `tbllike_ibfk_2` FOREIGN KEY (`postid`) REFERENCES `tblposttest` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbllike`
--

LOCK TABLES `tbllike` WRITE;
/*!40000 ALTER TABLE `tbllike` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbllike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblpostcategory`
--

DROP TABLE IF EXISTS `tblpostcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblpostcategory` (
  `postid` int DEFAULT NULL,
  `categoryid` int DEFAULT NULL,
  KEY `fk_postidcategory` (`postid`),
  KEY `fk_categoryid` (`categoryid`),
  CONSTRAINT `fk_categoryid` FOREIGN KEY (`categoryid`) REFERENCES `tblcategory` (`id`),
  CONSTRAINT `fk_postidcategory` FOREIGN KEY (`postid`) REFERENCES `tblposttest` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblpostcategory`
--

LOCK TABLES `tblpostcategory` WRITE;
/*!40000 ALTER TABLE `tblpostcategory` DISABLE KEYS */;
INSERT INTO `tblpostcategory` VALUES (1,1),(1,2),(24,1),(24,3),(26,1),(26,2),(25,1),(2,1),(4,1),(4,2),(4,3),(39,1),(39,2),(39,3),(39,5),(42,6),(41,6),(41,7);
/*!40000 ALTER TABLE `tblpostcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblposttest`
--

DROP TABLE IF EXISTS `tblposttest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblposttest` (
  `id` int NOT NULL AUTO_INCREMENT,
  `author` varchar(100) DEFAULT NULL,
  `content` text,
  `username` varchar(255) DEFAULT NULL,
  `postDate` date DEFAULT NULL,
  `lastEdit` date DEFAULT NULL,
  `view` int DEFAULT NULL,
  `title` text,
  `up` int DEFAULT '0',
  `down` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblposttest`
--

LOCK TABLES `tblposttest` WRITE;
/*!40000 ALTER TABLE `tblposttest` DISABLE KEYS */;
INSERT INTO `tblposttest` VALUES (1,'John','Red sun\nRed sun over paradise\nRed sun\nRed sun over paradise\n\nGolden rays of the glorious sunshine\n\nSetting down, such a blood-red light\nNow the animals slowly retreat\nTo the shadows – out of sight\n\nArid breeze blows across the mountains\nGiving flight to the birds of prey\nIn the distance machines come\nTo transform Eden – day by day\n\nOnly love is with us now\nSomething warm and pure\nFind the peace within ourselves / Violence breeds within ourselves*\nNo need for a cure\n\nWhen the wind is slow\nAnd the fire’s hot\nThe vulture waits to see what rots\n\nOh how pretty\nAll the scenery\n\nThis is nature’s sacrifice\n\nWhen the air blows through\nWith a brisk attack\nThe reptile tail ripped from its back\n\nWhen the sun sets\nWe will not forget the\nRed sun over paradise\n\nRed sun','user1','2022-01-01','2025-01-01',23,'A',0,0),(2,'Alex','Alulalulela','user2','2022-04-04','2022-05-24',5,'B',0,0),(4,'Sam','This is nice1111','user1','2025-01-01','2022-05-31',108,'D141',23,13),(18,'','A','','2022-05-16','2022-05-16',5,'Test',20,1),(23,'','HAVE ACTION AND REVENGENCE','','2022-05-17','2022-05-17',1,'TEST CATE',0,0),(24,'Raiden','ACTION AND REVENGENCE','user1','2022-05-17','2022-05-17',2,'TEST CAG 2',0,0),(25,'Raiden','ABCDEFGH','user1','2022-05-23','2022-05-24',5,'Test 4',0,1),(26,'Raiden','AAAAAAA','user1','2022-05-23','2022-05-23',1,'Test 4',0,0),(29,'Raiden','Hello \r\nhow are you?\r\nI\'m fine, i guess','user1','2022-05-28','2022-05-28',2,'Hello world!!!',0,0),(34,'Raiden','111','user1','2022-05-29','2022-05-29',2,'AC',0,0),(35,'Raiden','111','user1','2022-05-29','2022-05-29',0,'BC',0,0),(36,'Raiden','line 1<br />line2<br />line 3<br />line 4','user1','2022-05-31','2022-05-31',8,'Test multiple line blog',0,0),(37,'','aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa','','2022-05-31','2022-05-31',3,'Muliple line 2',0,0),(38,'Raiden','Hellooooo<br />GSDASD<br />HASDASDAS<br />AdsASDASD<br />ASDASd','user1','2022-05-31','2022-05-31',3,'AAA111',0,0),(39,'Raiden','3','user1','2022-05-31','2022-05-31',0,'3',0,0),(41,'TESTWEB','test1','test1','2022-06-01','2022-06-01',2,'BT1',0,0),(42,'TESTWEB','AAA','test1','2022-06-01','2022-06-01',1,'BT2',0,0);
/*!40000 ALTER TABLE `tblposttest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblusertest`
--

DROP TABLE IF EXISTS `tblusertest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblusertest` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` char(64) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblusertest`
--

LOCK TABLES `tblusertest` WRITE;
/*!40000 ALTER TABLE `tblusertest` DISABLE KEYS */;
INSERT INTO `tblusertest` VALUES (1,'user1','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','Raiden111','admin','viethung682001@gmail.com'),(10,'user123','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','Hung','mob','viethungnumber2@gmail.com'),(13,'user112','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Hung','mob','A@BB'),(24,'test1','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','TESTWEB','admin','viethungnumber3@gmail.com');
/*!40000 ALTER TABLE `tblusertest` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-01 22:35:09
