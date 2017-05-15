# MySQL-Front 5.0  (Build 1.0)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: pfms
# ------------------------------------------------------
# Server version 5.0.67-community-nt

DROP DATABASE IF EXISTS `pfms`;
CREATE DATABASE `pfms` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `pfms`;

#
# Table structure for table payin
#

DROP TABLE IF EXISTS `payin`;
CREATE TABLE `payin` (
  `Id` int(11) NOT NULL auto_increment,
  `payInName` varchar(12) default NULL,
  `payInMoney` double(7,2) default NULL,
  `payInDate` date default NULL,
  `userId` int(11) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Table structure for table payout
#

DROP TABLE IF EXISTS `payout`;
CREATE TABLE `payout` (
  `Id` int(11) NOT NULL auto_increment,
  `payOutName` varchar(12) default NULL,
  `payOutMoney` double(7,2) default NULL,
  `payOutDate` date default NULL,
  `userId` int(11) default NULL,
  PRIMARY KEY  (`Id`),
  KEY `userId` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
INSERT INTO `payout` VALUES (1,'吃早餐12',2.6,'2009-06-10',52);
INSERT INTO `payout` VALUES (3,'购物',208.3,'2009-06-09',50);
INSERT INTO `payout` VALUES (4,'吃晚饭',12,'2009-08-27',50);
INSERT INTO `payout` VALUES (16,'吃中饭',34,'2009-01-10',50);
INSERT INTO `payout` VALUES (17,'吃中饭',25,'2009-06-10',50);
INSERT INTO `payout` VALUES (18,'吃中饭',23,'2009-06-10',50);
INSERT INTO `payout` VALUES (19,'吃中饭',34,'2009-06-10',50);
INSERT INTO `payout` VALUES (20,'吃中饭',23,'2009-06-11',50);
INSERT INTO `payout` VALUES (21,'吃中饭',25,'2009-06-10',50);
INSERT INTO `payout` VALUES (22,'吃中饭',23,'2009-06-10',50);
INSERT INTO `payout` VALUES (23,'吃中饭',23,'2009-06-12',50);
INSERT INTO `payout` VALUES (24,'吃盒饭',14,'2009-06-30',50);
INSERT INTO `payout` VALUES (25,'吃盒饭',12,'2009-06-12',50);
INSERT INTO `payout` VALUES (26,'吃盒饭',12,'2009-06-12',50);
INSERT INTO `payout` VALUES (27,'吃盒饭',12,'2009-06-12',50);
INSERT INTO `payout` VALUES (28,'吃盒饭',12,'2009-06-12',50);
INSERT INTO `payout` VALUES (29,'吃中饭',23,'2009-06-10',52);
INSERT INTO `payout` VALUES (31,'吃中饭',23,'2009-06-10',52);
INSERT INTO `payout` VALUES (32,'吃晚饭',23,'2009-06-10',52);
INSERT INTO `payout` VALUES (33,'吃晚饭',23,'2009-06-10',52);
INSERT INTO `payout` VALUES (34,'吃晚饭',23,'2009-06-10',52);
INSERT INTO `payout` VALUES (35,'办理公交卡',30,'2009-10-30',52);

#
# Table structure for table user
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Id` int(11) NOT NULL auto_increment,
  `username` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `quanxian` int(11) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
INSERT INTO `user` VALUES (50,'xiaolu','123',0);
INSERT INTO `user` VALUES (52,'admin','admin',1);
INSERT INTO `user` VALUES (53,'james','12345',NULL);

#
#  Foreign keys for table payout
#

ALTER TABLE `payout`
ADD CONSTRAINT `payout_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`Id`);


/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
