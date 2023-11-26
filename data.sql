-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: springcommerce
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grade` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `ratio` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` VALUES ('HG','High Grade','1/144'),('MG','Master Grade','1/100'),('PG','Perfect Grade','1/60'),('RG','Real Grade','1/144'),('SD','Super Deformed',NULL);
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `box_image` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `equipments` varchar(255) DEFAULT NULL,
  `front_image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `remaining_quantity` int NOT NULL,
  `series` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKidvb36bkv34nw9uweyyj01u2n` (`grade`),
  CONSTRAINT `FKidvb36bkv34nw9uweyyj01u2n` FOREIGN KEY (`grade`) REFERENCES `grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'1699939140967_a03c8637-9768-45d5-b204-602909034200.jpg','Độ chi tiết vừa phải, ráp theo kiểu bấm khớp, không cần dùng keo dán. Thuộc thế hệ HG mới có độ linh hoạt khớp cao hơn, biên độ chuyển động lớn.','chùy lớn, trường kiếm, 2 phần giáp ống tay.','1699939140970_6dbcf30b-d932-4891-9030-ce65a70fe1c6.jpg','Gundam Barbatos HG - 1/144 - Mô hình lắp ráp chính hãng Bandai',290000,100,'Mobile Suit Gundam: Iron-Blooded Orphans','HG'),(2,'1699939636249_15ea924b-3220-4096-92fb-3ee52720a204.jpg','Độ chi tiết vừa phải, khớp chuyển động tương đối linh hoạt. Ráp theo kiểu bấm khớp, không cần dùng keo dán.','GN Sword II x2','1699939636250_9edc5f30-ecf3-4193-a60f-bf5e9a18165c.jpg','00 Gundam (HG - 1/144)',360000,50,'Mobile Suit Gundam 00','HG'),(3,'1699940066959_c66cba6d-8130-4c90-86d2-ba2e85ebc9f8.jpg','Độ chi tiết cực cao, có khung xương chuyển động linh hoạt. Ráp theo kiểu bấm khớp, không cần dùng keo dán.','Beam Magnum x1, Beam Magnum backup cartridge x1, Beam gatling gun x2, Beam saber x4, Beam bazooka x1, Shield x1','1699940066961_4c4c8151-1d9a-47c4-bbfd-d87e265707b5.jpg','Unicorn Gundam (PG - 1/60)',4152000,18,'Mobile Suit Gundam Unicorn','PG'),(4,'1699940470044_86d0c69f-345c-4c6d-9af0-c4eec05ba465.jpg','Độ chi tiết cực cao, có khung xương chuyển động linh hoạt. Ráp theo kiểu bấm khớp, không cần dùng keo dán.','Beam Rifle x2, Railgun x2, Super Dragoon x8, Beam Shield','1699940470045_a8b523c0-43a8-4724-bc1d-31c091a7f3c6.jpg','Strike Freedom Gundam (PG - 1/60)',4952000,20,'Mobile Suit Gundam SEED','PG'),(5,'1699940888348_cb099b0b-0c58-4cbb-a974-2c02b8f23052.jpg','Độ chi tiết cao, có khung xương chuyển động linh hoạt. Ráp theo kiểu bấm khớp, không cần dùng keo dán.','Beam Rifle, Beam Saber x2, Large Beam Saber, New Hyper Bazooka, Shield (beam cannon), Fin Funnel x6, Missiles','1699940888349_1556e341-3f60-422c-a737-9fa6e6a5772c.jpg','RX-93 Nu Gundam (RG - 1/144)',1280000,40,'Mobile Suit Gundam: Char\'s Counterattack','RG'),(6,'1699941226577_57800a9f-8a80-41fb-9c46-7d166bf43651.jpg','Độ chi tiết cao, có khung xương chuyển động linh hoạt. Ráp theo kiểu bấm khớp, không cần dùng keo dán.','Buster Rifle, Beam Saber, Shield','1699941226580_3e4f5541-de0d-4e32-b3e9-36817c436bc3.jpg','XXXG-01W Wing Gundam (RG - 1/144)',880000,80,'Mobile Suit Gundam Wing','RG'),(7,'1699941757466_de21516b-f0d2-473b-b70c-bbb9ee156dee.jpg','Độ chi tiết cao, có khung xương chuyển động linh hoạt. Ráp theo kiểu bấm khớp, không cần dùng keo dán.','GN Sniper Rifle x1, GN Beam Pistol x2, GN Beam Saber x2, GN Shield x2, GN Full Shield x2','1699941757467_fbd08b6b-441b-43c3-950c-79390aa49c14.jpg','Gundam Dynames (MG - 1/100)',1125000,30,'Mobile Suit Gundam 00','MG'),(8,'1699942284356_0ab3b1b0-6c4d-4e69-abfe-aaa37c7a3081.jpg','Độ chi tiết cao, có khung xương chuyển động linh hoạt. Ráp theo kiểu bấm khớp, không cần dùng keo dán.','Beam Rifle, Beam Saber, 2-tube Grenade Launcher, Shield','1699942284357_8b149e96-e2d9-4fdb-8c22-4405b0dcaed3.jpg','MSZ-006 Zeta Gundam Ver.Ka (MG 1/100)',1264000,30,'Mobile Suit Zeta Gundam','MG'),(9,'1699942963260_05043b77-c255-4de9-acf8-fc42eb0e7c96.jpg','Độ chi tiết thấp, khớp chuyển động tương đối linh hoạt. Ráp theo kiểu bấm khớp, không cần dùng keo dán.','Beam Magnum, Beam Saber, Shield','1699942963263_3656de29-666b-4ef5-b3b7-89bc14464e82.jpg','Unicorn Gundam (SD/BB)',320000,80,'Mobile Suit Zeta Gundam','SD'),(10,'1699943249541_c932e0ed-f831-4026-bc97-61f40c6f9fb2.jpg','Độ chi tiết thấp, khớp chuyển động tương đối linh hoạt. Ráp theo kiểu bấm khớp, không cần dùng keo dán.','Fatum-00, Beam Rifle, Laminated Anti-beam Shield','1699943249543_a9ab79a1-d054-46c0-aad4-af6bf3fda810.jpg','Justice Gundam - SD Gundam G Generation Seed',180000,80,'SD Gundam G Generation Neo','SD');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_image_paths`
