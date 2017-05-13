# MySQL-Front 5.0  (Build 1.0)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: bfvipshopping
# ------------------------------------------------------
# Server version 5.0.67-community-nt


DROP DATABASE IF EXISTS `bfvipshopping`;
CREATE DATABASE `bfvipshopping` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bfvipshopping`;


#
# Table structure for table commodity
#

DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `commodityId` int(11) NOT NULL auto_increment,
  `commodityName` varchar(30) default NULL,
  `price` decimal(10,2) default NULL,
  `agio` decimal(4,3) default NULL,
  PRIMARY KEY  (`commodityId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=gb2312;
INSERT INTO `commodity` VALUES (1,'伊利纯牛奶',2.3,1);
INSERT INTO `commodity` VALUES (2,'蒙牛纯牛奶',2.5,1);
INSERT INTO `commodity` VALUES (3,'三元纯牛奶',2.1,0.9);
INSERT INTO `commodity` VALUES (4,'王老吉',3.5,1);
INSERT INTO `commodity` VALUES (5,'伊利奶粉',40,1);
INSERT INTO `commodity` VALUES (6,'蒙牛奶粉',50,1);
INSERT INTO `commodity` VALUES (7,'三元奶粉',45,1);
INSERT INTO `commodity` VALUES (8,'JavaWeb项目开发',50.9,0.85);

#
# Table structure for table consume
#

DROP TABLE IF EXISTS `consume`;
CREATE TABLE `consume` (
  `consumeId` int(11) NOT NULL auto_increment,
  `vipId` int(11) default NULL,
  `name` varchar(20) default NULL,
  `commodityId` int(11) default NULL,
  `commodityName` varchar(30) default NULL,
  `price` decimal(10,2) default NULL,
  `practicePrice` decimal(10,2) default NULL,
  PRIMARY KEY  (`consumeId`),
  KEY `vipId` (`vipId`),
  KEY `commodityId` (`commodityId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=gb2312;
INSERT INTO `consume` VALUES (1,1,'刘爱明',1,'伊利纯牛奶',2.3,2.3);
INSERT INTO `consume` VALUES (2,1,'刘爱明',2,'蒙牛纯牛奶',2.5,2.5);
INSERT INTO `consume` VALUES (3,1,'刘爱明',3,'三元纯牛奶',2.1,1.89);
INSERT INTO `consume` VALUES (4,2,'张力',1,'伊利纯牛奶',2.3,2.3);
INSERT INTO `consume` VALUES (5,2,'张力',2,'蒙牛纯牛奶',2.5,2.5);
INSERT INTO `consume` VALUES (6,2,'张力',3,'三元纯牛奶',2.1,1.89);
INSERT INTO `consume` VALUES (7,2,'张力',4,'王老吉',3.5,3.5);
INSERT INTO `consume` VALUES (8,2,'张力',5,'伊利奶粉',40,40);
INSERT INTO `consume` VALUES (9,3,'秦华',4,'王老吉',3.5,3.5);
INSERT INTO `consume` VALUES (10,5,'笑笑',5,'伊利奶粉',40,40);
INSERT INTO `consume` VALUES (11,5,'笑笑',6,'蒙牛奶粉',50,50);
INSERT INTO `consume` VALUES (12,1,'刘爱明',8,'JavaWeb项目开发',50.9,43.27);
INSERT INTO `consume` VALUES (13,1,'刘爱明',4,'王老吉',3.5,3.5);
INSERT INTO `consume` VALUES (14,3,'秦华',4,'王老吉',3.5,3.5);

#
# Table structure for table user
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(20) NOT NULL default '',
  `password` varchar(20) default NULL,
  `quanxian` int(11) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
INSERT INTO `user` VALUES ('admin','admin',1);
INSERT INTO `user` VALUES ('ibeifeng','123',0);
INSERT INTO `user` VALUES ('lifengxing','123',0);

#
# Table structure for table vip
#

DROP TABLE IF EXISTS `vip`;
CREATE TABLE `vip` (
  `vipId` int(11) NOT NULL auto_increment,
  `joinTime` date default NULL,
  `name` varchar(20) default NULL,
  `age` int(11) default NULL,
  `profession` varchar(20) default NULL,
  PRIMARY KEY  (`vipId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=gb2312;
INSERT INTO `vip` VALUES (1,'2009-12-15','刘爱明',30,'工程师');
INSERT INTO `vip` VALUES (2,'2009-12-15','张力',50,'评论员');
INSERT INTO `vip` VALUES (3,'2009-12-01','秦华',21,'职业玩家');
INSERT INTO `vip` VALUES (4,'2009-12-01','李明',22,'工程师');
INSERT INTO `vip` VALUES (5,'2009-12-03','笑笑',24,'银行职员');
INSERT INTO `vip` VALUES (6,'2009-12-04','黄一',25,'it评论员');
INSERT INTO `vip` VALUES (7,'2010-01-30','张三',30,'it工程师');
INSERT INTO `vip` VALUES (8,'2010-01-30','李四',30,'it评论员');

#
#  Foreign keys for table consume
#

ALTER TABLE `consume`
ADD CONSTRAINT `consume_ibfk_1` FOREIGN KEY (`vipId`) REFERENCES `vip` (`vipId`),
  ADD CONSTRAINT `consume_ibfk_2` FOREIGN KEY (`commodityId`) REFERENCES `commodity` (`commodityId`);


/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
