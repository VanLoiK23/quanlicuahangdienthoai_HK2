-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: quan_ly_ban_hang
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `phienbansanpham`
--

DROP TABLE IF EXISTS `phienbansanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phienbansanpham` (
  `maphienbansp` int NOT NULL AUTO_INCREMENT,
  `masp` int DEFAULT NULL,
  `rom` int DEFAULT NULL,
  `ram` int DEFAULT '0',
  `mausac` int DEFAULT NULL,
  `gianhap` int DEFAULT NULL,
  `giaxuat` int DEFAULT NULL,
  `soluongton` int DEFAULT '0',
  PRIMARY KEY (`maphienbansp`),
  KEY `FK_phienbansanpham_sanpham` (`masp`),
  KEY `FK_rom` (`rom`),
  KEY `FK_ram` (`ram`),
  KEY `FK_color` (`mausac`),
  CONSTRAINT `FK_color` FOREIGN KEY (`mausac`) REFERENCES `mausac` (`mamau`),
  CONSTRAINT `FK_phienbansanpham_sanpham` FOREIGN KEY (`masp`) REFERENCES `sanpham` (`masp`),
  CONSTRAINT `FK_ram` FOREIGN KEY (`ram`) REFERENCES `dungluongram` (`madlram`),
  CONSTRAINT `FK_rom` FOREIGN KEY (`rom`) REFERENCES `dungluongrom` (`madlrom`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phienbansanpham`
--

LOCK TABLES `phienbansanpham` WRITE;
/*!40000 ALTER TABLE `phienbansanpham` DISABLE KEYS */;
INSERT INTO `phienbansanpham` VALUES (1,1,1,4,3,19000000,22000000,0),(7,2,1,1,1,20000000,22000000,13),(10,4,1,4,2,1000,100,8),(13,1,2,5,1,20000000,23000000,6),(14,2,2,4,2,25000000,27000000,4),(17,3,2,4,2,2000,4000,2),(19,10,2,3,3,2000,600,0),(20,11,2,4,1,300000,600000,0),(22,13,4,5,2,500000,700000,0),(23,13,3,1,2,800000,1900000,0),(24,16,2,3,2,20000000,30000000,0),(25,12,1,3,2,10000000,13000000,0),(26,1,3,5,2,25000000,28000000,8),(27,2,3,5,3,30000000,33000000,8),(28,5,1,3,2,20000000,23000000,1),(29,5,2,4,3,25000000,28000000,6),(30,5,3,5,1,30000000,33000000,19),(31,15,1,3,3,20000000,23000000,6),(32,15,2,4,2,23000000,26000000,0),(33,15,3,5,1,26000000,33000000,19);
/*!40000 ALTER TABLE `phienbansanpham` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-02  9:59:02