--

DROP TABLE IF EXISTS `product_image_paths`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_image_paths` (
  `product_id` bigint NOT NULL,
  `image_paths` varchar(255) DEFAULT NULL,
  KEY `FKk5th7jkitvqpj3buceetxuviw` (`product_id`),
  CONSTRAINT `FKk5th7jkitvqpj3buceetxuviw` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_image_paths`
--

LOCK TABLES `product_image_paths` WRITE;
/*!40000 ALTER TABLE `product_image_paths` DISABLE KEYS */;
INSERT INTO `product_image_paths` VALUES (1,'1699939140994_642f246b-92cc-404f-bfd5-96feabb3db0f_0.jpg'),(1,'1699939140994_642f246b-92cc-404f-bfd5-96feabb3db0f_1.jpg'),(2,'1699939636254_637b1ab2-abfe-4ebb-8023-cecd23cb546d_0.jpg'),(2,'1699939636254_637b1ab2-abfe-4ebb-8023-cecd23cb546d_1.jpg'),(3,'1699940066979_db9b0121-f345-43ff-8af7-177fca378a98_0.jpg'),(3,'1699940066979_db9b0121-f345-43ff-8af7-177fca378a98_1.jpg'),(4,'1699940470074_170dfdb8-5947-4c3e-8605-9ca461360ff3_0.jpg'),(4,'1699940470074_170dfdb8-5947-4c3e-8605-9ca461360ff3_1.jpg'),(4,'1699940470074_170dfdb8-5947-4c3e-8605-9ca461360ff3_2.jpg'),(5,'1699940888350_a04f9c86-3d1d-4cd1-adb1-fd561be87fc1_0.jpg'),(5,'1699940888350_a04f9c86-3d1d-4cd1-adb1-fd561be87fc1_1.jpg'),(6,'1699941226582_ce4cd0ac-0d15-4754-8b00-dd4ca51773fa_0.jpg'),(6,'1699941226582_ce4cd0ac-0d15-4754-8b00-dd4ca51773fa_1.jpg'),(6,'1699941226582_ce4cd0ac-0d15-4754-8b00-dd4ca51773fa_2.jpg'),(7,'1699941757469_2990c28a-cab8-4ff7-a87f-ffafa74ee208_0.jpg'),(7,'1699941757469_2990c28a-cab8-4ff7-a87f-ffafa74ee208_1.jpg'),(7,'1699941757469_2990c28a-cab8-4ff7-a87f-ffafa74ee208_2.jpg'),(7,'1699941757469_2990c28a-cab8-4ff7-a87f-ffafa74ee208_3.jpg'),(8,'1699942284358_f28c879b-2207-4dc0-b581-fa2f4a2cef45_0.jpg'),(8,'1699942284358_f28c879b-2207-4dc0-b581-fa2f4a2cef45_1.jpg'),(9,'1699942963264_6d2bd319-3fb7-4f76-83d4-9ffe8dc813d5_0.jpg'),(9,'1699942963264_6d2bd319-3fb7-4f76-83d4-9ffe8dc813d5_1.jpg'),(9,'1699942963264_6d2bd319-3fb7-4f76-83d4-9ffe8dc813d5_2.jpg'),(10,'1699943249544_1ae9c67c-d4d9-41e0-930f-e9b74bd657bf_0.jpg');
/*!40000 ALTER TABLE `product_image_paths` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-26 16:56:18
