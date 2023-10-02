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
-- Table structure for table `MemoryData`
--

DROP TABLE IF EXISTS `MemoryData`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MemoryData` (
  `idMemoryData` int(11) NOT NULL,
  `idCentury` int(11) NOT NULL,
  `card1` varchar(45) DEFAULT NULL,
  `card2` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idMemoryData`,`idCentury`),
  KEY `fk_Memory_Century1_idx` (`idCentury`),
  CONSTRAINT `fk_Memory_Century1` FOREIGN KEY (`idCentury`) REFERENCES `Century` (`idCentury`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MemoryData`
--

LOCK TABLES `MemoryData` WRITE;
/*!40000 ALTER TABLE `MemoryData` DISABLE KEYS */;
INSERT INTO `MemoryData` VALUES (1,1,'Burkard','DioceseFoundation'),(2,1,'Marienberg','Fortress'),(3,1,'Grafeneckart','KingWenzel'),(4,1,'RomanesqueCathedrals','Salier'),(5,1,'SaintKilian','Martyrdom'),(6,1,'BuilderEnzelin','DealWithTheDevil'),(7,2,'Bibra','Rennaissance'),(8,2,'Marienkapelle','CitizensChurch'),(9,2,'PeasantsWar','MorningStar'),(10,2,'Stachel','Tavern'),(11,2,'Echter','University'),(12,2,'Buergerspital','SterenAndTeufel');
/*!40000 ALTER TABLE `MemoryData` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-12 19:32:59
