# MySQL-Front 5.0  (Build 1.0)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: db_hr
# ------------------------------------------------------
# Server version 5.0.67-community-nt

DROP DATABASE IF EXISTS `db_hr`;
CREATE DATABASE `db_hr` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_hr`;

#
# Table structure for table educate
#

CREATE TABLE `educate` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(100) NOT NULL,
  `purpose` varchar(500) NOT NULL,
  `begintime` datetime default NULL,
  `endtime` datetime default NULL,
  `datum` text,
  `teacher` varchar(50) default NULL,
  `student` varchar(50) default NULL,
  `createtime` datetime default NULL,
  `educate` bit(1) NOT NULL,
  `effect` varchar(500) default NULL,
  `summarize` text,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='培训信息表';
INSERT INTO `educate` VALUES (2,'Swing课程培训','提高员工Swing开发的能力','2009-09-10','2009-10-30','《Swing从入门到精通》、《零基础学Swing》','方威','所有Java程序员','2009-10-26 11:47:05',b'1','很好','在这次培训中，大家都学会了如何进行Swing开发');
INSERT INTO `educate` VALUES (3,'JavaWeb项目培训','增强员工的动手能力','2010-01-05','2010-01-09','Java编程思想。','SUN','所有Java程序员','2010-01-28 14:49:49',b'1','效果很好','效果不错');

#
# Table structure for table institution
#

CREATE TABLE `institution` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `reason` varchar(50) default NULL,
  `explains` text,
  `createtime` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='奖惩信息表';
INSERT INTO `institution` VALUES (1,'小李罚款100元','迟到1小时','凡是上班时间迟到者一律罚款50元，超过1小时罚款100元！','2009-10-22 14:11:39');
INSERT INTO `institution` VALUES (2,'杨明奖励100元','连续加班3天','','2009-10-26 14:25:22');

#
# Table structure for table job
#

CREATE TABLE `job` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `sex` bit(1) default '',
  `age` int(11) default NULL,
  `job` varchar(50) default NULL,
  `specialty` varchar(50) default NULL,
  `experience` varchar(50) default NULL,
  `studyeffort` varchar(50) default NULL,
  `school` varchar(50) default NULL,
  `tel` varchar(50) default NULL,
  `email` varchar(50) default NULL,
  `createtime` datetime default NULL,
  `content` text,
  `isstock` bit(1) default '\0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='应聘人员信息表';
INSERT INTO `job` VALUES (2,'刘笑笑',b'1',30,'技术员','信息与计算科学','2','本科','湖南城市学院','123456789','928968880@qq.com','2009-10-21 16:49:23','ddddd',b'1');
INSERT INTO `job` VALUES (3,'张振华',b'1',30,'技术员','信息与计算科学','2','本科','湖南城市学院','123456789','eggpeijun@qq.com','2009-10-21 10:23:17','',b'1');
INSERT INTO `job` VALUES (4,'李东阳',b'1',25,'Web程序员','信息与计算科学','1','本科','中南大学','123456789','123456@qq.com','2009-10-22 20:10:05','本人在校期间曾经参加过学校教务处网站的编写。',b'1');
INSERT INTO `job` VALUES (6,'徐翔',b'1',25,'Java工程师','信息工程','3','本科','湖南大学','789456123','123456@qq.com','2009-10-26 10:56:30','2007年，XXX软件公司从事Java Web开发工作。\r\n2008年，XXX软件公司担任项目组长工作。',b'1');

#
# Table structure for table stipend
#

CREATE TABLE `stipend` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `basic` float default NULL,
  `eat` float default NULL,
  `house` float default NULL,
  `duty` float default NULL,
  `scot` float default NULL,
  `punishment` float default NULL,
  `other` float default NULL,
  `granttime` datetime default NULL,
  `totalize` float default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='薪金信息表';
INSERT INTO `stipend` VALUES (2,'张敏',1111,300,300,300,200,0,600,'2009-02-03',2411);
INSERT INTO `stipend` VALUES (3,'李明',2523,300,300,300,200,100,200,'2009-02-03',3323);
INSERT INTO `stipend` VALUES (4,'徐翔',2800,300,300,200,200,200,200,'2009-02-03',3400);
INSERT INTO `stipend` VALUES (5,'张亮',6500,1000,1000,500,800,600,500,'2009-02-03',8100);
INSERT INTO `stipend` VALUES (6,'徐翔',4500,200,200,120,400,300,500,'2009-02-03',4820);

#
# Table structure for table users
#

CREATE TABLE `users` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `sex` bit(1) default '',
  `birthday` datetime NOT NULL,
  `createtime` datetime NOT NULL,
  `isadmin` bit(1) default '\0',
  `content` text,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='人员信息表';
INSERT INTO `users` VALUES (1,'admin','admin',b'1','1988-01-03','2005-05-09',b'1','超级管理员');
INSERT INTO `users` VALUES (4,'sanqing','1234567',b'1','1988-01-04','2009-10-21 10:21:33',b'1','管理员');

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
