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
-- Table structure for table `User_has_Quiz`
--

DROP TABLE IF EXISTS `User_has_Quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_has_Quiz` (
  `idUser` int(11) NOT NULL,
  `idQuiz` int(11) NOT NULL,
  `solved` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`idUser`,`idQuiz`),
  KEY `fk_User_has_Quiz_Quiz1_idx` (`idQuiz`),
  KEY `fk_User_has_Quiz_User1_idx` (`idUser`),
  CONSTRAINT `fk_User_has_Quiz_Quiz1` FOREIGN KEY (`idQuiz`) REFERENCES `Quiz` (`idQuiz`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Quiz_User1` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_has_Quiz`
--

LOCK TABLES `User_has_Quiz` WRITE;
/*!40000 ALTER TABLE `User_has_Quiz` DISABLE KEYS */;
INSERT INTO `User_has_Quiz` VALUES (2,1,80),(2,2,60),(2,3,80),(2,4,100),(2,5,60),(2,6,60),(3,1,100),(3,2,100),(3,3,100),(3,4,100),(3,5,100),(3,6,100),(3,7,60),(4,1,100),(4,2,100),(4,3,100),(4,4,100),(4,5,100),(4,6,100),(4,7,100),(4,8,100),(4,9,100),(5,1,100),(5,2,100),(5,3,100),(7,1,100),(7,2,100),(7,3,80),(9,1,100),(11,1,100),(11,2,100),(11,3,100),(11,4,100),(11,5,100),(11,6,80),(11,8,80),(11,9,100),(15,1,80),(16,1,60),(16,2,80),(16,3,100);
/*!40000 ALTER TABLE `User_has_Quiz` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-12 19:33:07
