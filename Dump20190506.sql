-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: my_alert
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `agent`
--

DROP TABLE IF EXISTS `agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `agent` (
  `idAgent` int(11) NOT NULL AUTO_INCREMENT,
  `idUser_FK` int(11) NOT NULL,
  `Department` varchar(45) NOT NULL,
  `Department_Code` varchar(10) NOT NULL,
  `Lat` double DEFAULT NULL,
  `Lon` double DEFAULT NULL,
  `StartDate_task` datetime DEFAULT NULL,
  `EndDate_task` datetime DEFAULT NULL,
  `idManager_FK` int(11) NOT NULL,
  PRIMARY KEY (`idAgent`),
  KEY `fk_Agent_User1_idx` (`idUser_FK`),
  KEY `fk_Agent_Manager1_idx` (`idManager_FK`),
  CONSTRAINT `fk_Agent_Manager1` FOREIGN KEY (`idManager_FK`) REFERENCES `manager` (`idManager`),
  CONSTRAINT `fk_Agent_User1` FOREIGN KEY (`idUser_FK`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent`
--

LOCK TABLES `agent` WRITE;
/*!40000 ALTER TABLE `agent` DISABLE KEYS */;
INSERT INTO `agent` VALUES (10,21,'Incendi','INC_01',40.1819344,18.2129788,'2020-09-26 19:26:23',NULL,5),(22,108,'Incendi','INC_02',40.1825362,18.2060615,'2020-09-27 09:20:27',NULL,5),(23,113,'Furti','FUR_01',40.1825362,18.2060615,'2020-09-27 09:20:27',NULL,5);
/*!40000 ALTER TABLE `agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alarm`
--

DROP TABLE IF EXISTS `alarm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `alarm` (
  `idAlarm` int(11) NOT NULL AUTO_INCREMENT,
  `idCitizen` int(11) NOT NULL,
  `idIntervention` int(11) NOT NULL,
  `AlarmDate` timestamp(6) NULL DEFAULT NULL,
  `Title` varchar(200) DEFAULT NULL,
  `Description` mediumtext,
  PRIMARY KEY (`idAlarm`),
  KEY `fk_Citizen_has_Intervention_Intervention2_idx` (`idIntervention`),
  KEY `fk_Citizen_has_Intervention_Citizen2_idx` (`idCitizen`),
  CONSTRAINT `fk_Citizen_has_Intervention_Citizen2` FOREIGN KEY (`idCitizen`) REFERENCES `citizen` (`idCitizen`),
  CONSTRAINT `fk_Citizen_has_Intervention_Intervention2` FOREIGN KEY (`idIntervention`) REFERENCES `intervention` (`idIntervention`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm`
--

LOCK TABLES `alarm` WRITE;
/*!40000 ALTER TABLE `alarm` DISABLE KEYS */;
INSERT INTO `alarm` VALUES (64,6,50,'2020-09-28 05:45:04.000000','Incendio casa','Sta bruciando tutto '),(65,51,50,'2020-09-29 05:45:04.000000','Incendio abitazione','Fumo esce da un appartamento. Sembra non ci sia nessuno'),(66,55,50,'2020-09-28 08:45:04.000000','Incendio ','Segnalo incendio presso abitazione vicino di casa'),(67,55,51,'2020-09-20 06:45:04.000000','Incidente','Incidente incrocio. Coinvolte più persone'),(68,56,52,'2020-05-01 06:45:04.000000','Discarica abusiva foresta','Segnalo un accumulo di spazzatura nei pressi della foresta via Po'),(69,6,53,'2020-07-04 13:45:04.000000','Discarica di plastica','Si segnala grandi quantità di plastica non raccolta'),(70,55,53,'2020-07-04 13:45:04.000000','Accumulo plastica','Enorme quantità di plastica nei pressi di una campagna '),(71,56,56,'2020-04-04 18:45:04.000000','Incendio','Brucia cassonetto ');
/*!40000 ALTER TABLE `alarm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assign`
--

DROP TABLE IF EXISTS `assign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `assign` (
  `idAssign` int(11) NOT NULL AUTO_INCREMENT,
  `idManager` int(11) NOT NULL,
  `idAgent` int(11) NOT NULL,
  `Confirm` tinyint(4) NOT NULL DEFAULT '0',
  `HasWritten` tinyint(4) NOT NULL DEFAULT '0',
  `StartValidate` datetime DEFAULT NULL,
  `EndValidate` datetime DEFAULT NULL,
  `idIntervention` int(11) NOT NULL,
  PRIMARY KEY (`idAssign`),
  KEY `fk_Assign_Manager1_idx` (`idManager`),
  KEY `fk_Assign_Agent1_idx` (`idAgent`),
  KEY `fk_Assign_Intervention1_idx` (`idIntervention`),
  CONSTRAINT `fk_Assign_Agent1` FOREIGN KEY (`idAgent`) REFERENCES `agent` (`idAgent`),
  CONSTRAINT `fk_Assign_Intervention1` FOREIGN KEY (`idIntervention`) REFERENCES `intervention` (`idIntervention`),
  CONSTRAINT `fk_Assign_Manager1` FOREIGN KEY (`idManager`) REFERENCES `manager` (`idManager`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assign`
--

LOCK TABLES `assign` WRITE;
/*!40000 ALTER TABLE `assign` DISABLE KEYS */;
INSERT INTO `assign` VALUES (60,5,10,1,1,'2020-09-28 08:45:04',NULL,50),(61,5,22,1,0,'2020-09-28 08:45:04',NULL,50),(62,5,23,0,0,'2020-09-28 08:45:04',NULL,50),(63,5,10,1,1,'2020-09-22 08:45:04',NULL,51),(64,5,10,1,0,'2020-07-04 16:45:04',NULL,53),(65,5,22,0,0,'2020-07-04 16:45:04',NULL,53),(67,5,23,1,0,'2020-04-04 16:45:04',NULL,56);
/*!40000 ALTER TABLE `assign` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `citizen`
--

DROP TABLE IF EXISTS `citizen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `citizen` (
  `idCitizen` int(11) NOT NULL AUTO_INCREMENT,
  `idUser_FK` int(11) NOT NULL,
  `Lat` double DEFAULT NULL,
  `Lon` double DEFAULT NULL,
  PRIMARY KEY (`idCitizen`),
  KEY `fk_Citizen_User1_idx` (`idUser_FK`),
  CONSTRAINT `fk_Citizen_User1` FOREIGN KEY (`idUser_FK`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `citizen`
--

LOCK TABLES `citizen` WRITE;
/*!40000 ALTER TABLE `citizen` DISABLE KEYS */;
INSERT INTO `citizen` VALUES (6,20,37.4220297,-122.0839813),(51,109,37.4219947,-122.0840117),(55,110,37.4219947,-122.0840117),(56,111,37.4219947,-122.0840117),(57,112,37.4219947,-122.0840117);
/*!40000 ALTER TABLE `citizen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `image` (
  `idImage` int(11) NOT NULL AUTO_INCREMENT,
  `Url` mediumtext NOT NULL,
  `Lat` double DEFAULT NULL,
  `Lon` double DEFAULT NULL,
  `idUser` int(11) NOT NULL,
  `idIntervention` int(11) NOT NULL,
  PRIMARY KEY (`idImage`),
  KEY `fk_Image_User1_idx` (`idUser`),
  KEY `fk_Image_Intervention1_idx` (`idIntervention`),
  CONSTRAINT `fk_Image_Intervention1` FOREIGN KEY (`idIntervention`) REFERENCES `intervention` (`idIntervention`),
  CONSTRAINT `fk_Image_User1` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (36,'https://firebasestorage.googleapis.com/v0/b/myalert-372f0.appspot.com/o/alarmIMGS%2F-stabile-appartamento-incendio-giovani-muralto-le99.jpg?alt=media&token=4085e832-3a85-4ff6-bcbe-408e2b91bc44',37.4219947,-122.0840117,109,50),(37,'https://firebasestorage.googleapis.com/v0/b/myalert-372f0.appspot.com/o/alarmIMGS%2F7F76C085-5FCA-482C-9738-3A4A1A0BDB16.jpeg?alt=media&token=10504e2d-0045-4773-8ffe-1ddf7a3a8eac',37.4219947,-122.0840117,20,50),(38,'https://firebasestorage.googleapis.com/v0/b/myalert-372f0.appspot.com/o/alarmIMGS%2FDiscarica-Noto.jpg?alt=media&token=cf36af9f-8c51-4215-aa5a-5ef327870b5c',37.4219947,-122.0840117,110,53),(39,'https://firebasestorage.googleapis.com/v0/b/myalert-372f0.appspot.com/o/alarmIMGS%2FWhatsApp_Image_2020-01-30_at_16_42_42_Thumb_HighlightCenter202245.jpg?alt=media&token=48565171-652a-4512-becd-b7df18676d19',37.4219947,-122.0840117,110,51),(40,'https://firebasestorage.googleapis.com/v0/b/myalert-372f0.appspot.com/o/alarmIMGS%2Fbesana-in-brianza-brucia-il-tetto-di-una-casa-a-villa-raverio_4f4530a4-5e51-11ea-ae27-b1cb4c841514_900_566.jpeg?alt=media&token=a1f93636-f7bc-4801-8976-e0201201e57d',37.4219947,-122.0840117,110,50),(41,'https://firebasestorage.googleapis.com/v0/b/myalert-372f0.appspot.com/o/alarmIMGS%2Fdiscarica-a-cxielo-aperto-castel-san-giovanni.jpg?alt=media&token=4d25ec56-7671-4e12-9f6b-cc9a841f8627',37.4219947,-122.0840117,111,52),(42,'https://firebasestorage.googleapis.com/v0/b/myalert-372f0.appspot.com/o/alarmIMGS%2Fimage.jpg?alt=media&token=7c96b3a8-a3c3-4497-9ad2-195a9b6f7297',37.4219947,-122.0840117,111,56),(43,'https://firebasestorage.googleapis.com/v0/b/myalert-372f0.appspot.com/o/alarmIMGS%2Frifiuti.jpg?alt=media&token=aea2b023-d77a-43c8-b2f1-65b4afd49cf2',37.4219947,-122.0840117,111,52),(44,'https://firebasestorage.googleapis.com/v0/b/myalert-372f0.appspot.com/o/alarmIMGS%2Frifiuti.jpg?alt=media&token=aea2b023-d77a-43c8-b2f1-65b4afd49cf2',37.4219947,-122.0840117,20,53),(45,'https://firebasestorage.googleapis.com/v0/b/myalert-372f0.appspot.com/o/alarmIMGS%2F5b69ddadb5528.image.jpg?alt=media&token=dcbb8b17-9026-4a13-8f28-a52924d0cb53',37.4219947,-122.0840117,20,50),(46,'https://firebasestorage.googleapis.com/v0/b/myalert-372f0.appspot.com/o/alarmIMGS%2F5c5941cceb3ce8233a05ee73.jpeg?alt=media&token=2ec14712-0c41-4fed-99f7-52f5bba25584',37.4219947,-122.0840117,110,50),(47,'https://firebasestorage.googleapis.com/v0/b/myalert-372f0.appspot.com/o/alarmIMGS%2Fdiscarica-abusiva-1200x751.jpg?alt=media&token=c376395a-6f08-41da-b38a-47530a79b648',37.4219947,-122.0840117,110,53),(48,'https://firebasestorage.googleapis.com/v0/b/myalert-372f0.appspot.com/o/alarmIMGS%2Fdiscarica-abusiva-saronno.jpg?alt=media&token=b2b34792-5d6d-4729-b017-4ed91a59eead',37.4219947,-122.0840117,111,52),(49,'https://firebasestorage.googleapis.com/v0/b/myalert-372f0.appspot.com/o/alarmIMGS%2Fr0_893_3024_2602_w1200_h678_fmax.jpg?alt=media&token=19f342e1-1d9b-4901-896e-11088ba6de00',37.4219947,-122.0840117,109,50);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `intervention`
--

DROP TABLE IF EXISTS `intervention`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `intervention` (
  `idIntervention` int(11) NOT NULL AUTO_INCREMENT,
  `idType` int(11) NOT NULL,
  `Lat` double NOT NULL,
  `Lon` double NOT NULL,
  `Address` varchar(45) NOT NULL,
  `City` varchar(45) NOT NULL,
  `StartDate` datetime DEFAULT NULL,
  `EndDate` datetime DEFAULT NULL,
  `ShortReport` mediumtext,
  `DetailedReport` longtext,
  `Status` varchar(45) NOT NULL DEFAULT 'signaled',
  `Count` int(11) DEFAULT NULL,
  PRIMARY KEY (`idIntervention`),
  KEY `fk_Intervention_Type1_idx` (`idType`),
  CONSTRAINT `fk_Intervention_Type1` FOREIGN KEY (`idType`) REFERENCES `type` (`idType`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intervention`
--

LOCK TABLES `intervention` WRITE;
/*!40000 ALTER TABLE `intervention` DISABLE KEYS */;
INSERT INTO `intervention` VALUES (50,3,37.4219989,-122.0839942,'Evergreen Terrace, 724','Springfield','2020-09-28 07:45:04','2020-09-28 10:45:04','Incendio domato in sicurezza.','L\'incendio è stato spento con successo. Nessun cittadino è in pericolo','closed',3),(51,5,37.4219989,-122.0839942,'Via Roma, 54','Lecce','2020-09-22 07:45:04','2020-09-22 15:45:04','Incidente risolto con successo','L\'incidente è avvenuto perchè uno dei due guidatori non ha rispettato lo stop. Aperta assicurazione a favore del malcapitato  ','closed',1),(52,4,37.4219989,-122.0839942,'Via Po, 7','Lecce','2020-08-14 15:45:04',NULL,NULL,NULL,'signaled',1),(53,4,37.4219989,-122.0839942,'Via G.Pascoli, 41','Lecce','2020-07-04 15:45:04','2020-07-04 17:45:04','Confermo presenza di plastica. Ho segnalato ai servizi sociali di intervenire il prima possibile',NULL,'assigned',2),(56,3,37.4219989,-122.0839942,'Via Como, 87','Lecce','2020-04-04 15:45:04','2020-04-04 20:45:04','Incendio di piccole dimensioni. Spento con successo',NULL,'assigned',1);
/*!40000 ALTER TABLE `intervention` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `manager` (
  `idManager` int(11) NOT NULL AUTO_INCREMENT,
  `idUser_FK` int(11) NOT NULL,
  `StartDate_task` datetime NOT NULL,
  `EndDate_task` datetime DEFAULT NULL,
  PRIMARY KEY (`idManager`),
  KEY `fk_Manager_User1_idx` (`idUser_FK`),
  CONSTRAINT `fk_Manager_User1` FOREIGN KEY (`idUser_FK`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (5,3,'2020-04-22 17:33:10',NULL);
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `type` (
  `idType` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `idManager` int(11) NOT NULL,
  PRIMARY KEY (`idType`),
  UNIQUE KEY `Name_UNIQUE` (`Name`),
  UNIQUE KEY `type_Name_IX` (`Name`),
  KEY `fk_Type_Manager1_idx` (`idManager`),
  CONSTRAINT `fk_Type_Manager1` FOREIGN KEY (`idManager`) REFERENCES `manager` (`idManager`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (3,'Incendio',5),(4,'Discarica abusiva',5),(5,'Incidente',5);
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Surname` varchar(45) NOT NULL,
  `Email` varchar(70) NOT NULL,
  `BirthDate` datetime NOT NULL,
  `Sex` varchar(1) NOT NULL,
  `Adress` varchar(70) NOT NULL,
  `City` varchar(45) NOT NULL,
  `Country` varchar(45) NOT NULL,
  `PushId` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `idUser_UNIQUE` (`idUser`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'Homer','Simpson','manager@manager.it','2020-04-22 17:33:10','M','Evergreen Terrace, 724','Springfield ','USA',''),(20,'Gabriele','Panico','gabriele.panico95@gmail.com','1988-06-08 14:33:55','M','Via Murica, 25','Soleto','ITA','42a0e22d-3245-4c29-a11c-75a3ce0180b7'),(21,'Dario','Rollo','wecet33242@rmomail.com','2020-09-25 00:00:00','M','Evergreen Terrace, 724','Springfield ','USA','720b34cb-df08-443d-aee2-6ba4e8ca77db'),(108,'Bart','Simpson','fifoy20841@rmomail.com','2020-09-27 00:00:00','M','Evergreen Terrace, 724','Springfield ','USA','6ce600f5-26bd-47be-9a61-e20c43b879cb'),(109,'Lisa','Simpson','lomila3750@yosemail.com','2020-09-27 09:46:04','F','Evergreen Terrace, 724','Springfield ','USA','42a0e22d-3245-4c29-a11c-75a3ce0180b7'),(110,'Ned','Flanders','vebelal378@htcsemail.com','2020-09-17 09:46:04','M','Evergreen Terrace, 724','Springfield ','USA',''),(111,'Milhouse','Van Houten','misix98410@finxmail.net','2020-04-11 09:46:04','M','Evergreen Terrace, 724','Springfield ','USA',NULL),(112,'Boe','Szyslak','vanobo2591@jalcemail.com','2020-01-25 00:00:00','M','Evergreen Terrace, 724','Springfield ','USA',NULL),(113,'Edna','Caprapall','xowefi9426@mailvxin.net','2020-07-28 00:00:00','F','Evergreen Terrace, 724','Springfield ','USA',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-29 19:33:22
