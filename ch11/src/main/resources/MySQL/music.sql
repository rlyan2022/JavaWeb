# MySQL-Front 5.0  (Build 1.0)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: music
# ------------------------------------------------------
# Server version 5.0.67-community-nt

DROP DATABASE IF EXISTS `music`;
CREATE DATABASE `music` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `music`;

#
# Table structure for table singer
#

DROP TABLE IF EXISTS `singer`;
CREATE TABLE `singer` (
  `SINGER_ID` int(10) unsigned NOT NULL auto_increment,
  `BAND_ID` int(10) unsigned default NULL,
  `COMPOSITIONSINGER_ID` int(10) unsigned default NULL,
  `NAME` varchar(45) default NULL,
  `REGION` varchar(45) default NULL,
  `SEX` char(1) default NULL,
  `TYPE` varchar(2) default NULL,
  PRIMARY KEY  (`SINGER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='歌手信息表';


#
# Table structure for table song
#

DROP TABLE IF EXISTS `song`;
CREATE TABLE `song` (
  `SONG_ID` int(10) unsigned NOT NULL auto_increment,
  `SINGER_ID` int(10) unsigned default NULL,
  `NAME` varchar(20) default NULL,
  `LOCATION` varchar(80) default NULL,
  PRIMARY KEY  (`SONG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='歌曲信息表';


#
# Table structure for table user
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `USER_ID` int(10) unsigned NOT NULL auto_increment,
  `USER_NAME` varchar(30) default NULL,
  `PASSWORD` varchar(20) default NULL,
  PRIMARY KEY  (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户信息表';
INSERT INTO `user` VALUES (1,'admin','admin');

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
