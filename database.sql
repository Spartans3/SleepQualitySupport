-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: qualitysleep
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `data`
--

DROP TABLE IF EXISTS `data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data` (
  `Heat` varchar(40) DEFAULT NULL,
  `Humidity` varchar(40) DEFAULT NULL,
  `AirQuality` varchar(40) DEFAULT NULL,
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data`
--

LOCK TABLES `data` WRITE;
/*!40000 ALTER TABLE `data` DISABLE KEYS */;
INSERT INTO `data` VALUES ('27.00','27.00','382.56	',1),('27.00','27.00','397.67	',2),('27.00','27.00','381.08	',3),('27.00','27.00','396.14	',4),('27.00','27.00','381.08	',5),('27.00','27.00','396.14	',6),('27.00','27.00','381.08	',7),('27.00','27.00','396.14	',8),('27.00','27.00','381.08	',9),('27.00','27.00','396.14	',10),('27.00','27.00','382.56	',11),('27.00','27.00','396.14	',12),('27.00','27.00','381.08	',13),('27.00','27.00','396.14	',14),('27.00','27.00','381.08	',15),('27.00','27.00','394.62	',16),('27.00','27.00','381.08	',17),('27.00','27.00','396.14	',18),('27.00','27.00','381.08	',19),('27.00','27.00','394.62	',20),('27.00','27.00','381.08	',21),('27.00','27.00','394.62	',22),('28.00','28.00','379.59	',23),('27.00','27.00','394.62	',24),('27.00','27.00','379.59	',25),('27.00','27.00','394.62	',26),('27.00','27.00','379.59	',27),('27.00','27.00','394.62	',28),('27.00','27.00','379.59	',29),('27.00','27.00','394.62	',30),('27.00','27.00','379.59	',31),('27.00','27.00','394.62	',32),('27.00','27.00','379.59	',33),('27.00','27.00','394.62	',34),('28.00','28.00','379.59	',35),('28.00','28.00','394.62	',36),('27.00','27.00','379.59	',37),('28.00','28.00','394.62	',38),('27.00','27.00','378.12	',39),('27.00','27.00','393.09	',40),('27.00','27.00','379.59	',41),('27.00','27.00','393.09	',42),('27.00','27.00','378.12	',43),('27.00','27.00','393.09	',44),('27.00','27.00','379.59	',45),('27.00','27.00','393.09	',46),('27.00','27.00','378.12	',47),('27.00','27.00','394.62	',48),('27.00','27.00','378.12	',49),('27.00','27.00','391.58	',50),('27.00','27.00','376.64	',51),('27.00','27.00','393.09	',52),('28.00','28.00','376.64	',53),('27.00','27.00','391.58	',54),('27.00','27.00','376.64	',55),('27.00','27.00','393.09	',56),('27.00','27.00','375.17	',57),('27.00','27.00','391.58	',58),('27.00','27.00','376.64	',59),('28.00','30.00','432.52	',60),('28.00','30.00','422.79	',61),('28.00','30.00','445.75	',62),('28.00','30.00','490.91	',63),('28.00','29.00','413.24	',64),('28.00','29.00','396.14	',65),('28.00','29.00','445.75	',66),('28.00','29.00','452.48	',67),('28.00','29.00','384.05	',68),('28.00','29.00','439.10	',69),('28.00','30.00','387.05	',70),('28.00','30.00','390.06	',71),('27.00','30.00','426.02	',72),('27.00','30.00','430.89	',73),('27.00','30.00','435.80	',74),('26.00','55.00','256.32	',79),('26.00','52.00','238.09	',80),('26.00','48.00','307.06	',81),('23.00','38.00','457.58	',82),('23.00','38.00','467.91	',83),('23.00','37.00','489.11	',84),('23.00','37.00','503.64	',85),('23.00','37.00','478.42	',86);
/*!40000 ALTER TABLE `data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `led`
--

DROP TABLE IF EXISTS `led`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `led` (
  `ledpower` int(10) unsigned DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `led`
--

LOCK TABLES `led` WRITE;
/*!40000 ALTER TABLE `led` DISABLE KEYS */;
INSERT INTO `led` VALUES (0),(0),(0),(0),(0),(0),(0),(0),(0),(0),(0),(0),(0),(0),(0),(0),(0),(0),(0),(0),(0),(0),(0),(0),(0),(0),(1);
/*!40000 ALTER TABLE `led` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `Username` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('root','root');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-22 15:42:50
