CREATE DATABASE  IF NOT EXISTS `sopra-ws1718-team2` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sopra-ws1718-team2`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: hci-sopra.informatik.uni-wuerzburg.de    Database: sopra-ws1718-team2
-- ------------------------------------------------------
-- Server version	5.7.20

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
-- Table structure for table `User_has_Information`
--

DROP TABLE IF EXISTS `User_has_Information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_has_Information` (
  `idUser` int(11) NOT NULL,
  `idInformation` int(11) NOT NULL,
  `collectedCoin` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idUser`,`idInformation`),
  KEY `fk_User_has_Information_Information1_idx` (`idInformation`),
  KEY `fk_User_has_Information_User1_idx` (`idUser`),
  CONSTRAINT `fk_User_has_Information_Information1` FOREIGN KEY (`idInformation`) REFERENCES `Information` (`idInformation`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Information_User1` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_has_Information`
--

LOCK TABLES `User_has_Information` WRITE;
/*!40000 ALTER TABLE `User_has_Information` DISABLE KEYS */;
INSERT INTO `User_has_Information` VALUES (1,1,NULL),(3,3,1),(3,7,1),(3,11,1),(3,12,1),(3,13,1),(3,25,1),(4,1,1),(4,3,1),(4,4,1),(4,6,1),(4,7,1),(4,9,1),(4,11,1),(4,12,1),(4,13,1),(4,15,1),(4,16,1),(4,17,1),(4,19,1),(4,21,1),(4,24,1),(4,25,1),(4,27,1),(4,28,1),(5,1,1),(5,3,1),(5,6,1),(5,9,1),(5,11,1),(5,12,1),(9,1,1),(9,3,1),(9,4,1),(9,6,1),(9,7,1),(11,3,1),(11,6,1),(11,7,1),(15,1,1),(16,1,1),(16,6,1),(16,7,1);
/*!40000 ALTER TABLE `User_has_Information` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-12 19:33:06
