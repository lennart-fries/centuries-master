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
-- Table structure for table `User_has_MultipleChoiceQuestion`
--

DROP TABLE IF EXISTS `User_has_MultipleChoiceQuestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_has_MultipleChoiceQuestion` (
  `idUser` int(11) NOT NULL,
  `idQuestion` int(11) NOT NULL,
  `selectedAnswer` tinyint(1) NOT NULL,
  `usedCard` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idUser`,`idQuestion`),
  KEY `fk_User_has_MultipleChoiceQuestion_MultipleChoiceQuestion1_idx` (`idQuestion`),
  KEY `fk_User_has_MultipleChoiceQuestion_User1_idx` (`idUser`),
  CONSTRAINT `fk_User_has_MultipleChoiceQuestion_MultipleChoiceQuestion1` FOREIGN KEY (`idQuestion`) REFERENCES `MultipleChoiceQuestion` (`idQuestion`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_MultipleChoiceQuestion_User1` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_has_MultipleChoiceQuestion`
--

LOCK TABLES `User_has_MultipleChoiceQuestion` WRITE;
/*!40000 ALTER TABLE `User_has_MultipleChoiceQuestion` DISABLE KEYS */;
INSERT INTO `User_has_MultipleChoiceQuestion` VALUES (1,1,1,1),(1,2,2,NULL),(1,3,4,1),(1,4,1,NULL),(2,1,1,1),(2,2,2,NULL),(2,3,4,NULL),(2,4,1,NULL),(2,5,3,NULL),(2,36,3,NULL),(3,1,1,1),(3,2,2,1),(3,3,4,1),(3,4,1,NULL),(3,5,3,NULL),(3,6,1,NULL),(3,7,3,NULL),(3,8,1,NULL),(3,9,4,NULL),(3,10,3,NULL),(3,11,1,1),(3,12,2,NULL),(3,13,1,1),(3,14,4,NULL),(3,15,4,NULL),(3,16,1,1),(3,17,3,1),(3,18,4,1),(3,19,2,NULL),(3,20,3,NULL),(3,21,1,1),(3,22,3,NULL),(3,23,1,NULL),(3,24,4,NULL),(3,25,3,NULL),(3,26,1,NULL),(3,27,2,NULL),(3,28,1,NULL),(3,29,4,NULL),(3,30,1,NULL),(3,31,1,NULL),(3,32,4,1),(3,33,2,1),(3,34,2,NULL),(3,35,3,NULL),(4,1,1,1),(4,2,2,1),(4,3,4,1),(4,4,1,NULL),(4,5,3,NULL),(4,6,1,NULL),(4,7,3,NULL),(4,8,1,NULL),(4,9,4,NULL),(4,10,3,NULL),(4,11,1,NULL),(4,12,2,NULL),(4,26,1,NULL),(4,31,1,NULL),(4,32,4,1),(4,33,2,NULL),(4,34,1,NULL),(4,35,3,NULL),(4,41,3,NULL),(4,42,1,NULL),(4,43,3,NULL),(4,44,2,NULL),(4,45,1,NULL),(5,1,1,1),(5,2,2,1),(5,3,4,1),(5,4,1,NULL),(5,5,3,NULL),(5,6,1,NULL),(5,7,3,NULL),(5,8,1,NULL),(5,9,4,NULL),(5,10,3,NULL),(5,11,1,NULL),(5,12,2,NULL),(5,13,1,NULL),(5,14,4,NULL),(5,15,4,NULL),(7,1,1,0),(7,2,2,0),(7,3,4,0),(7,4,1,0),(7,5,3,0),(7,6,1,0),(7,7,3,0),(7,8,1,0),(7,9,4,0),(7,10,3,0),(7,11,1,0),(7,12,2,0),(7,13,1,0),(7,14,4,0),(7,15,4,0),(9,1,1,NULL),(9,2,2,1),(9,3,4,NULL),(9,4,1,NULL),(9,5,3,NULL),(11,1,1,NULL),(11,2,2,NULL),(11,3,4,NULL),(11,4,1,NULL),(11,5,3,NULL),(11,6,1,NULL),(11,7,3,NULL),(11,8,1,NULL),(11,9,4,NULL),(11,10,3,NULL),(11,11,1,NULL),(11,12,2,NULL),(11,13,1,NULL),(11,14,3,NULL),(11,15,4,NULL),(11,16,1,1),(11,17,3,NULL),(11,18,4,1),(11,19,2,NULL),(11,20,3,NULL),(11,21,1,NULL),(11,22,3,NULL),(11,23,1,NULL),(11,24,4,NULL),(11,25,3,NULL),(11,26,1,NULL),(11,27,2,1),(11,28,1,NULL),(11,29,4,NULL),(11,30,3,NULL),(11,36,2,NULL),(11,37,2,NULL),(11,38,3,NULL),(11,39,2,NULL),(11,40,3,NULL),(11,41,3,1),(11,42,1,1),(11,43,3,1),(11,44,2,1),(11,45,1,NULL),(15,1,1,NULL),(15,2,2,1),(15,3,4,1),(15,4,1,1),(15,5,2,NULL),(16,1,1,NULL),(16,2,2,NULL),(16,3,4,NULL),(16,4,4,NULL),(16,5,4,NULL),(16,6,2,NULL),(16,7,3,NULL),(16,8,1,NULL),(16,9,4,NULL),(16,10,3,1),(16,11,1,NULL),(16,12,2,NULL),(16,13,1,NULL),(16,14,3,NULL),(16,15,4,NULL);
/*!40000 ALTER TABLE `User_has_MultipleChoiceQuestion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-12 19:33:00
