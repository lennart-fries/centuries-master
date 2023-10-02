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
-- Table structure for table `MultipleChoiceQuestion`
--

DROP TABLE IF EXISTS `MultipleChoiceQuestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MultipleChoiceQuestion` (
  `idQuestion` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `answer1` varchar(255) NOT NULL,
  `answer2` varchar(255) NOT NULL,
  `answer3` varchar(255) NOT NULL,
  `answer4` varchar(255) NOT NULL,
  `correctAnswer` tinyint(1) NOT NULL,
  `idQuiz` int(11) NOT NULL,
  PRIMARY KEY (`idQuestion`,`idQuiz`),
  KEY `fk_Question_Quiz1_idx` (`idQuiz`),
  CONSTRAINT `fk_Question_Quiz1` FOREIGN KEY (`idQuiz`) REFERENCES `Quiz` (`idQuiz`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MultipleChoiceQuestion`
--

LOCK TABLES `MultipleChoiceQuestion` WRITE;
/*!40000 ALTER TABLE `MultipleChoiceQuestion` DISABLE KEYS */;
INSERT INTO `MultipleChoiceQuestion` VALUES (1,'Who were the Apostles of Franconia?','Kilian, Kolonat & Totnan\n','Kiliani, Kolonan & Totnat\n','Walther, Tilman & Matthias\n','Kasper, Melchior & Balthasar\n',1,1),(2,'What was the Apostles\' of Franconia cause of death?','The Duke didn\'t approve of them and their missionary attempts and sentenced them to death','The Duke\'s Wife, Gailana, let them be killed because they demanded the Duke to divorce her','They were attacked and killed by thieves','They all succumbed to the plague',2,1),(3,'Who was Wuerzburg\'s first ever Bishop?','Bonifatius','Kilian','Gosbert','Burkard',4,1),(4,'When did Wuerzburg become a diocese?','742','690','1337','808',1,1),(5,'What made Wuerzburg a popular city for piligrimage?','The fresh air','Wuerzburg had the most relics north of the alps','Wuerzburg had the most saints north of the alps','The bishop was said to be a great man and people wanted to come see him',3,1),(6,'Who ordered the construction of the Dom?','Bishop Bruno','Saint Bruno','Bishop Burkard','Saint Kilian',1,2),(7,'Where is another cathedral built by the Salier?','Trier','Berlin','Mainz','Nuernberg',3,2),(8,'Why was Wuerzburg frequented by merchants?','It was the most convenient place to cross the Main','Wuerzburg had especially wealthy citizens','The economy was the most developed out of all cities in the area','There were no thieves in Wuerzburg',1,2),(9,'Who was the builder of the bridge?','Saint Kilian','Neumann','Bishop Bruno','Enzelin',4,2),(10,'Why was a bridge needed in the first place?','It looked nice','The citizens wanted to be the first city with a bridge made completely of stone','Merchants had to be able to cross the Main even in times of flooding','The people did not want to swim to cross the Main',3,2),(11,'Who made the bishops of Wuerzburg into prince-bishops?','Barbarossa','The Pope','They themselves','Saint Kilian',1,3),(12,'What did it mean to be a prince-bishop?','The prince-bishop had to wear a crown','They were able to rule in clerical and secular affairs','It means that they had enough money to be a king','It didn\'t do anything, just sounded cool',2,3),(13,'Why was the fortress on Marienberg built?','The prince-bishop didn\'t feel safe anymore living down in the city','A good prince-bishop needed a good castle','Wuerzburg had too much money, so they built it','It was to have better view of Wuerzburg\'s surroundings',1,3),(14,'What did Walther von der Vogelweide do for a living?','He was a thief','He was a king','He was a singer','He wrote books',3,3),(15,'Who promised to make Wuerzburg a free city?','Saint Kilian','Emperor Karl','Bishop Bruno','King Wenzel',4,3),(16,'Why was Scherenberg elected as a prince-bishop?\r ','He was rich enough to settle Wuerzburgs debts and was expected to die soon after\n','He was very rich and bribed his way into the position\n','He was loved by citizens and the council\n','He was elected by the pope himself\n',1,4),(17,'How old was prince-bishop Scherenberg when he died?\r ','83','101','95','91',3,4),(18,'What did Lorenz von Bibra start building?\r ','The Residence in Wuerzburg','The Cathedral in Baroque style\r ','The city walls\r ','The Fortress in Rennaissance Style\r ',4,4),(19,'Who was the most famous Sculptor in Wuerzburg during the Gothic Times?\r','Walther von der Vogelweide\r','Tilman Riemenschneider\n','Saint Kilian\n','King Arthur\n',2,4),(20,'Riemenschneider also made the famous Heiligblutalter in …?\r','Berlin\n','Wuerzburg\n','Rothenburg\r','Munich\n',3,4),(21,'What was the church the citizens built all by themselves?\r','Marienkapelle\n','Cathedral\n','Saint Peter and Paul\r','Neubaukirche\n',1,5),(22,'What about the church made the bishop angry with the citizens?\r','He didn\'t like its colors\r ','That they occupied Tilman Riemenschneider with working on the chapel\r ','A bishop was sent to hell in its depiction of the doomsday\r','It was something he didn\'t order to build himself\r ',3,5),(23,'Where does the Phrase \"even the devil does good things in Wuerzburg\" come from?\r ','The Family Teufel, who were funders of the Buergerspital\r ','Enzelin, who was said to have a deal with the devil, built Würzburg\'s bridge','It doesn\'t exist\r ','Because the citizens figuratively sent a bishop to hell, which they though was a good thing\n',1,5),(24,'Why is the tavern called Stachel?\r ','Stachel is Franconian for Morning Star\r','No real reason\n','The place where it was built was already called Stachel','It is called that because the peasants met up there and their common weapon was a Stachel\n',4,5),(25,'What else occurred at the tavern called Stachel?\r','Sword fights\r ','Gourmet wine tastings\r ','Taxes were paid','It was also a post office\r ',3,5),(26,'How old was Julius Echter when he was elected as the prince-bishop?\r','28','45','39','50',1,6),(27,'What was Julius Echter\'s main agenda?\r','Reformation','Counter-Reformation\r ','Improving his own wealth\r ','A wage war with other cities\r ',2,6),(28,'What made Echter\'s University succeed?\n','Having a building for the students to live and learn\r in','Giving it a good name\n','Hiring the best of the best to teach at his University','Having his heart buried there after his death\n',1,6),(29,'For which purpose did Echter built the Juliusspital?\r ','As an arsenal for his weapons\r ','Only himself\r ','Rich people\r ','Sick people & orphans\r ',4,6),(30,'What do we learn from Echters mistakes building bastions?\r ','When building bastions you should be concerned with their functionality, not their looks','As long as your walls look nice, no one will dare to attack you','Archangel Michael is not the right Angel to stop Swedes','He never made mistakes',1,6),(31,'Which country were the architects of the walls brought from?\r ','Italy','England','France','Sweden\n',1,7),(32,'Why were walls built all around the city?\r ','Because the prince-bishops had too much money and wanted to use it\r ','It was an order from the Emperor\r ','Walls were only built atop the Marienberg\r ','To make Wuerzburg safe from all kinds of attacks\r ',4,7),(33,'Who built the Stift Haug?\n','Saint Kilian','Petrini','Greising','Prince bishop Greiffenclau',2,7),(34,'Who was responsible for a lot of renovation in the early Baroque times?','Greising','Neumann','Bishop Bruno','There were no Renovations',1,7),(35,'Petrini created the Franconian Baroque, which was a mix of ...?','No one really knows','Italian Renaissance & German Baroque','German Renaissance & Italian Baroque','There is no such thing as the Franconian Baroque',2,7),(36,'From which family was the prince-bishop that ordered the Residence to be built?','Schönborn','Greiffenclau','Seinsheim','Hutten',1,8),(37,'What is the Residnce modelled after?','Neuschwanstein','Versailles','Hohenschwangau','Buckingham Palace',2,8),(38,'Who built the Residence?','Petrini','Greising','Neumann','Enzelin',3,8),(39,'Who painted most of the large paintings in the Residence?','Neumann','Tiepolo','Petrini','Bossi',2,8),(40,'Which of these problems did Neumann NOT encounter while building the Residence?','There was no infrastructure and he had to build it all himself','The bishop who ordered it died and the next one halted work on the project','The funds stopped and he had to pay the workers himself','There were other Architects hired that wanted to be the main designers',3,8),(41,'Which of these did Neumann not build?','Kaepelle','Residence','Falkenhaus','Vierröhrenbrunnen',3,9),(42,'Why did Neumann build the Vierröhrenbrunnen?','He had just built the water infrastructure for the Residence so he could also use that for the well','He was told to prioritize the citizens\' well-being and thus build a flowing water well','He was feeling generous','He did not build it\n',1,9),(43,'How many projects did Neumann have as an architect before being assigned to build the Residence?','Around 10','Over 20','1 minor project','None at all',3,9),(44,'What did Neumann promise citizens that renovated their houses into stone houses?','One free pass to visit the Residence','A few years of no taxes','They would be refunded for their expenses','Less costy home insurance',2,9),(45,'What was the full name of the Neumann that was the most important person for Baroque-era Wuerzburg?','Johann Balthasar Neumann','Franz Ignaz Michael Neumann','Karl Friedrich Neumann\n','Philipp Franz Neumann',1,9);
/*!40000 ALTER TABLE `MultipleChoiceQuestion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-12 19:33:03
