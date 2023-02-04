-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: abschlussprojekt
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `EMPLOYEE_ID` int NOT NULL AUTO_INCREMENT,
  `JOB_TITLE` varchar(255) DEFAULT NULL,
  `SALARY` double DEFAULT NULL,
  `USER_NAME` varchar(255) NOT NULL,
  `USER_PASSWORD` varchar(255) NOT NULL,
  `ID_NUMBER` bigint DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_ID`),
  KEY `ID_NUMBER` (`ID_NUMBER`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`ID_NUMBER`) REFERENCES `person` (`ID_NUMBER`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Database Admin',5000,'admin','$2a$10$IWOx/AU6f//xEQvwbfWlH.xzTcHB.rJY69mSTl7e5xLRJanBBsuvC',1),(2,'Software Developer',3000,'bedn025','$2a$10$kxdEx77cSHi5RsO9TqBSbO6WJvaL8/aUCuNp7KW..FUwHEUosvJUa',211197),(3,'Software Developer',10000,'easa007','$2a$10$JUbpd7pRkxTdv3jccqfMheVC5EZrz0FNLOfpBJ.zcoqoc3t4LCxIi',987654321);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guest`
--

DROP TABLE IF EXISTS `guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guest` (
  `GUEST_ID` int NOT NULL,
  `ID_NUMBER` bigint DEFAULT NULL,
  PRIMARY KEY (`GUEST_ID`),
  KEY `ID_NUMBER` (`ID_NUMBER`),
  CONSTRAINT `guest_ibfk_1` FOREIGN KEY (`ID_NUMBER`) REFERENCES `person` (`ID_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest`
--

LOCK TABLES `guest` WRITE;
/*!40000 ALTER TABLE `guest` DISABLE KEYS */;
INSERT INTO `guest` VALUES (727042872,11111111),(393233618,65688498),(871352482,115698452),(424060407,489484156),(462300301,659874123),(680010050,10119538726),(153122342,10435646169),(303818394,10645590897),(273054457,10889658283),(286263575,12595736786),(173143915,12943371978),(680349889,14116862783),(925585069,15508916186),(354490608,16068625075),(608030730,16904227642),(494597795,17083763105),(981594056,17257837499),(933197871,17658680375),(194595487,25820318447),(528435372,30745164899),(433486590,30818030747),(736811085,31237314463),(339742803,31860167819),(359580771,32176001269),(362723055,32598717834),(135558638,36090699543),(586723951,40505966024),(813513373,40588855855),(873398485,46493675093),(622480349,48335379509),(381784153,51923688221),(639441495,53782199980),(517864706,55418872276),(465613173,56029086661),(104265987,57331797201),(629474872,61123495208),(652723709,62463567614),(613710598,65496189918),(737373972,69936770367),(354634792,70464598179),(885499647,71653172162),(643290033,72588273636),(736219771,77288824897),(701487733,77900471223),(921628319,77913508615),(786896193,78226252092),(195331809,78732687191),(219552947,81256135297),(203279571,82710719766),(244585095,83635219653),(596110665,83999103573),(749436073,84098105905),(485064256,86649879127),(957218346,96305917222),(355577294,98306839235);
/*!40000 ALTER TABLE `guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `ID_NUMBER` bigint NOT NULL,
  `FIRST_NAME` varchar(255) DEFAULT NULL,
  `LAST_NAME` varchar(255) DEFAULT NULL,
  `BIRTH_DATE` datetime DEFAULT NULL,
  `PHONE_NUMBER` varchar(255) DEFAULT NULL,
  `MAIL_ADDRESS` varchar(255) DEFAULT NULL,
  `STREET_NAME` varchar(255) DEFAULT NULL,
  `STREET_NUMBER` int DEFAULT NULL,
  `APARTMENT_NUMBER` int DEFAULT NULL,
  `ZIP_CODE` varchar(255) DEFAULT NULL,
  `CITY` varchar(255) DEFAULT NULL,
  `COUNTRY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Admin','Admin','2022-10-14 19:29:20',NULL,'admin@it-support.at',NULL,NULL,NULL,NULL,'Vienna','Austria'),(211197,'Agnes','Bednar','1997-11-21 00:00:00','+43 123123123','BednarA@wifi-it.at','Währinger Gürtel',97,NULL,'1180','Vienna','Austria'),(11111111,'Thomas','Leiter','1989-10-05 00:00:00','0664 8965580','thomas.leiter@gmx.at','Bergstraße',15,9,'3500','Krems a. d. Donau','Österreich'),(65688498,'Lisa','Hofer','1984-09-19 00:00:00','0699 4600998','lisa.hofer@gmail.com','Landgutstraße',12,20,'1090','Wien','Österreich'),(115698452,'Anna','Berger','1993-07-08 00:00:00','0664 2508890','anna.berger@outlook.com','Hainstraße',88,2,'3500','Krems a. d. Donau','Österreich'),(489484156,'Thomas','Leiter Der Zweite','2022-10-07 00:00:00','+43660846568','thomas.leiterderzweite@gmail.com','Leiterstraße',22,21,'1040','Wien','Österreich'),(659874123,'Sabrina','Aigner','1987-12-25 00:00:00','0660 8900457','sabrina.hofer@yahoo.com','Hofstraße',12,46,'1040','Wien','Wien'),(987654321,'Emad','Easa',NULL,NULL,'EmadE@wifi-it.at','Währinger Gürtel',97,NULL,'1180','Vienna','Austria'),(10119538726,'Elinore','Morissette','1968-05-12 08:45:47','(347) 659-7247','elinore-morissette@gmail.com','Hodkiewicz Knoll',479,25,'76344-2339','North Herminiashire','Dominica'),(10435646169,'Carter','Feil','1996-01-02 08:57:35','411-486-5277','carter-feil@gmx.at','Oswaldo Circle',109,49,'54008-4575','Lake Irvingville','Morocco'),(10645590897,'Marti','Turner','1990-05-26 10:41:49','1-880-224-8733 x72691','marti.turner@yahoo.com','Okuneva Ford',201,36,'04950','West Mikaelahaven','Dominica'),(10889658283,'Joanna','Graham','2003-10-01 11:23:58','(349) 173-9974 x763','joanna_graham@outlook.com','Demarcus Mission',233,42,'55756','Lake Ollie','Liberia'),(12595736786,'Jake','Berge','1959-09-30 05:17:36','(965) 305-9205','jake_berge@hotmail.com','Tromp Ramp',456,5,'36479-2026','South Tyishashire','Malawi'),(12943371978,'Bruce','Brakus','1964-06-24 20:48:18','(102) 231-7744 x471','bruce_brakus@yahoo.com','Giovanni Neck',108,13,'77584-9618','Lake Zoraport','French Southern Territories'),(14116862783,'Marsha','Cole','1994-04-04 13:18:57','1-660-505-0093','marsha.cole@gmx.at','Mohr Shores',256,2,'76088','Port Lainechester','Bolivia'),(15508916186,'Eric','Schuppe','1976-07-07 22:56:19','598.374.3148','eric.schuppe@yahoo.com','Bode Lakes',90,18,'92457','Schimmelchester','Qatar'),(16068625075,'Jacob','Gottlieb','1969-07-25 01:45:56','826-446-3728','jacob_gottlieb@gmail.com','Sheryl Harbors',60,15,'87756','North Lashauntown','Mayotte'),(16904227642,'Manual','Hessel','1992-05-30 11:35:22','1-941-577-1075','manual-hessel@hotmail.com','Stokes Common',3,33,'98497-1221','South Shawannastad','Timor-Leste'),(17083763105,'Genevive','Lockman','1998-06-25 15:14:14','624-627-3142 x5079','genevive_lockman@hotmail.com','Angeline Prairie',132,12,'07781','Lake Tajuana','Honduras'),(17257837499,'Luanne','Schaden','1970-07-06 07:30:39','740-523-5892','luanne-schaden@hotmail.com','Goldner Plain',152,38,'32344','North Dorianborough','Burkina Faso'),(17658680375,'Lavera','Rempel','1970-01-29 10:14:44','532-336-8099','lavera-rempel@gmx.at','Trantow Heights',74,49,'04217-4058','North Peg','Reunion'),(25820318447,'Rocco','Cormier','1987-03-08 11:28:22','1-481-415-9341','rocco-cormier@yahoo.com','Minh Extension',490,41,'43474','East Alexandria','Congo'),(30745164899,'Donte','Borer','1996-12-28 10:51:55','288-326-4592 x6776','donte_borer@outlook.com','Bobbie Views',132,4,'98147-5710','Lake Hankfurt','Qatar'),(30818030747,'Kimberli','Marks','1963-05-27 22:19:42','859.093.7651 x3809','kimberli-marks@outlook.com','Ernser Heights',205,2,'62813-2325','South Basilton','Zimbabwe'),(31237314463,'Alysa','Gibson','1970-09-30 12:21:56','385.571.8013','alysa-gibson@yahoo.com','Leroy Ports',209,23,'65483','West Erinchester','Germany'),(31860167819,'Travis','Bosco','1984-12-26 15:12:44','(345) 461-6443','travis-bosco@yahoo.com','Moore Spring',6,47,'25972-7367','Port Wallychester','Bahrain'),(32176001269,'Moses','Mayer','1997-06-26 20:58:32','1-262-712-3248','moses_mayer@gmx.at','Teddy Lights',182,4,'49140-7772','Emelyborough','Slovakia (Slovak Republic)'),(32598717834,'Cyndi','McKenzie','1970-12-01 12:57:33','(973) 040-4079 x81141','cyndi-mckenzie@outlook.com','Boyle Rest',16,22,'77507-6350','Miquelhaven','Turkey'),(36090699543,'Mitch','Rosenbaum','1985-02-01 08:25:48','356.045.5531 x973','mitch_rosenbaum@gmx.at','Peter Lane',407,11,'43119','Vicentetown','Myanmar'),(40505966024,'Trenton','Monahan','1980-04-13 19:51:38','217.300.0839 x9597','trenton_monahan@yahoo.com','Hegmann Square',231,20,'58025','Lake Michaeltown','Palau'),(40588855855,'Jaime','Parker','1971-10-29 11:16:32','669.753.4628','jaime.parker@outlook.com','Nobuko Rapids',491,46,'33816-2410','Port Venus','Tonga'),(46493675093,'Tasha','Brakus','1977-11-18 16:14:12','343.423.9086 x7045','tasha.brakus@gmail.com','Kutch Mission',80,32,'60583-7450','East Kennethmouth','Christmas Island'),(48335379509,'Star','Terry','1969-06-19 17:12:44','154.086.6551 x82215','star-terry@hotmail.com','Kory Branch',237,9,'22287','Baumbachmouth','Indonesia'),(51923688221,'Edelmira','Metz','1996-09-17 01:20:23','1-118-539-7424 x6516','edelmira-metz@yahoo.com','Bernice Tunnel',443,21,'78100','Shalondaton','Iceland'),(53782199980,'Korey','Mertz','1967-03-25 15:45:39','538.943.6122 x540','korey-mertz@hotmail.com','Pagac Court',478,27,'39471-7569','Treutelport','Iceland'),(55418872276,'Sherwood','Willms','1996-12-13 16:27:10','872-753-5881 x3737','sherwood_willms@gmail.com','Clint Ramp',383,10,'19894','Alysiaborough','Portugal'),(56029086661,'Junie','Bartell','1960-09-24 00:10:19','(944) 734-6525','junie_bartell@gmx.at','Nitzsche Center',60,37,'76828-8779','Lake Norine','Gibraltar'),(57331797201,'Jacalyn','Cassin','1988-11-22 00:06:02','1-017-363-0278 x437','jacalyn.cassin@yahoo.com','Homenick Motorway',271,14,'65875','North Phillipstad','Hong Kong'),(61123495208,'Ettie','Balistreri','1983-02-02 12:33:22','050.568.1956','ettie-balistreri@gmx.at','Jenee Pines',283,32,'16307','West Alpha','San Marino'),(62463567614,'Elizbeth','Moore','2003-02-18 18:13:26','185.676.9267 x18029','elizbeth_moore@gmx.at','Bartell View',322,49,'82932','East Olindaton','Russian Federation'),(65496189918,'Angel','Skiles','1994-10-21 21:44:33','512.389.8617 x539','angel-skiles@gmx.at','Botsford Shoal',210,43,'34017','Michaelhaven','Grenada'),(69936770367,'Edmundo','Wiegand','2000-06-03 09:58:22','(531) 810-4728 x58178','edmundo.wiegand@gmx.at','Jerald Islands',425,33,'24606','Lake Pat','Czech Republic'),(70464598179,'Tamala','Schmitt','1995-06-19 09:57:41','1-297-200-4060 x4779','tamala-schmitt@outlook.com','Stefan Summit',109,15,'33389','Deckowchester','Mauritius'),(71653172162,'Jackqueline','Thiel','1982-10-23 21:24:34','930-284-0269 x98112','jackqueline.thiel@outlook.com','Muller Springs',380,49,'77878','Mariannstad','Guinea-Bissau'),(72588273636,'Howard','Glover','1990-11-06 18:15:03','(026) 482-0431 x2867','howard-glover@gmx.at','Marcella Garden',334,35,'69353-6828','Clemmieburgh','Guinea'),(77288824897,'Teodoro','Cormier','1988-03-14 11:55:58','(189) 759-0414','teodoro.cormier@gmx.at','Millicent Lodge',137,41,'22194','Beattyshire','Sudan'),(77900471223,'Darell','Wilderman','1961-10-03 14:09:49','(849) 973-3338','darell.wilderman@hotmail.com','Bobette Street',357,16,'00035-2774','O\'Connellstad','Belgium'),(77913508615,'Reyes','Willms','1996-01-17 16:11:45','(655) 807-5720','reyes-willms@yahoo.com','Walker Ranch',323,8,'18479','South Rudolfport','Saint Pierre and Miquelon'),(78226252092,'Joe','Mills','1979-08-02 13:47:31','(866) 239-7317 x1150','joe-mills@gmx.at','Schuppe Parkways',471,4,'83332-1967','Port Velvetbury','Zimbabwe'),(78732687191,'Buford','Pfannerstill','1991-03-26 09:46:33','534.614.9166 x28723','buford.pfannerstill@hotmail.com','McLaughlin Rue',451,48,'73066-6451','West Tanna','Mozambique'),(81256135297,'Micheline','Howe','1971-12-30 21:26:46','1-772-178-5232','micheline.howe@outlook.com','Mueller Key',364,3,'68957','East Maybell','India'),(82710719766,'Lara','McCullough','1996-11-29 02:16:16','(060) 811-0159','lara-mccullough@yahoo.com','Emile Pass',47,11,'08226','Whitneymouth','Bermuda'),(83635219653,'Larisa','Cummings','1967-12-09 12:26:14','(323) 870-7752 x09162','larisa.cummings@hotmail.com','Fadel Plains',275,12,'63656','West Berryfort','Venezuela'),(83999103573,'Gerard','Brakus','1965-07-08 11:03:35','743-490-1028 x153','gerard_brakus@gmail.com','Johnson Shores',337,26,'88680-6464','Koeppton','Netherlands'),(84098105905,'Ling','Rempel','1958-05-06 14:45:15','(659) 714-6331','ling_rempel@hotmail.com','Lien Plaza',4,24,'00432-0685','Stephanbury','Bahamas'),(86649879127,'Geoffrey','McGlynn','1993-10-09 23:49:45','654-683-0050','geoffrey.mcglynn@hotmail.com','Parisian Ranch',80,7,'44286','Schimmelchester','Northern Mariana Islands'),(96305917222,'Margo','Braun','1968-01-03 00:27:02','392.910.6677 x66320','margo.braun@gmx.at','Keebler Summit',135,16,'29317','Irisfort','Dominica'),(98306839235,'Quintin','D\'Amore','1974-09-25 12:25:06','(946) 865-7944 x36297','quintin-d\'amore@gmx.at','Hessel Mall',5,4,'92997-7020','New Tianachester','South Georgia and the South Sandwich Islands');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `CONFIRMATION_NUMBER` varchar(255) NOT NULL,
  `ARRIVAL_DATE` datetime NOT NULL,
  `DEPARTURE_DATE` datetime NOT NULL,
  `ROOM_NUMBER` int DEFAULT NULL,
  `RESERVATION_PRICE` double NOT NULL,
  `TOTAL_BALANCE` double DEFAULT NULL,
  `PAYMENT_TYPE` varchar(255) NOT NULL,
  `RESERVATION_STATUS` varchar(255) DEFAULT NULL,
  `GUEST_ID` int DEFAULT NULL,
  `ROOM_ID` int DEFAULT NULL,
  PRIMARY KEY (`CONFIRMATION_NUMBER`),
  KEY `GUEST_ID` (`GUEST_ID`),
  KEY `ROOM_ID` (`ROOM_ID`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`GUEST_ID`) REFERENCES `guest` (`GUEST_ID`),
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`ROOM_ID`) REFERENCES `room` (`ROOM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES ('05YRPDFSYRS0HHH','2022-11-12 16:25:52','2022-11-15 17:30:41',544,500,500,'DEBITCARD','UNKNOWN',622480349,2),('2LI4VVM3ITFDBS3','2022-10-30 17:13:14','2022-11-04 23:18:29',101,500,500,'PREPAYMENT','CHECKED_OUT',381784153,91),('3FZTQ72BI13LKWA','2022-10-25 00:00:00','2022-10-28 00:00:00',880,380,380,'DEBITCARD','CHECKED_OUT',355577294,60),('3LFQ0GE19QZDO3Z','2022-10-29 11:17:18','2022-11-04 22:37:25',439,500,500,'DEBITCARD','CHECKED_OUT',355577294,93),('40H3T01VYYROH3F','2022-11-04 03:58:00','2022-11-09 02:29:28',760,500,500,'CREDITCARD','CHECKED_OUT',933197871,1),('4PG0G1W94JBCIKL','2022-11-05 07:57:08','2022-11-14 05:43:08',876,500,500,'CREDITCARD','UNKNOWN',680349889,15),('7806RHRYRBOWXKQ','2022-10-29 00:00:00','2022-10-30 00:00:00',434,250,250,'CREDITCARD','CHECKED_OUT',104265987,87),('7M0BAO5FPBYARPV','2022-10-25 23:06:21','2022-10-31 14:11:19',655,500,500,'DEBITCARD','CHECKED_OUT',786896193,48),('812RIEW41RWANIM','2022-11-09 22:29:39','2022-11-13 03:57:21',103,500,500,'DEBITCARD','UNKNOWN',680010050,65),('89R6PZ02JVOCSBU','2022-11-01 07:29:53','2022-11-04 01:37:06',650,500,510,'CREDITCARD','CHECKED_OUT',586723951,34),('9T1JDPMCJB3VJQ3','2022-10-20 07:43:38','2022-10-23 14:55:06',107,500,500,'DEBITCARD','CHECKED_OUT',873398485,96),('B37PQZ0K2C7OZQN','2022-10-21 17:03:56','2022-10-30 04:40:27',984,500,500,'CREDITCARD','CHECKED_OUT',517864706,7),('C03JF2LIHABG3I3','2022-11-02 00:00:00','2022-11-09 00:00:00',433,510,500,'CREDITCARD','CHECKED_OUT',286263575,84),('C7O1S65B1RDLSQO','2022-11-08 14:27:26','2022-11-14 21:10:42',988,500,500,'DEBITCARD','UNKNOWN',303818394,62),('CDJ0SYKLKEILSIG','2022-11-12 00:00:00','2022-11-22 00:00:00',324,380,0,'CREDITCARD','CHECKED_OUT',957218346,71),('CY4P1X83A78XE2Z','2022-11-13 03:11:30','2022-11-17 08:41:47',543,500,500,'DEBITCARD','UNKNOWN',219552947,49),('D24X8KV660M7RSC','2022-10-28 08:31:52','2022-11-06 20:34:57',654,500,500,'DEBITCARD','CHECKED_OUT',652723709,76),('D4L14J20RB80EC1','2022-11-01 14:43:47','2022-11-01 06:27:26',328,500,0,'DEBITCARD','CHECKED_OUT',737373972,19),('DWC5RUJU4VAINJY','2022-11-07 05:42:07','2022-11-16 17:44:18',218,500,500,'PREPAYMENT','UNKNOWN',925585069,23),('DXJHADU9GNX00QK','2022-10-24 00:00:00','2022-10-28 00:00:00',980,510,500,'CREDITCARD','CHECKED_OUT',629474872,90),('E26FU0YHK456W0E','2022-10-23 00:00:00','2022-10-25 00:00:00',216,510,510,'DEBITCARD','CHECKED_OUT',596110665,82),('EBPY2VOK8CL320E','2022-10-26 00:00:00','2022-11-03 00:00:00',108,380,380,'DEBITCARD','CHECKED_OUT',339742803,8),('EFHRGVH0B260AYF','2022-11-17 00:00:00','2022-11-19 00:00:00',983,510,510,'CREDITCARD','UNKNOWN',244585095,54),('EVE85XMNYBQT21V','2022-10-17 21:19:30','2022-10-20 01:02:09',982,500,500,'CREDITCARD','CHECKED_OUT',701487733,9),('EWTQZ2LVLXOO90B','2022-10-30 00:00:00','2022-10-29 00:00:00',320,380,500,'DEBITCARD','CHECKED_OUT',528435372,63),('FYDYBPBOY3OX7XF','2022-10-17 21:46:25','2022-10-20 07:54:31',540,500,500,'PREPAYMENT','CHECKED_OUT',173143915,100),('FZAXCVWK3VZOM5V','2022-11-14 05:06:59','2022-11-14 13:44:48',873,500,500,'PREPAYMENT','UNKNOWN',813513373,67),('HJ7SKF5HFZORYYC','2022-10-23 18:42:06','2022-10-30 09:21:01',430,500,500,'CREDITCARD','CHECKED_OUT',981594056,6),('IDL0J25DPBWTNYX','2022-10-28 00:00:00','2022-10-30 00:00:00',215,380,380,'DEBITCARD','CHECKED_OUT',354490608,73),('IH0LHWW3PAQARBN','2022-10-18 23:19:19','2022-10-26 12:36:50',871,500,500,'PREPAYMENT','CHECKED_OUT',485064256,98),('JPQ0M3QHIEDA7Q1','2022-10-24 19:53:13','2022-10-29 14:09:10',211,500,500,'CREDITCARD','CHECKED_OUT',921628319,70),('JQ4A0XI4YMRLH8K','2022-11-11 00:00:00','2022-11-18 00:00:00',108,380,510,'PREPAYMENT','CHECKED_IN',433486590,30),('K2URZD9W3FKGLVR','2022-10-27 05:51:32','2022-10-29 12:25:52',435,500,500,'PREPAYMENT','CHECKED_OUT',885499647,56),('KXJ630I8AA39K35','2022-10-25 22:01:33','2022-10-27 21:38:40',431,500,500,'CREDITCARD','CHECKED_OUT',362723055,24),('LYDN7P7U1JQBKTT','2022-10-21 02:42:50','2022-10-21 09:50:44',765,500,500,'DEBITCARD','CHECKED_OUT',203279571,27),('MB2LGUFI7QK9X00','2022-11-02 14:13:54','2022-11-09 15:50:22',763,500,500,'PREPAYMENT','CHECKED_OUT',613710598,74),('MILED1CNA6X1UI9','2022-10-28 09:38:22','2022-11-02 13:32:04',542,500,500,'DEBITCARD','CHECKED_OUT',643290033,80),('MLJCQA1LZBIW2A4','2022-10-22 23:50:33','2022-10-27 11:25:38',541,500,500,'PREPAYMENT','CHECKED_OUT',494597795,40),('MY5LOMK1W5FQAXC','2022-10-15 12:31:12','2022-10-19 03:02:54',102,500,500,'DEBITCARD','CHECKED_OUT',736219771,50),('N0EV3GICZLE81AE','2022-10-25 12:05:28','2022-10-30 08:32:53',999,500,500,'PREPAYMENT','CHECKED_OUT',273054457,42),('O4DGGU81KLBOQX8','2022-11-01 12:37:14','2022-11-05 23:42:34',326,500,0,'CREDITCARD','CHECKED_OUT',195331809,61),('OQSEM9GON4NTT0U','2022-10-28 00:00:00','2022-10-30 00:00:00',216,510,510,'CREDITCARD','CHECKED_OUT',194595487,88),('OTCNXKEIBG2H9SI','2022-10-24 15:58:37','2022-10-26 15:29:09',438,500,500,'PREPAYMENT','CHECKED_OUT',135558638,55),('OXH59PYDZNM3GUN','2022-11-07 20:06:34','2022-11-15 08:04:43',322,500,500,'PREPAYMENT','UNKNOWN',354490608,51),('P3H5QB0C38S46A4','2022-10-27 00:00:00','2022-11-02 00:00:00',216,510,510,'DEBITCARD','CHECKED_OUT',244585095,13),('PRPAXW73WFTECNZ','2022-10-21 00:05:24','2022-10-22 21:01:23',325,500,500,'PREPAYMENT','CHECKED_OUT',736811085,69),('PSZ09048KN80GIR','2022-10-24 00:00:00','2022-10-26 00:00:00',878,380,380,'PREPAYMENT','CHECKED_OUT',465613173,28),('Q4DVOPPGOOLGJM8','2022-10-15 23:54:24','2022-10-17 20:45:04',104,500,500,'PREPAYMENT','CHECKED_OUT',194595487,43),('Q9RH7XVIUK4OS8D','2022-11-01 00:00:00','2022-11-03 00:00:00',104,380,0,'CREDITCARD','CHECKED_OUT',465613173,99),('QH7WTWXHTJBA799','2022-11-15 00:00:00','2022-11-17 00:00:00',658,380,380,'CREDITCARD','UNKNOWN',680349889,39),('SOCD74Z9D0JFDMW','2022-10-23 11:40:15','2022-10-30 15:36:30',762,500,500,'PREPAYMENT','CHECKED_OUT',608030730,46),('T7MDMV10D1OSMLD','2022-11-07 00:07:48','2022-11-07 11:26:29',214,500,500,'CREDITCARD','CHECKED_OUT',749436073,35),('USD9V7K5WU8H0FZ','2022-10-26 03:29:41','2022-10-26 19:05:59',330,500,500,'CREDITCARD','CHECKED_OUT',153122342,59),('VBYCUQD70AB1S07','2022-10-19 09:59:02','2022-10-23 14:03:10',989,500,500,'CREDITCARD','CHECKED_OUT',359580771,36),('XMJV51UN59G1UIY','2022-11-01 23:46:11','2022-11-07 13:48:41',545,500,500,'DEBITCARD','CHECKED_OUT',639441495,78),('ZE5JZ9ZBT05KCBP','2022-10-29 00:00:00','2022-10-31 00:00:00',765,250,250,'DEBITCARD','CHECKED_OUT',354634792,31);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `ROOM_ID` int NOT NULL AUTO_INCREMENT,
  `ROOM_NUMBER` int NOT NULL,
  `ROOM_CATEGORY` varchar(255) DEFAULT NULL,
  `ROOM_STATUS` varchar(255) DEFAULT NULL,
  `ROOM_PRICE` double DEFAULT NULL,
  PRIMARY KEY (`ROOM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,760,'SUITE','CLEAN',510),(2,544,'SUITE','DIRTY',510),(3,321,'REGULAR','CLEAN',250),(4,548,'SUITE','CLEAN',510),(5,546,'REGULAR','CLEAN',250),(6,430,'REGULAR','CLEAN',250),(7,984,'REGULAR','CLEAN',250),(8,108,'DELUXE','CLEAN',380),(9,982,'SUITE','CLEAN',510),(10,875,'REGULAR','CLEAN',250),(11,220,'DELUXE','CLEAN',380),(12,660,'SUITE','CLEAN',510),(13,437,'REGULAR','CLEAN',250),(14,766,'REGULAR','CLEAN',250),(15,876,'SUITE','CLEAN',510),(16,870,'DELUXE','CLEAN',380),(17,770,'SUITE','CLEAN',510),(18,652,'REGULAR','CLEAN',250),(19,328,'SUITE','CLEAN',510),(20,877,'DELUXE','OUT_OF_ORDER',380),(21,432,'REGULAR','DIRTY',250),(22,874,'SUITE','CLEAN',510),(23,218,'DELUXE','OCCUPIED',380),(24,431,'SUITE','CLEAN',510),(25,651,'SUITE','CLEAN',510),(26,106,'SUITE','CLEAN',510),(27,765,'REGULAR','CLEAN',250),(28,878,'DELUXE','CLEAN',380),(29,323,'DELUXE','CLEAN',380),(30,990,'SUITE','OCCUPIED',510),(31,879,'SUITE','CLEAN',510),(32,768,'REGULAR','CLEAN',250),(33,440,'SUITE','CLEAN',510),(34,650,'SUITE','CLEAN',510),(35,214,'DELUXE','CLEAN',380),(36,989,'REGULAR','CLEAN',250),(37,761,'DELUXE','CLEAN',380),(38,213,'REGULAR','CLEAN',250),(39,658,'DELUXE','CLEAN',380),(40,541,'REGULAR','CLEAN',250),(41,329,'REGULAR','CLEAN',250),(42,999,'DELUXE','CLEAN',380),(43,104,'DELUXE','CLEAN',380),(44,872,'DELUXE','DIRTY',510),(45,550,'DELUXE','CLEAN',380),(46,762,'DELUXE','CLEAN',380),(47,217,'SUITE','CLEAN',510),(48,655,'SUITE','CLEAN',510),(49,543,'DELUXE','OCCUPIED',380),(50,102,'SUITE','CLEAN',510),(51,322,'SUITE','OCCUPIED',510),(52,436,'REGULAR','CLEAN',250),(53,986,'REGULAR','CLEAN',250),(54,983,'SUITE','CLEAN',510),(55,438,'DELUXE','CLEAN',380),(56,435,'DELUXE','CLEAN',380),(57,987,'REGULAR','OCCUPIED',510),(58,656,'DELUXE','CLEAN',380),(59,330,'REGULAR','CLEAN',250),(60,880,'DELUXE','CLEAN',380),(61,326,'DELUXE','CLEAN',380),(62,988,'DELUXE','OCCUPIED',380),(63,320,'DELUXE','CLEAN',380),(64,210,'SUITE','CLEAN',510),(65,103,'SUITE','OCCUPIED',510),(66,653,'REGULAR','CLEAN',250),(67,873,'DELUXE','DUEOUT',380),(68,219,'SUITE','CLEAN',510),(69,325,'REGULAR','CLEAN',250),(70,211,'REGULAR','CLEAN',250),(71,324,'DELUXE','DIRTY',380),(72,764,'REGULAR','CLEAN',250),(73,215,'DELUXE','CLEAN',380),(74,763,'REGULAR','CLEAN',250),(75,110,'REGULAR','CLEAN',250),(76,654,'SUITE','CLEAN',510),(77,549,'DELUXE','CLEAN',380),(78,545,'SUITE','CLEAN',510),(79,769,'REGULAR','CLEAN',250),(80,542,'DELUXE','CLEAN',380),(81,105,'REGULAR','CLEAN',250),(82,212,'SUITE','CLEAN',510),(83,657,'REGULAR','CLEAN',250),(84,433,'SUITE','CLEAN',250),(85,327,'SUITE','CLEAN',510),(86,547,'DELUXE','CLEAN',380),(87,434,'REGULAR','CLEAN',250),(88,216,'SUITE','CLEAN',510),(89,100,'REGULAR','CLEAN',250),(90,980,'SUITE','CLEAN',380),(91,101,'REGULAR','CLEAN',250),(92,985,'REGULAR','CLEAN',250),(93,439,'DELUXE','CLEAN',380),(94,767,'DELUXE','CLEAN',380),(95,109,'DELUXE','CLEAN',380),(96,107,'SUITE','CLEAN',510),(97,659,'SUITE','CLEAN',510),(98,871,'REGULAR','CLEAN',250),(99,981,'SUITE','CLEAN',510),(100,540,'REGULAR','CLEAN',250);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'abschlussprojekt'
--
/*!50106 SET @save_time_zone= @@TIME_ZONE */ ;
/*!50106 DROP EVENT IF EXISTS `clean_rooms_from_old_reservations` */;
DELIMITER ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8mb4 */ ;;
/*!50003 SET character_set_results = utf8mb4 */ ;;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = 'SYSTEM' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`root`@`localhost`*/ /*!50106 EVENT `clean_rooms_from_old_reservations` ON SCHEDULE EVERY 1 DAY STARTS '2022-10-26 18:20:00' ON COMPLETION NOT PRESERVE ENABLE DO UPDATE RESERVATION r INNER JOIN ROOM ro ON r.ROOM_ID = ro.ROOM_ID 
    SET r.RESERVATION_STATUS = 'CHECKED_OUT', ro.ROOM_STATUS = 'CLEAN'
    WHERE DEPARTURE_DATE < CURRENT_DATE */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
/*!50106 DROP EVENT IF EXISTS `set_reservation_status_to_due_out` */;;
DELIMITER ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8mb4 */ ;;
/*!50003 SET character_set_results = utf8mb4 */ ;;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = 'SYSTEM' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`root`@`localhost`*/ /*!50106 EVENT `set_reservation_status_to_due_out` ON SCHEDULE EVERY 1 DAY STARTS '2022-11-02 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO UPDATE RESERVATION r
    SET r.RESERVATION_STATUS = 'DUE_OUT'
    WHERE DATE(DEPARTURE_DATE) = DATE(CURRENT_DATE) */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
DELIMITER ;
/*!50106 SET TIME_ZONE= @save_time_zone */ ;

--
-- Dumping routines for database 'abschlussprojekt'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-11 18:51:03
