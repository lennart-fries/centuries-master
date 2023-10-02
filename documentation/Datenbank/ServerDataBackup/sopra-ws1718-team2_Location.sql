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
-- Table structure for table `Location`
--

DROP TABLE IF EXISTS `Location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Location` (
  `idLocation` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `backgroundPath` varchar(255) NOT NULL,
  `idCentury` int(11) NOT NULL,
  PRIMARY KEY (`idLocation`,`idCentury`),
  KEY `fk_Location_Century1_idx` (`idCentury`),
  CONSTRAINT `fk_Location_Century1` FOREIGN KEY (`idCentury`) REFERENCES `Century` (`idCentury`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Location`
--

LOCK TABLES `Location` WRITE;
/*!40000 ALTER TABLE `Location` DISABLE KEYS */;
INSERT INTO `Location` VALUES (1,'Burkaduskirche','images/level1/burkaduskirche.jpg',1),(2,'Festung','images/level1/festung_mit_brunnen.png',1),(3,'Grafeneckart','images/level1/Wenzelsaal_high_res1.png',1),(4,'Dom','images/level1/Dom_perspective.png',1),(5,'Neumuenster','images/level1/neumuenster.jpg',1),(6,'Lusamgaertchen','images/level1/lusamgarten_high_res1.png',1),(7,'Alte Mainbruecke','images/level1/alteMainbruecke.jpg',1),(8,'Dom','images/level2/dom.png',2),(9,'Festung','images/level2/echtertor.png',2),(10,'Marienkapelle','images/level2/Marienkapelle.png',2),(11,'Stachel','images/level2/stachel.png',2),(12,'Juliusspital','images/level2/juliusspital.png',2),(13,'Universitaet','images/level2/alteUni.jpg',2),(14,'Buergerspital','images/level2/buergerspital.jpg',2),(15,'Festung','images/level3/festung.jpg',3),(16,'Residenz','images/level3/residenz.jpg',3),(17,'Grabkapelle','images/level3/grabkapelle_close_up.jpg	',3),(18,'Kaepelle','images/level3/kaeppele.jpg',3),(19,'Vierroehrenbrunnen','images/level3/vierroehrenbrunnen.jpg',3),(20,'Neumuenster','images/level3/neumuenster.jpg',3),(21,'Stift Haug','images/level3/StiftHaug.jpg',3),(22,'Stadtmauer','images/level3/stadtmauer.jpg',3),(23,'Falkenhaus','images/level3/falkenhaus.jpg',3),(24,'Juliusspital','images/level3/juliusspital.jpg',3),(25,'Alte Mainbruecke','images/level3/alteMainbruecke.jpg',3),(26,'Alter Kranen','images/level3/alterKran.jpg',3),(27,'Kaufhaus am Markt','images/level3/marktkaufhaus.jpg',3),(28,'Rotkreuzklinik','images/level3/rotkreuzklinik.jpg',3);
/*!40000 ALTER TABLE `Location` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-12 19:32:57
