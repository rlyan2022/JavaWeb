# MySQL-Front 5.0  (Build 1.0)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: db_affairmanage
# ------------------------------------------------------
# Server version 5.0.67-community-nt

DROP DATABASE IF EXISTS `db_affairmanage`;
CREATE DATABASE `db_affairmanage` /*!40100 DEFAULT CHARACTER SET gb2312 */;
USE `db_affairmanage`;

#
# Table structure for table tb_criticism
#

CREATE TABLE `tb_criticism` (
  `criticismID` int(11) NOT NULL auto_increment,
  `criticismContent` text,
  `employeeID` int(11) default NULL,
  `criticismTime` datetime default NULL,
  `messageID` int(11) default NULL,
  PRIMARY KEY  (`criticismID`),
  KEY `employeeID` (`employeeID`),
  KEY `messageID` (`messageID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gb2312 COMMENT='消息批复表';
INSERT INTO `tb_criticism` VALUES (3,'<p>不错，以后大家好好干活！！</p>',3052,'2009-08-25 15:08:16',14);
INSERT INTO `tb_criticism` VALUES (4,'<p>测试批复</p>',3052,'2010-01-26 20:52:46',14);
INSERT INTO `tb_criticism` VALUES (5,'<p>测试批复</p>',3052,'2010-01-26 20:53:06',16);

#
# Table structure for table tb_employee
#

CREATE TABLE `tb_employee` (
  `employeeID` int(11) NOT NULL default '0',
  `employeeName` varchar(20) default NULL,
  `employeeSex` bit(1) default NULL,
  `employeeBirth` date default NULL,
  `employeePhone` varchar(20) default NULL,
  `employeePlace` varchar(50) default NULL,
  `joinTime` date default NULL,
  `password` varchar(20) default NULL,
  `isLead` bit(1) default NULL,
  PRIMARY KEY  (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='员工信息表';
INSERT INTO `tb_employee` VALUES (3052,'李俊',b'1','1978-01-08','1378675****','北京市','2008-12-30','123456',b'1');

#
# Table structure for table tb_message
#

CREATE TABLE `tb_message` (
  `messageID` int(11) NOT NULL auto_increment,
  `messageTitle` varchar(50) default NULL,
  `messageContent` text,
  `employeeID` int(11) default NULL,
  `publishTime` datetime default NULL,
  PRIMARY KEY  (`messageID`),
  KEY `employeeID` (`employeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=gb2312 COMMENT='消息表';
INSERT INTO `tb_message` VALUES (14,'网络维护公告','<p class=\"MsoNormal\" style=\"margin: 0cm 0cm 0pt; text-indent: 48pt; line-height: 42pt; mso-char-indent-count: 2.0; mso-line-height-rule: exactly\"><span style=\"font-size: 24pt; font-family: 宋体; mso-ascii-font-family: \'Times New Roman\'; mso-hansi-font-family: \'Times New Roman\'\">为保证网络上网，网络信息中心特安排了网络维护人员值班。各用户如有网络故障，请按以下方式联系。</span><span lang=\"EN-US\" style=\"font-size: 24pt\"><o:p></o:p></span></p>\r\n<p class=\"MsoNormal\" style=\"margin: 0cm 0cm 0pt; text-indent: 24pt; line-height: 42pt; mso-char-indent-count: 1.0; mso-line-height-rule: exactly\"><span style=\"font-size: 24pt; font-family: 宋体; mso-ascii-font-family: \'Times New Roman\'; mso-hansi-font-family: \'Times New Roman\'\">　值班电话：</span><span lang=\"EN-US\" style=\"font-size: 24pt\">XXXXXX</span></p>\r\n<p class=\"MsoNormal\" style=\"margin: 0cm 0cm 0pt; text-indent: 24pt; line-height: 42pt; mso-char-indent-count: 1.0; mso-line-height-rule: exactly\"><span style=\"font-size: 24pt; font-family: 宋体; mso-ascii-font-family: \'Times New Roman\'; mso-hansi-font-family: \'Times New Roman\'\">&nbsp;值班人：周先生</span></p>',3052,'2009-08-25 14:18:59');
INSERT INTO `tb_message` VALUES (16,'开会通知','<p><span style=\"color: #3366ff\"><span style=\"background-color: #ffffff\"><span><span style=\"font-size: 20px\">请开发部的所有人员于8月30日的下午2点到三楼会议室，会议重要，请勿缺席。</span></span></span></span></p>',3052,'2009-08-25 14:27:54');
INSERT INTO `tb_message` VALUES (17,'研发部会议通知','<p><span style=\"color: #ff0000\"><span style=\"font-size: 20px\">请研发部的所有人员于8月30日的下午2点到三楼会议室，会议重要，请勿缺席！！</span></span></p>',3052,'2009-08-25 14:33:51');
INSERT INTO `tb_message` VALUES (18,'关于国庆放假的通知','<p><span style=\"font-size: 20px\">按国家规定国庆放假七天，8月8日正常上班。</span></p>',3052,'2009-08-25 14:35:35');
INSERT INTO `tb_message` VALUES (19,'请假一天，望领导能批准','<p>由于家里有事情，急需处理，现请假一天，忘领导能批准。</p>',3052,'2009-08-25 14:37:02');
INSERT INTO `tb_message` VALUES (20,'测试一下发布！','<p><img alt=\"\" src=\"http://localhost:8080/JavaPrj_1/fckeditor/editor/images/smiley/qq/004.gif\" />测试一下！</p>',3052,'2010-01-26 20:53:57');

#
# Table structure for table tb_reply
#

CREATE TABLE `tb_reply` (
  `replyID` int(11) NOT NULL auto_increment,
  `replyContent` text,
  `employeeID` int(11) default NULL,
  `replyTime` datetime default NULL,
  `messageID` int(11) default NULL,
  PRIMARY KEY  (`replyID`),
  KEY `employeeID` (`employeeID`),
  KEY `messageID` (`messageID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gb2312 COMMENT='消息回复表';
INSERT INTO `tb_reply` VALUES (9,'<p>太好了，希望以后不要再无故掉线了！！！</p>',3052,'2009-08-25 15:03:23',14);
INSERT INTO `tb_reply` VALUES (10,'<p>是哦，总算是能够安心上网了。</p>',3052,'2009-08-25 15:03:49',14);
INSERT INTO `tb_reply` VALUES (11,'<p>测试回复</p>',3052,'2010-01-26 20:52:26',14);

#
#  Foreign keys for table tb_criticism
#

ALTER TABLE `tb_criticism`
ADD CONSTRAINT `tb_criticism_ibfk_1` FOREIGN KEY (`employeeID`) REFERENCES `tb_employee` (`employeeID`),
  ADD CONSTRAINT `tb_criticism_ibfk_2` FOREIGN KEY (`messageID`) REFERENCES `tb_message` (`messageID`);

#
#  Foreign keys for table tb_message
#

ALTER TABLE `tb_message`
ADD CONSTRAINT `tb_message_ibfk_1` FOREIGN KEY (`employeeID`) REFERENCES `tb_employee` (`employeeID`);

#
#  Foreign keys for table tb_reply
#

ALTER TABLE `tb_reply`
ADD CONSTRAINT `tb_reply_ibfk_1` FOREIGN KEY (`employeeID`) REFERENCES `tb_employee` (`employeeID`),
  ADD CONSTRAINT `tb_reply_ibfk_2` FOREIGN KEY (`messageID`) REFERENCES `tb_message` (`messageID`);


/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
