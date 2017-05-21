# MySQL-Front 5.0  (Build 1.0)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: db_oa
# ------------------------------------------------------
# Server version 5.0.67-community-nt

DROP DATABASE IF EXISTS `db_oa`;
CREATE DATABASE `db_oa` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_oa`;

#
# Table structure for table address
#

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `username` varchar(50) NOT NULL default '',
  `name` varchar(50) NOT NULL default '',
  `sex` varchar(10) default NULL,
  `mobile` varchar(20) default NULL,
  `email` varchar(50) default NULL,
  `qq` varchar(20) default NULL,
  `company` varchar(100) default NULL,
  `address` varchar(100) default NULL,
  `postcode` varchar(10) default NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `ID_2` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
INSERT INTO `address` VALUES (8,'admin','牛力','男','12345678911','123@126.com','123456','北京地税局','北京市昌平区','010000');
INSERT INTO `address` VALUES (9,'admin','李霞','女','12345678911','123@126.com','123456','北京地税局','北京市昌平区','010000');
INSERT INTO `address` VALUES (10,'admin','李华明','男','12345678911','123@126.com','123456','北京地税局','北京市昌平区','010000');
INSERT INTO `address` VALUES (11,'admin','张明','男','12345678911','123@126.com','123456','北京地税局','北京市昌平区','010000');
INSERT INTO `address` VALUES (12,'admin','陈丽','女','12345678911','123@126.com','123456','北京地税局','北京市昌平区','010000');
INSERT INTO `address` VALUES (14,'admin','测试','男','13423231212','ege@yahoo.com.cn','32423323','中国电信','北京市海淀区XXXX','100000');

#
# Table structure for table meeting
#

DROP TABLE IF EXISTS `meeting`;
CREATE TABLE `meeting` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `sender` varchar(50) NOT NULL default '',
  `starttime` varchar(20) default NULL,
  `endtime` varchar(20) default NULL,
  `address` varchar(100) default NULL,
  `title` varchar(100) default NULL,
  `content` text,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `ID_2` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
INSERT INTO `meeting` VALUES (1,'admin','2009-10-21','2009-10-22','公司T型办公室','加强员工办公效率！','加强员工办公效率！加强员工办公效率！加强员工办公效率！加强员工办公效率！');

#
# Table structure for table notice
#

DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `sender` varchar(50) NOT NULL default '',
  `title` varchar(100) default NULL,
  `content` text,
  `sendtime` varchar(20) default NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `ID_2` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
INSERT INTO `notice` VALUES (1,'admin','爱护公物人人有责！','爱护公物人人有责！','2009-12-20 20:06:39');
INSERT INTO `notice` VALUES (2,'admin','局域网内有人中毒，请注册杀毒！','局域网内有人中毒，请注册杀毒！','2009-12-20 20:06:56');
INSERT INTO `notice` VALUES (3,'admin','明后天放假通知！','明后天放假通知！','2009-12-20 20:07:22');
INSERT INTO `notice` VALUES (4,'sanqing','sanqing发公告','sanqing发公告sanqing发公告sanqing发公告','2010-02-01 16:40:35');

#
# Table structure for table schedule
#

DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `username` varchar(50) NOT NULL default '',
  `year` int(4) default NULL,
  `month` int(2) default NULL,
  `day` int(2) default NULL,
  `plan` text,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `ID_2` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
INSERT INTO `schedule` VALUES (1,'admin',2009,10,3,'出差！');
INSERT INTO `schedule` VALUES (3,'admin',2009,10,5,'项目需求分析！');
INSERT INTO `schedule` VALUES (4,'admin',2009,10,6,'和项目经理谈项目！');
INSERT INTO `schedule` VALUES (5,'admin',2009,10,7,'和客户谈项目需求！');
INSERT INTO `schedule` VALUES (6,'admin',2009,10,8,'项目功能分析！');
INSERT INTO `schedule` VALUES (7,'admin',2010,1,31,'买火车票！！！！');

#
# Table structure for table sms
#

DROP TABLE IF EXISTS `sms`;
CREATE TABLE `sms` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `username` varchar(50) NOT NULL default '',
  `sender` varchar(50) NOT NULL default '',
  `message` text,
  `sendtime` varchar(20) default NULL,
  `isRead` varchar(1) default '0',
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `ID_2` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
INSERT INTO `sms` VALUES (6,'admin','sanqing','你收到了吗？？','2010-02-01 16:41:18','1');
INSERT INTO `sms` VALUES (7,'sanqing','admin','收到了，你买火车票了吗？？','2010-02-01 16:41:51','1');

#
# Table structure for table user
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `username` varchar(50) default NULL,
  `password` varchar(50) default NULL,
  `email` varchar(50) default NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `ID_2` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
INSERT INTO `user` VALUES (1,'admin','admin','abc@163.com');
INSERT INTO `user` VALUES (2,'sanqing','sanqing','abc@163.com');

#
# Table structure for table worklog
#

DROP TABLE IF EXISTS `worklog`;
CREATE TABLE `worklog` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `username` varchar(50) NOT NULL default '',
  `year` int(4) default NULL,
  `month` int(2) default NULL,
  `day` int(2) default NULL,
  `title` varchar(100) default NULL,
  `description` text,
  `logtime` varchar(20) default NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `ID_2` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
INSERT INTO `worklog` VALUES (1,'admin',2009,11,1,'很高兴客户已经满意了！','很高兴客户已经满意了！','2009-12-20 19:54:37');
INSERT INTO `worklog` VALUES (2,'admin',2009,11,2,'今天好冷啊！','今天好冷啊！','2009-12-20 19:55:05');
INSERT INTO `worklog` VALUES (3,'admin',2009,11,3,'客户要求修改功能！','客户要求修改功能！','2009-12-20 19:55:31');
INSERT INTO `worklog` VALUES (4,'admin',2009,11,4,'客户是上帝，要求一定要达到！','客户是上帝，要求一定要达到！','2009-12-20 19:55:47');
INSERT INTO `worklog` VALUES (6,'admin',2010,2,2,'测试工作日志','测试工作日志！！！','2010-02-01 16:39:18');

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
