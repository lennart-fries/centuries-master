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
-- Table structure for table `Cards`
--

DROP TABLE IF EXISTS `Cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cards` (
  `idCards` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('FOOL','JUDGEMENT','WHEEL_OF_FORTUNE') DEFAULT NULL,
  `idUser` int(11) NOT NULL,
  PRIMARY KEY (`idCards`,`idUser`),
  KEY `fk_Cards_User1_idx` (`idUser`),
  CONSTRAINT `fk_Cards_User1` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cards`
--

LOCK TABLES `Cards` WRITE;
/*!40000 ALTER TABLE `Cards` DISABLE KEYS */;
INSERT INTO `Cards` VALUES (1,'FOOL',4),(4,'WHEEL_OF_FORTUNE',4),(6,'FOOL',3),(7,'WHEEL_OF_FORTUNE',3),(8,'JUDGEMENT',3),(10,'WHEEL_OF_FORTUNE',3),(13,'WHEEL_OF_FORTUNE',3),(16,'WHEEL_OF_FORTUNE',3),(19,'WHEEL_OF_FORTUNE',3),(22,'WHEEL_OF_FORTUNE',3),(26,'FOOL',3),(27,'FOOL',3),(28,'FOOL',3),(29,'WHEEL_OF_FORTUNE',3),(31,'FOOL',3),(32,'WHEEL_OF_FORTUNE',3),(34,'FOOL',3),(35,'FOOL',3),(36,'FOOL',3),(37,'FOOL',3),(38,'WHEEL_OF_FORTUNE',3),(39,'FOOL',3),(45,'FOOL',3),(46,'FOOL',3),(47,'FOOL',3),(49,'WHEEL_OF_FORTUNE',3),(54,'JUDGEMENT',4),(55,'JUDGEMENT',4),(56,'JUDGEMENT',4),(57,'WHEEL_OF_FORTUNE',4),(58,'WHEEL_OF_FORTUNE',4),(59,'FOOL',4),(61,'JUDGEMENT',4),(80,'FOOL',16),(82,'WHEEL_OF_FORTUNE',5);
/*!40000 ALTER TABLE `Cards` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-12 19:33:04
