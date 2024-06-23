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
-- Table structure for table `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanpham` (
  `masp` int NOT NULL AUTO_INCREMENT,
  `tensp` varchar(255) DEFAULT NULL,
  `hinhanh` varchar(255) DEFAULT NULL,
  `xuatxu` int DEFAULT NULL,
  `dungluongpin` int DEFAULT NULL,
  `kichthuocman` double DEFAULT NULL,
  `hedieuhanh` int DEFAULT NULL,
  `phienbanhdh` int DEFAULT NULL,
  `camerasau` varchar(255) DEFAULT NULL,
  `cameratruoc` varchar(255) DEFAULT NULL,
  `thoigianbaohanh` int DEFAULT NULL,
  `thuonghieu` int DEFAULT NULL,
  `khuvuckho` int DEFAULT NULL,
  `Soluongton` int DEFAULT '1',
  `trangthai` int DEFAULT '1',
  `giaxuat` int NOT NULL,
  `mapbansp` int NOT NULL,
  PRIMARY KEY (`masp`),
  UNIQUE KEY `unique_tensp` (`tensp`),
  KEY `FK_xuatxu` (`xuatxu`),
  KEY `FK_thuonghieu` (`thuonghieu`),
  KEY `FK_hedieuhan` (`hedieuhanh`),
  KEY `FK_kho` (`khuvuckho`),
  CONSTRAINT `FK_hedieuhan` FOREIGN KEY (`hedieuhanh`) REFERENCES `hedieuhanh` (`mahedieuhanh`),
  CONSTRAINT `FK_kho` FOREIGN KEY (`khuvuckho`) REFERENCES `khuvuckho` (`makhuvuc`),
  CONSTRAINT `FK_thuonghieu` FOREIGN KEY (`thuonghieu`) REFERENCES `thuonghieu` (`mathuonghieu`),
  CONSTRAINT `FK_xuatxu` FOREIGN KEY (`xuatxu`) REFERENCES `xuatxu` (`maxuatxu`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` VALUES (1,'iPhone 15  Pro Max','/D:/Code_java/Do_an_co_so_1/Sanpham/iphone-15-pro-max-blue-thumbnew-600x600.jpg',5,4500,6.7,2,1,'12 MP','8 MP',12,1,1,14,0,0,0),(2,'Samsung Galaxy Z Fold 3','/D:/Code_java/Do_an_co_so_1/Sanpham/samsung-galasy Z-fold3_5G.jpg',2,5000,6.2,1,5,'8 MP','5 MP',24,7,2,25,0,0,0),(3,'Realme','/D:/Code_java/Do_an_co_so_1/Sanpham/Realme.jpg',1,4000,6.8,1,4,'5 MP','2 MP',12,9,2,2,0,0,0),(4,'Oppo A57','/D:/Code_java/Do_an_co_so_1/Sanpham/oppo A57.jpg',1,4500,6.5,1,5,'8 MP','4 MP',12,8,3,8,1,0,0),(5,'Samsung Galaxy M54','/D:/Code_java/Do_an_co_so_1/Sanpham/samsung-galaxy-m54.jpg',1,5000,6.5,1,5,'8 MP','4 MP',12,7,1,26,0,0,0),(6,'Oppo A58','/D:/Code_java/Do_an_co_so_1/Sanpham/oppo A58.jpg',1,4000,6.5,1,5,'8 MP','4 MP',12,8,2,0,1,0,0),(9,'Xiaomi','/D:/Code_java/Do_an_co_so_1/Sanpham/Xiaomi12pro.jpg',1,1230,13,2,12,'1','1',14,1,1,0,0,0,0),(10,'SamSung','/D:/Code_java/Do_an_co_so_1/Sanpham/Realme.jpg',1,3000,14,1,3,'3MP','5MP',12,9,2,0,0,0,0),(11,'Hilight','/D:/Code_java/Do_an_co_so_1/Sanpham/oppo A57.jpg',4,30000,14,1,5,'3MP','5MP',12,1,1,0,0,0,0),(12,'Samsung M54','/D:/Code_java/Do_an_co_so_1/Sanpham/samsung-galaxy-m54.jpg',2,4500,15,1,5,'3MP','5MP',12,7,3,0,0,0,0),(13,'Zold3','/D:/Code_java/Do_an_co_so_1/Sanpham/samsung-galasy Z-fold3_5G.jpg',2,4000,16,1,5,'3MP','5MP',12,7,1,0,0,0,0),(15,'Oppo A5','/D:/Code_java/Do_an_co_so_1/Sanpham/oppo A57.jpg',4,5000,14,2,5,'4MP','5Mp',12,1,1,25,0,0,0),(16,'iPhone 15 Pro Max','/D:/Code_java/Do_an_co_so_1/Sanpham/iphone-15-pro-max-blue-thumbnew-600x600.jpg',2,4000,14,2,5,'3MP','5MP',12,1,1,0,0,0,0);
/*!40000 ALTER TABLE `sanpham` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-02  9:59:01
