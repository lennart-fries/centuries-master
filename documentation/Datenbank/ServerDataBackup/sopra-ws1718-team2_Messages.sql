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
-- Table structure for table `Messages`
--

DROP TABLE IF EXISTS `Messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Messages` (
  `idMessages` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) DEFAULT NULL,
  `idReceiver` int(11) NOT NULL,
  `idSender` int(11) NOT NULL,
  PRIMARY KEY (`idMessages`,`idReceiver`,`idSender`),
  KEY `fk_Messages_User1_idx` (`idReceiver`),
  KEY `fk_Messages_User2_idx` (`idSender`),
  CONSTRAINT `fk_Messages_User1` FOREIGN KEY (`idReceiver`) REFERENCES `User` (`idUser`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_Messages_User2` FOREIGN KEY (`idSender`) REFERENCES `User` (`idUser`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Messages`
--

LOCK TABLES `Messages` WRITE;
/*!40000 ALTER TABLE `Messages` DISABLE KEYS */;
INSERT INTO `Messages` VALUES (1,'tolles messagesystem wow :O',2,4),(2,'testinbox',5,3),(3,'testoutbox',3,5),(4,'wohfewoif',4,5),(5,'woehgo',4,5),(6,'weghwhgowieh',4,5),(7,'hhhhhhhhhhhhhhhhhh',3,5),(8,'segp',1,5),(9,'lsghwpe',3,5),(10,'fefpihw',1,5),(11,'Hallo',6,3),(12,'test',3,4),(13,'This works!',7,4),(14,'Dinge ',6,3),(15,'Hallo Lisa',5,9),(16,'abc',3,5),(17,'def',2,5),(18,'work now?',7,4),(19,'Hallo!',9,4),(20,'Hallo!',9,4),(21,'huhu \r\nwas geht?',4,11),(22,'Test',4,3),(23,'hi, das Spiel ist super cooooolll',3,5),(24,'hi,\r\nsehr cool',3,15),(25,'hi,\r\nwie coooll',4,16);
/*!40000 ALTER TABLE `Messages` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-12 19:33:01
