# MySQL-Front 5.0  (Build 1.0)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: mybirger
# ------------------------------------------------------
# Server version 5.0.67-community-nt

DROP DATABASE IF EXISTS `mybirger`;
CREATE DATABASE `mybirger` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mybirger`;

#
# Table structure for table din
#

DROP TABLE IF EXISTS `din`;
CREATE TABLE `din` (
  `Id` varchar(20) NOT NULL,
  `Hao` varchar(10) NOT NULL,
  `Qifei` varchar(20) NOT NULL,
  `Mudi` varchar(20) NOT NULL,
  `Rqi` varchar(20) NOT NULL,
  `Jiage` varchar(10) NOT NULL,
  `Piao` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=uft8;
INSERT INTO `din` VALUES ('eggpeijun','T101','武汉','北京','2009-12-11','10',1);
INSERT INTO `din` VALUES ('eggpeijun','T114','长沙','北京','2010-01-01','540',2);
INSERT INTO `din` VALUES ('jamestest','T110','武汉','北京','2010-02-09','100',0);

#
# Table structure for table sch
#

DROP TABLE IF EXISTS `sch`;
CREATE TABLE `sch` (
  `Hao` varchar(10) NOT NULL,
  `Qifei` varchar(20) NOT NULL,
  `Rqi` date default NULL,
  `Mudi` varchar(20) NOT NULL,
  `Jiage` int(5) NOT NULL,
  `Piaosu` int(11) NOT NULL,
  PRIMARY KEY  (`Hao`)
) ENGINE=InnoDB DEFAULT CHARSET=uft8;
INSERT INTO `sch` VALUES ('T101','武汉','2009-12-11','北京',10,0);
INSERT INTO `sch` VALUES ('T110','武汉','2010-02-09','北京',100,10);
INSERT INTO `sch` VALUES ('T114','长沙','2010-01-01','北京',540,198);
INSERT INTO `sch` VALUES ('T125','上海','2010-01-06','北京',270,30);

#
# Table structure for table user
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Username` varchar(20) NOT NULL,
  `Password` varchar(16) NOT NULL,
  `Name` varchar(10) NOT NULL,
  `Sex` varchar(2) NOT NULL,
  `Tel` varchar(16) NOT NULL,
  `Email` varchar(30) default NULL,
  PRIMARY KEY  (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=uft8;
INSERT INTO `user` VALUES ('eggpeijun','123456','zhangsan','男','010456789123','fdsfds@ffwe.com');
INSERT INTO `user` VALUES ('jamestest','123456','杨波','男','01023232323','123@qq.com');

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
