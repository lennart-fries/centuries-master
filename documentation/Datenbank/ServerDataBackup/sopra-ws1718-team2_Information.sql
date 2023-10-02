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
-- Table structure for table `Information`
--

DROP TABLE IF EXISTS `Information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Information` (
  `idInformation` int(11) NOT NULL,
  `content` varchar(5000) DEFAULT NULL,
  `isCoin` tinyint(1) DEFAULT NULL,
  `idLocation` int(11) NOT NULL,
  PRIMARY KEY (`idInformation`,`idLocation`),
  KEY `fk_Information_Location1_idx` (`idLocation`),
  CONSTRAINT `fk_Information_Location1` FOREIGN KEY (`idLocation`) REFERENCES `Location` (`idLocation`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Information`
--

LOCK TABLES `Information` WRITE;
/*!40000 ALTER TABLE `Information` DISABLE KEYS */;
INSERT INTO `Information` VALUES (1,'<h2>Burkard</h2>\n<p>The Burkarduskirche, or rather \"Sankt Burkard\", was named after Burkard, the first bishop of Würzburg. Burkhard was an Anglosaxon born in the south-west of England who became a monk and started working as a missionary on the mainland. In 742 it was finally time: Burkard became bishop of Würzburg.</p>\n\n<h2>Holy bones</h2>\n<p>After a trip to Rome, bishop Burkard got lucky. Due to some excavations close to Würzburg, the bones of some Franconian apostles where found. This increased pilgrim tourism in the following years and made Würzburg rich.</p>\n\n<h2>Andreaskloster</h2>\n<p>The origin of this building lies with Burkard, the first bishop of Würzburg. He founded the so-called\nAndreaskloster here, named after the holy Andreas.</p>\n\n <h2>Death</h2>\n <p>In 755 Burkard, first bishop of Würzburg, died as a hermit in Homburg am Main. In 988 his bones were brought to the Andreaskloster. After that the Andreaskloster was renamed to Sankt Burkard.</p>',1,1),(2,'<h2>Ancestors</h2>\n\n<p>The location on top of the Marienberg hill is secure and provides a pretty good view of the surrounding area. That was known the Celtic people, who were the first to build a defensive flight castle there. But the Celts were expelled by Franks and after some time the Marienkapelle was built on top of the Marienberg. This small chapel had to be defended and was soon surrounded by walls made of wood and clay.</p>\n\n\n<h2>Early state</h2>\n\n<p>In its early state, the castle was surrounded by a 12 meter deep moat. After some time defensive courtyards were added, such as the Greiffenclauhof and the Echterhof. A 100-meter deep well was dug to serve as a water source in case of a siege.</p>\n\n<h2>Prince-bishops</h2>\n\n<p>When the bishops of Würzburg achieved the status of prince-bishops, they gained the right to rule in secular and clerical affairs, which meant firstly the right to raise taxes. Thus, the taxes were increased and the people of Würzburg grew angry, which lead to the murder of one of the prince-bishops. His successors stayed in the Marienberg Fortress for their own safety.</p>',0,2),(3,'<h2>Eckhart de Foro</h2>\n<p>This building was firstly owned by Graf Eckhart de Foro, an official and servant of the bishop of Würzburg. 1201 he was murdered by Bodo von Ravensburg and Heinrich von Falkenberg. Those two noblemen had to pay extra taxes to the bishop, but did not want to bow to these orders, so they sent the bishop a clear sign, by killing his friend. But that was not enough and they killed the bishop later. 1316 the city bought this house and made it the city hall. </p>\n\n<h2>Pursuing freedom</h2>\n<p>Würzburg tried to gain independency from its prince-bishops for a long time. It seemed that it achieved it when König Wenzel der Faule (king Wenzel the lazy) promised to the people of Würzburg, that they would be a free city of the Holy Roman Empire. The prince-bishop reversed that the day after it was promised, but non the less, the people of Würzburg tried to free themselves. It should take some time until they gain freedom from the prince-bishops. </p>',1,3),(4,'<h2>King family</h2>\n<p>The Salier were a royal lineage, that built 4 cathedrals in the Holy Roman Empire. One in Worms, one in Münster, one in Speyer and one in Würzburg. A relative of the Salier called Bruno was bishop of Würzburg when he ordered the creation of the cathedral in Würzburg. He thought, that he would never experience, that the cathedral gets finished, and he was right.</p>\n\n<h2>Holy Bruno</h2>\n<p>At a festivity, the ground beneath the feet of bishop Bruno broke away and he fell unfavourably. He died of a broken neck. At the place where the relics of bishop Bruno were stored, some people reported cases of wonder healing, so Bruno ywas declared holy. That\'s why he has been revered as a saint since 1200. </p>',1,4),(5,'<h2>Kilian, Kolonat and Totnan</h2>\n<p>Kilian the missionary bishop together with his helpers Kolonat and Totnan, tried to convert Würzburg to christianity. In the beginning he succeeded, the duke, who ruled Würzburg that time named Gospert, converted. After that Kilian demanded, that Gospert should get rid of his wife, Gailana, who has been the former wife of Gosperts dead brother. Gailana ordered the murder of Kilian and his friends, and she had them buried in the stables. Fifty years later, these bones were found by bishop Burkard and the whole story came out. </p>\n\n<h2>Many holy people</h2>>\n<p>After the bones of Kilian, Kolonat and Totnan were found, they were canonized. Not only because of their holy mission, but also because of their martyr deaths. And so Würzburg became the city with the most Saints in the north of the alps.</p>\n\n<h2>Relics to feel</h2>\n<p>Here in Neumünster lie the Relics of Kilian, Kolonat and Totnat. In the crypt stands the relic coffin, open to grab in. These old bones are said to be good for the eyes. You can find a wonder spring with healing water, too, which brought a lot of pilgrims to Würzburg in old times. </p>\n',0,5),(6,'<h2>Walter von der Vogelweide</h2>\n<p>Walter von der Vogelweide was the most important singer in his time. He wrote a lot of romantic songs, but critical and political songs, too. Due to his bad luck, he had to change his lord a lot of times, because he always stood on the wrong side of conflicts. When he was fifty years old, he got a piece of land close to Würzburg from emperor Friedrich II so he wasn´t forced to make music for his money anymore. Soon he started to write praises about emperor Friedrich II. 10 years later he died in Würzburg and was buried here, in the Lusamgärtchen, but this information is not assured.</p>',1,6),(7,'<h2>Former times</h2>\n<p>When there was no bridge to cross the river, traders and other people had to rely on a ford, which was impassable at high tide. At such times there was no easy way over the Main, which was disadvantageous for Würzburg as it was the most convenient place to cross the river in the surrounding area. The city lost income due to lower tariffs and cheaper goods when no one could cross the Main, so it was decided that Würzburg needed a bridge..</p>\n\n<h2>Bridge times</h2>\n<p>Master-builder Enzelin was the one who built the first bridge across the Main. He used the so-called \"Enzelin\"-bows to construct it and nobody thought that the bridge could be stable built with this new technique. But it was, and rumors arose that Enzelin had a contract with the devil. Then Enzelin got the offer to do some construction work on the cathedral from the bishop himself and those rumors vanished.</p>\n\n<h2> Tricky bridge</h2>\n<p>A small part of the bridge wasn\'t made of stone, but simply of wood. That seems unpractical, but in case of an attack, the wooden part of the bridge could be removed so it was impassable. Afterwards, the wooden part could be easily replaced.</p>',1,7),(8,'<h2>Scherenberg</h2>\n<p>In the late middle ages, an old man named Scherenberg became prince-bishop of Würzburg. Though not for long, it seemed: he was 65 years old, and many of the other candidates wished for his early demise, but they were disappointed. He lived to be 95 years old. In his 30 years of rule, Scherenberg had to deal with the financially difficult situation in Würzburg, so he bought back some leased properties, decreased the authority and rights of the city council for his own advantage.</p>\n\n<h2>Bibra</h2>\n<p>Prince-bishop Scherenberg was followed by Lorenz of Bibra, who was a beloved by the people of Würzburg, even though he decreased the rights of the city council even more than Scherenberg did. Under Bibra\'s reign, Würzburg reached its financial zenith and the Marienberg fortress was rebuilt in Renaissance style. Bibra was also the first bishop who had his tomb made before his death, and shortly before his death he apologized to the city council for acting against them.</p>\n\n<h2>Echter</h2>\n<p>Julius Echter of Mespelbrunn was elected bishop when he was 28 years old. He had the fortress rebuilt and enlarged with a bailey named after him, the \"Echter Hof\". In the following years he built the University of Würzburg, founded a hospital for Christians named after him, the \"Juliusspital\", and had the court library renovated. Echter was a great supporter of the counter-Reformation movement and had the non-Catholic citizens thrown out of Würzburg. Under his reign, the witch trials were resumed and the Jews of Würzburg were expelled and their property was confiscated.</p>\n\n<h2>Bishops will die!</h2>\n<p>After a bishop died, his body would be taken to the fortress and an autopsy would be performed to determine the cause of death. His innards would be removed and placed in a tin vessel, which was then buried in the Marienkapelle in the fortress. The heart would be brought to the Cistercian monestary in Ebrach, so that the monastery would not be lost to Bamberg. It was a great honour to safekeep the heart of the bishop. The bishop\'s body was then washed with wine and filled with fragrant herbs. A wooden stake would be put into the back as the body needed to sit upright for the two day ceremony during which it would be brought from the fortress to the cathedral, where it would be buried. Lastly, a master sculptor would be commissioned to make the grave monument. </p>',0,8),(9,'<h2>Renaissance fortress</h2>\n<p>Lorenz of Bibra initiated the Renaissance-style reconstruction of the fortress. Soon after, under the bishop Wirsberg, the south side of the fortress burnt down due to a night candle that he forgot to put out. Julius Echter had the fortress repaired and enhanced, but made a mistake. He didn\'t enlarge the walls enough, so the defenders couldn\'t see the gate. The mechanism of the drawbridge wasn\'t that good, either: In the thirty years war, the fortress was under attack by the swedish, and the defenders weren´t able to pull up the bridge, due to the dead bodies on it.</p>\n',1,9),(10,'<h2>Jews</h2>\n<p>Before the chapel was built, a Jewish synagogue stood at its place, but when the plague raged through Würzburg, the Jews were driven out and their synagogue was burned down. After the massacre of the Jews, a church was built at the place of the burnt synagogue as atonement, not for massacring the Jews, but for letting Jews in the city.</p>\n\n<h2>Chapel of the citizens</h2>\n<p>The Marienkapelle is a chapel built by citizens of Würzburg and was their hole pride, as it was built with their money. Around the chapel shops were opened to maintain it. Over the main entrance the doomsday is depicted, as well as a bishop, who is sent to hell. To excuse this gibe, the citizens claimed, that the scene should show, that everyone is equal in death.</p>\n\n<h2>The smell of decay</h2>\n<p>The chapel got very rich over the time. That is due to the possibility, that even simple citizens could have themselves buried under the chapels floor. After sometime, this became a big problem, because there were not enough floor plates to cover all the bodies and the smell of decay spread through the whole chapel.</p>',1,10),(11,'<h2>Taxes</h2>\n<p>Since the early middle ages the taxes of Würzburg were brought here, to this wine tavern. That came in pretty handy, because a big part of the taxes were handed over in form of wine, what´s not surprising in a big wine region like this. In fact it was to much wine to store it all, and Würzburg was forced to sell wine and gained prosperity.</p>\n\n<h2>Peasants war</h2>\n<p>In times of the peasants war, the prince bishop left the city to avoid the wrath of his people. When a peasants army arrived at the gates the city council decided to open the gates for them and the leaders of the army gathered in this tavern. When they hold up meetings they let a morning star hang out of the window, which was the reason why the tavern later was called Stachel, as this is the franconian word for morning star.</p>\n',1,11),(12,'<h2>Just Echter things</h2>\n<p>When Julius Echter came back from a long travel, where he got to know new ways of healing, he founded this hospital with his personal money. After he had the Jews thrown out of the city he confiscated their property, which included a big graveyard. On this graveyard the Juliusspital was built and a garden for healing herbs was created here, too. Soon after wine bars were added.</p>',1,12),(13,'<h2>first try</h2>\n<p>The first try to found a university in Würzburg was in 1402. But only few years later some students of the university murdered the director of the university and it was closed again.</p>\n\n<h2>Echter try</h2>\n<p>When Julius Echter founded the university, he had built a own university building, where the students could live and learn. In former times the students had to go to the professors to listen their lectures and had to find a place to sleep and eat, so the Echter university was pretty comfortable place to study.</p>\n\n<h2>Neubaukirche</h2>\n<p>The Neubaukirche was built as a part of the university founded by Julius Echter. It possesses the highest tower in Würzburg, and this is the place, where Echters heart rests. In the middle ages the funeral ceremony of the prince bishops contained a ritual, in which the heart of the dead bishop is taken out and put into a vessel. And the vessel with Echters heart stands in the tower of the Neubaukirche, due to Echters wish: \"After my death my heart should be, where it alway has been!\"</p>',1,13),(14,'<h2>Use</h2>\n<p>Like the Juliusspital this hospital, the so-called Bürgerspital, was founded to take care of the old and sick, but it was funded by the citizens and not a privileged Person. Formerly the Bürgerspital was a Siechhaus, a housing were people are left to die, but after some time, the heavily sick people were sent away.</p>\n\n<h2>Teufel</h2>\n<p>Especially two families stood out in case of the funding of the Bürgerspital: The families Steren and Teufel (last one means devil). People made jokes about the name of the last family: \"Even the devil does good things in Würzburg.\"</p>\n\n<h2>Other facts</h2>\n<p>The city medicus was responsible for the supplies of the Bürgerspital, what came in pretty expensive. Everyone who wanted to stay in the Bürgerspital had to offer all of his possessions to it, but at least they got prescribed one glass of wine per day!</p>',0,14),(15,'<h2>walls</h2>\n<p>After the terrible defensive performance in the Thirty Years\' War, it seemed like the prince-bishops learned their lesson. The walls were rebuilt bigger and higher, and special wall constructions was to provide additional protection from artillery fire. Underground tunnels were dug under the fortresses for more effective defensive tactics. And, with the reconstruction of the walls, the defenders could finally see their own gates!</p>\n\n<h2>Prince bishop with fancy name</h2>\n<p>The prince-bishop Greiffenclau gave orders to build an armory in the fortress, which led to a new bailey, the Greiffenclauhof. 160 cannons and the necessary equipment for them were stored in this armory. The cellar was filled with wine and equipment for infantry and cavalry was stored on the upper floors, enough to equip 40.000 soldiers.</p>',1,15),(16,'<h2>Schönborn</h2>\n<p>There were 3 prince-bishops from the family Schönborn and when the Residence was built, one of them was prince-bishop of Würzburg.\nThey claimed that they were infested with the \"building-worm\", a fancy way to say that the family Schönborn had many buildings constructed.</p>\n\n<h2>Neumann</h2>\n<p>Balthasar Neumann was the architect who built the Residence, after which the family Schönborn had many buildings constructed by him.\nBut at the start, it seemed like Neumann would never finish the Residence. After his first client, Johann Philipp Franz von Schönborn,\nwho gave him the commission to built the Residence at first, died, the following prince-bishop stopped the construction. 5 years after\nhis death, the construction work was resumed.</p>\n\n<h2>Bossi</h2>\n<p>Antonio Giuseppe Bossi was responsible for the stucco work in the Residence. The stucco at this time was made with\ntoxic ingredients and after finishing the white hall, a room which is almost completely made of stucco, Bossi became\nmentally deranged and died soon after.</p>\n\n<h2>Finances</h2>\n<p>Johann Philipp Franz von Schönborn raised taxes to get enough money to build the Residence, but this was not his only way to fund the\nconstruction. Gallus Jacob, Würzburg\'s former director of finances, was forced by Schönborn to pay a lot of money, because he had stolen\ntax revenue belonging to the city. He did so to avoid a legal process.</p>\n\n<h2>Carpe diem</h2>\n<p>One of the two big principles of the Baroque period is \"Carpe diem!\", seize the day.</p>',1,16),(17,'<h2>Architect</h2>\n<p>Balthasar Neumann built this chapel.</p>\n\n<h2>Former use</h2>\n<p>Schönborn wanted to make this chapel into a shrine for his family, but just one child from the family Schönborn who died early is buried here.</p>\n\n<h2>Memento mori</h2>\n<p>The second principle of the Baroque time is \"Memento mori!\", which means \"remember that you have to die\".\nThat\'s why the chapel was built close enough to the Residence to see it, to remind the prince-bishops that they, too, will die.</p>\n\n',1,17),(18,'<h2>Neumann again</h2>\n<p>The Käpelle was the last project Neumann started, though he didn\'t finish it. He died while the construction work was underway.</p>\n\n<h2>See a light</h2>\n<p>Once, a fisherman found a statue of Pieta, the Roman version of Mary, mother of Jesus, in the Main.\nHe brought it to the Nikolausberg and the winegrowers started praying to this Pieta.\nSoon pilgrims started visiting it and the citizens built a wooden chapel after light phenomena were seen close to this area.\nThe prince-bishop tried to avoid the construction of a sanctuary for pilgrims, but he had to bow to the citizens and under Balthasar Neumann,\na real chapel made of stone was built.</p>',0,18),(19,'<h2>Water</h2>\n<p>When Balthasar Neumann rebuilt Würzburg, he found a new source of water, which he used for his construction work on the Residence.\nLater he rerouted the water here and built the \"Vierröhrenbrunnen\". </p>\n\n<h2>Statues</h2>\n<p>The four statues at the bottom of the well depict the four secular virtues: Fortitudo - Strength and courage; Prudentia - Wisdom;\nJustitia - Justice; Temperantia - Moderation. On the top of the well we can see Frankonia, patron of the Franks, holding a sword and a banner.</p>',1,19),(20,'<h2>Greising</h2>\n<p>Before Balthasar Neumann, Joseph Greising was the main architect in Würzburg. He gave the Neumünster a new facade.\nUnder prince-bishop Greiffenclau Greising built most of the commissioned buildings, but when Schönborn reigned, he was nearly forgotten.</p>',0,20),(21,'<h2>Petrini</h2>\n<p>With the arrival of Antonio Petrini and his work on the walls of Würzburg, a new style of construction was created:\nPetrini mixed German Renaissance style with Italian Baroque and the Franconian Baroque style was born. He built the Stift Haug,\na church with two 60 meter tall domes, in this new style, inspired by the Dome in Rome.</p>\n\n<h2>Cannonballs</h2>\n<p>The people of Würzburg didn\'t trust the stability of the construction and wanted to test it. They fired a cannon inside the\nchurch and if the domes broke down, the architect would be executed. Petrini waited for an escape signal outside Würzburg,\nbut the domes stayed in place, and Petrini returned to the city.</p>',1,21),(22,'<h2>Italians</h2>\n<p>Italian architects who were specialized in fortress building were ordered to Würzburg to create walls to make Würzburg\nimpregnable. The whole city should get surrounded by high walls and in front of the walls, a so-called glacis was made.\nA glacis is empty, which makes attackers unable to hide or protect themselves using the environment.</p>\n\n<h2>Funny fact</h2>\n<p>High expenses were made to built these walls and the city of Würzburg was reconstructed heavily, but under the reign of\nthe prince-bishops Würzburg was never again attacked and its walls were left useless.</p>',0,22),(23,'<h2>Naming</h2>\n<p>Formerly there were no street names, so the people had to choose different orientation points like the falcon on the Falkenhaus, which it is named after. That is how the Falkenhaus became a landmark for the people of Würzburg.</p>\n\n<h2>Baroque</h2>\n<p>Under Balthasar Neumann the whole city was reconstructed in the Baroque style and everyone who built a stone facade on their house could get tax subsidies. The owner of the Falkenhaus overdid it and created a very pompous Rococo style facade to get extra tax relief, but it was not granted.</p>\n\n<h2>Neumann</h2>\n<p>The pompous and overloaded style of the house displeased many citizens, one being Balthasar Neumann. He wrote in a letter to his son, \"Such a building does not deserve a place in my Würzburg!\".</p>',0,23),(24,'<h2>Burn baby burn!</h2>\n<p>Under Petrini, a new pharmacy was added to the Juliusspital, but it burned down. After that it was rebuilt in Baroque style.</p>\n\n<h2>garden</h2>\n<p>Prince-bishop Guttenberg had built a botanical garden and Greising later added a pavilion.</p>\n\n<h2>Well</h2>\n<p>The Baroque well uses a special Baroque technique. Four water streams flow into the well as an allegory of the four Franconian rivers: the Main, Tauber, Sinn and Saale.</p>',1,24),(25,'<h2>Bridge</h2>\n<p>Two prince-bishops were responsible for the statues on the bridge. One of them, Friedrich Karl von Schönborn, placed statues of some commonly known saints on the northern side, while the other, Christoph Franz von Hutten, decorated the southern side with saints from Würzburg. </p>',1,25),(26,'<h2>Architect</h2>\n<p>The son of Balthasar Neumann, Franz Ignaz Neumann takes over for his father and builds a great structure in Würzburg: the Kranen. The prince-bishop Seinsheim wanted to increase trade on the Main so he gave orders to built this giant tool for lifting up to 1.2 tons. Inside, a large wheel can move the two arms of the crane up and down. This was very hard labour, done by only the toughest workers.</p>',0,26),(27,'<h2>Architect</h2>\n<p>Balthasar Neumann built this house as his own perfect store. On the lower floors there was space for different shops\nand on the upper floors there were rooms for families and private housing.</p>',1,27),(28,'<h2>Baumann</h2>\n<p>Balthasar Neumann built this house on his own and was thrown out, because someone of his origin wasn\'t supposed to\nlive in such a pompous place. And so the most popular architect of the Baroque era in Würzburg had to leave his own house.</p>\n\n<h2>Biographie</h2>\n<p>Balthasar Neumann was a trained cannon and bell founder as well as a fireworks expert and went to Würzburg for work. He\nstarted studying architecture and geometry and became engineer captain while he worked in Würzburg. While his first buildings\nwere unimpressive residential houses, his second big construction project was the Residence. After some finishing the Residence\nunder difficult conditions, he became chief-architect of Würzburg. Then a time of traveling and teaching began for Neumann.\nHe became an architecture teacher and tried to deepen his knowledge by traveling and learning. Additionally the royal and mighty\nfamily Schönborn sent him all around the German empire to construct other buildings for them. Neumann died before his last project,\nthe Käppelle, was finished and was buried in the Marienkapelle.</p>\n\n',1,28);
/*!40000 ALTER TABLE `Information` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-12 19:32:58
