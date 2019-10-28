-- MySQL dump 10.13  Distrib 5.7.11, for Win64 (x86_64)
--
-- Host: localhost    Database: sms
-- ------------------------------------------------------
-- Server version	5.7.11

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
-- Table structure for table `class_table`
--

DROP TABLE IF EXISTS `class_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class_table` (
  `class_id` int(11) NOT NULL,
  `class_name` varchar(15) CHARACTER SET utf8 NOT NULL,
  `maj_id` int(11) NOT NULL,
  `inst_id` int(11) NOT NULL,
  PRIMARY KEY (`class_id`),
  KEY `class_maj_key_idx` (`maj_id`),
  KEY `class_inst_key_idx` (`inst_id`),
  CONSTRAINT `class_inst_key` FOREIGN KEY (`inst_id`) REFERENCES `institute_table` (`inst_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `class_maj_key` FOREIGN KEY (`maj_id`) REFERENCES `major_table` (`maj_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_table`
--

LOCK TABLES `class_table` WRITE;
/*!40000 ALTER TABLE `class_table` DISABLE KEYS */;
INSERT INTO `class_table` VALUES (1,'软工一班',1,1),(2,'软工二班',1,1),(3,'卓越一班',1,1),(4,'计科一班',5,2),(5,'物联网一班',2,1),(6,'网络一班',3,2),(7,'信息一班',4,2),(8,'金融一班',6,3);
/*!40000 ALTER TABLE `class_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_table`
--

DROP TABLE IF EXISTS `course_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_table` (
  `coz_id` int(11) NOT NULL,
  `coz_name` varchar(45) CHARACTER SET utf8 NOT NULL,
  `coz_place` varchar(45) CHARACTER SET utf8 NOT NULL,
  `coz_credit` int(1) NOT NULL,
  `inst_id` int(11) NOT NULL,
  `maj_id` int(11) DEFAULT NULL,
  `tch_id` int(13) NOT NULL,
  `coz_time` varchar(45) CHARACTER SET utf8 NOT NULL,
  `coz_year` int(4) NOT NULL,
  `coz_semester` int(1) NOT NULL,
  PRIMARY KEY (`coz_id`),
  KEY `coz_inst_key_idx` (`inst_id`),
  KEY `coz_maj_key_idx` (`maj_id`),
  KEY `coz_tch_key_idx` (`tch_id`),
  CONSTRAINT `coz_inst_key` FOREIGN KEY (`inst_id`) REFERENCES `institute_table` (`inst_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `coz_maj_key` FOREIGN KEY (`maj_id`) REFERENCES `major_table` (`maj_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `coz_tch_key` FOREIGN KEY (`tch_id`) REFERENCES `teacher_table` (`tch_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_table`
--

LOCK TABLES `course_table` WRITE;
/*!40000 ALTER TABLE `course_table` DISABLE KEYS */;
INSERT INTO `course_table` VALUES (1,'计算机基础','1区,5-301',2,1,1,1,'周三:3-14周,每1周;11-13节',2019,1),(2,'数据结构与算法','3区, 1-101',2,1,1,1,'周三:3-14周,每1周;11-13节',2019,1),(3,'操作系统','3区, 1-101',2,3,6,4,'周三:3-14周,每1周;11-13节',2013,1),(4,'操作系统2','3区, 1-101',2,1,1,2,'周三:3-14周,每1周;11-13节',2019,1),(5,'操作系统3','3区, 1-101',2,2,3,5,'周三:3-14周,每1周;11-13节',2017,3),(6,'操作系统4','3区, 1-101',2,2,4,3,'周三:3-14周,每1周;11-13节',2019,1);
/*!40000 ALTER TABLE `course_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institute_table`
--

DROP TABLE IF EXISTS `institute_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institute_table` (
  `inst_id` int(11) NOT NULL,
  `inst_name` varchar(15) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`inst_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institute_table`
--

LOCK TABLES `institute_table` WRITE;
/*!40000 ALTER TABLE `institute_table` DISABLE KEYS */;
INSERT INTO `institute_table` VALUES (1,'国际软件学院'),(2,'计算机学院'),(3,'经济与管理学院'),(4,'数学学院'),(5,'信息管理学院');
/*!40000 ALTER TABLE `institute_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major_table`
--

DROP TABLE IF EXISTS `major_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `major_table` (
  `maj_id` int(13) NOT NULL,
  `maj_name` varchar(15) CHARACTER SET utf8 NOT NULL,
  `inst_id` int(13) NOT NULL,
  PRIMARY KEY (`maj_id`),
  KEY `inst_id_idx` (`inst_id`),
  CONSTRAINT `inst_id` FOREIGN KEY (`inst_id`) REFERENCES `institute_table` (`inst_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major_table`
--

LOCK TABLES `major_table` WRITE;
/*!40000 ALTER TABLE `major_table` DISABLE KEYS */;
INSERT INTO `major_table` VALUES (1,'软件工程',1),(2,'物联网',1),(3,'网络安全',2),(4,'信息安全',2),(5,'计算机科学与技术',2),(6,'金融工程',3),(7,'空间信息',1);
/*!40000 ALTER TABLE `major_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score_table`
--

DROP TABLE IF EXISTS `score_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `score_table` (
  `stu_id` int(13) NOT NULL,
  `coz_id` int(11) NOT NULL,
  `score` float(3,1) DEFAULT NULL,
  PRIMARY KEY (`stu_id`,`coz_id`),
  KEY `score_coz_key_idx` (`coz_id`),
  CONSTRAINT `score_coz_key` FOREIGN KEY (`coz_id`) REFERENCES `course_table` (`coz_id`),
  CONSTRAINT `score_stu_key` FOREIGN KEY (`stu_id`) REFERENCES `student_table` (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score_table`
--

LOCK TABLES `score_table` WRITE;
/*!40000 ALTER TABLE `score_table` DISABLE KEYS */;
INSERT INTO `score_table` VALUES (1,1,99.5),(1,2,80.0),(1,3,60.0),(2,1,99.0),(2,2,NULL),(2,3,NULL),(2,4,NULL),(3,2,NULL);
/*!40000 ALTER TABLE `score_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_table`
--

DROP TABLE IF EXISTS `student_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_table` (
  `stu_id` int(13) NOT NULL,
  `stu_name` varchar(10) CHARACTER SET utf8 NOT NULL,
  `stu_sex` varchar(1) CHARACTER SET utf8 NOT NULL,
  `stu_birth_date` date NOT NULL,
  `stu_birth_place` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `stu_political` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `inst_id` int(11) NOT NULL,
  `maj_id` int(11) NOT NULL,
  `class_id` int(11) NOT NULL,
  PRIMARY KEY (`stu_id`),
  KEY `inst_id_idx` (`inst_id`),
  KEY `stu_maj_key_idx` (`maj_id`),
  KEY `stu_class_key_idx` (`class_id`),
  CONSTRAINT `stu_class_key` FOREIGN KEY (`class_id`) REFERENCES `class_table` (`class_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `stu_inst_key` FOREIGN KEY (`inst_id`) REFERENCES `institute_table` (`inst_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `stu_maj_key` FOREIGN KEY (`maj_id`) REFERENCES `major_table` (`maj_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_table`
--

LOCK TABLES `student_table` WRITE;
/*!40000 ALTER TABLE `student_table` DISABLE KEYS */;
INSERT INTO `student_table` VALUES (1,'王子昂','女','2019-09-02','安徽','群众',3,6,8),(2,'余连玮','女','2019-09-20','安徽','群众',1,1,1),(3,'郑晓颖','女','2019-09-14','安徽','群众',2,5,6),(4,'郑晓颖','女','2019-09-20','安徽','群众',1,1,2);
/*!40000 ALTER TABLE `student_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_table`
--

DROP TABLE IF EXISTS `teacher_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_table` (
  `tch_id` int(13) NOT NULL,
  `tch_name` varchar(10) CHARACTER SET utf8 NOT NULL,
  `tch_sex` varchar(1) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`tch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_table`
--

LOCK TABLES `teacher_table` WRITE;
/*!40000 ALTER TABLE `teacher_table` DISABLE KEYS */;
INSERT INTO `teacher_table` VALUES (1,'朱卫平','男'),(2,'赵小刚','男'),(3,'陈刚','男'),(4,'王倩','男'),(5,'韩波','男');
/*!40000 ALTER TABLE `teacher_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_table`
--

DROP TABLE IF EXISTS `user_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_table` (
  `username` varchar(10) NOT NULL,
  `password` varchar(45) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_table`
--

LOCK TABLES `user_table` WRITE;
/*!40000 ALTER TABLE `user_table` DISABLE KEYS */;
INSERT INTO `user_table` VALUES ('admin','123456'),('user','987654');
/*!40000 ALTER TABLE `user_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-18 18:19:28
