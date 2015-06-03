-- MySQL dump 10.13  Distrib 5.5.41, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: db_study
-- ------------------------------------------------------
-- Server version	5.5.41-0ubuntu0.14.04.1

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
-- Table structure for table `tb_article`
--

DROP TABLE IF EXISTS `tb_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(20) DEFAULT NULL COMMENT '文章标题',
  `content` varchar(255) DEFAULT NULL COMMENT '文章内容',
  `subject_tpye_id` int(11) DEFAULT NULL COMMENT '类型编号',
  PRIMARY KEY (`id`),
  KEY `subject_tpye_id` (`subject_tpye_id`),
  CONSTRAINT `tb_subject_ibfk_1` FOREIGN KEY (`subject_tpye_id`) REFERENCES `tb_subject_type` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_discipline`
--

DROP TABLE IF EXISTS `tb_discipline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_discipline` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '题目编号',
  `question` varchar(255) DEFAULT NULL COMMENT '问题',
  `answers` varchar(255) DEFAULT NULL COMMENT '答案',
  `score` int(11) DEFAULT NULL COMMENT '分数',
  `subject_tpye_id` int(11) DEFAULT NULL COMMENT '类型编号',
  PRIMARY KEY (`id`),
  KEY `subject_tpye_id` (`subject_tpye_id`),
  CONSTRAINT `tb_subject_ibfk_2` FOREIGN KEY (`subject_tpye_id`) REFERENCES `tb_subject_type` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_discipline_option`
--

DROP TABLE IF EXISTS `tb_discipline_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_discipline_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '选项编号',
  `discipline_id` int(11) DEFAULT NULL COMMENT '题目编号',
  `content` varchar(255) DEFAULT NULL COMMENT '选项内容',
  PRIMARY KEY (`id`),
  KEY `discipline_id` (`discipline_id`),
  CONSTRAINT `tb_discipline_option_ibfk_1` FOREIGN KEY (`discipline_id`) REFERENCES `tb_discipline` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_questionnaire`
--

DROP TABLE IF EXISTS `tb_questionnaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_questionnaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '问卷编号',
  `subject_type_id` int(11) DEFAULT NULL COMMENT '科目编号',
  PRIMARY KEY (`id`),
  KEY `subject_type_id` (`subject_type_id`),
  CONSTRAINT `tb_questionnaire_ibfk_1` FOREIGN KEY (`subject_type_id`) REFERENCES `tb_discipline` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_questionnaire_discipline`
--

DROP TABLE IF EXISTS `tb_questionnaire_discipline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_questionnaire_discipline` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '问卷题目编号',
  `questionnaire_id` int(11) DEFAULT NULL COMMENT '问卷编号',
  `discipline_id` int(11) DEFAULT NULL COMMENT '题目编号',
  PRIMARY KEY (`id`),
  KEY `questionnaire_id` (`questionnaire_id`),
  KEY `discipline_id` (`discipline_id`),
  CONSTRAINT `tb_questionnaire_discipline_ibfk_1` FOREIGN KEY (`questionnaire_id`) REFERENCES `tb_questionnaire` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `tb_questionnaire_discipline_ibfk_2` FOREIGN KEY (`discipline_id`) REFERENCES `tb_discipline` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_subject_type`
--

DROP TABLE IF EXISTS `tb_subject_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_subject_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '1语文,2数学,3英语,4诗歌,5政治历史,6健康心里,7生活百科',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `name` varchar(12) DEFAULT NULL COMMENT '真实名字',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `login_time` date DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_user_collect_discipline`
--

DROP TABLE IF EXISTS `tb_user_collect_discipline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user_collect_discipline` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `discipline_id` int(11) DEFAULT NULL COMMENT '文章编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `collect_date` date DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`id`),
  KEY `discipline_id` (`discipline_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tb_user_collect_discipline_ibfk_1` FOREIGN KEY (`discipline_id`) REFERENCES `tb_discipline` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `tb_user_collect_discipline_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_user_exam`
--

DROP TABLE IF EXISTS `tb_user_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user_exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `sum` int(11) DEFAULT NULL COMMENT '总分',
  `create_date` date DEFAULT NULL COMMENT '做题日期',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tb_user_exam_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_user_exam_discipline`
--

DROP TABLE IF EXISTS `tb_user_exam_discipline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user_exam_discipline` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `exam_id` int(11) DEFAULT NULL COMMENT '考试编号',
  `discipline_id` int(11) DEFAULT NULL COMMENT '题目编号',
  `answers` varchar(255) DEFAULT NULL COMMENT '选择的答案',
  PRIMARY KEY (`id`),
  KEY `exam_id` (`exam_id`),
  KEY `discipline_id` (`discipline_id`),
  CONSTRAINT `tb_user_exam_discipline_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `tb_user_exam` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `tb_user_exam_discipline_ibfk_2` FOREIGN KEY (`discipline_id`) REFERENCES `tb_discipline` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_user_questionnaire`
--

DROP TABLE IF EXISTS `tb_user_questionnaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user_questionnaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `questionnaire_id` int(11) DEFAULT NULL COMMENT '问卷编号',
  `sum` int(11) DEFAULT NULL COMMENT '总分',
  `create_date` date DEFAULT NULL COMMENT '做题日期',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `questionnaire_id` (`questionnaire_id`),
  CONSTRAINT `tb_user_questionnaire_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `tb_user_questionnaire_ibfk_2` FOREIGN KEY (`questionnaire_id`) REFERENCES `tb_questionnaire` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_user_read_article`
--

DROP TABLE IF EXISTS `tb_user_read_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user_read_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '阅读文章编号',
  `article_id` int(11) DEFAULT NULL COMMENT '文章编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `read_date` date DEFAULT NULL COMMENT '阅读时间',
  `update_date` date DEFAULT NULL COMMENT '下次阅读时间',
  `read_count` int(11) DEFAULT NULL COMMENT '阅读次数',
  PRIMARY KEY (`id`),
  KEY `article_id` (`article_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tb_user_read_article_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `tb_article` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `tb_user_read_article_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_user_wrong_discipline`
--

DROP TABLE IF EXISTS `tb_user_wrong_discipline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user_wrong_discipline` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '题目错误编号',
  `discipline_id` int(11) DEFAULT NULL COMMENT '题目编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `create_date` date DEFAULT NULL COMMENT '第一次错的时间',
  `update_date` date DEFAULT NULL COMMENT '下次错误的时间',
  `count` int(11) DEFAULT NULL COMMENT '总共次数',
  PRIMARY KEY (`id`),
  KEY `discipline_id` (`discipline_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tb_user_wrong_discipline_ibfk_1` FOREIGN KEY (`discipline_id`) REFERENCES `tb_discipline` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `tb_user_wrong_discipline_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-03 12:17:37
